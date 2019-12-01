package com.mallcai.bs.service;

import com.dailuobo.biz.service.redis.InventoryRedisService;
import com.google.common.collect.Lists;
import com.mallcai.backend.common.redis.generator.RedisKeyGenerator;
import com.mallcai.bs.dao.PieceProductDao;
import com.dailuobo.api.domain.entity.PieceGroup;
import com.dailuobo.api.domain.entity.StoreMarketingInventory;
import com.mallcai.bs.mapper.SOAMarketingInventoryMapper;
import com.dailuobo.api.domain.soa.SOAMarketingInventoryVo;
import com.dailuobo.api.domain.soa.SOAStoreProduct;
import com.dailuobo.api.domain.soa.StandardResult;
import com.dailuobo.api.domain.soa.inventory.InventoryConstant;
import com.dailuobo.api.domain.soa.inventory.MarketingInventoryVo;
import com.dailuobo.api.domain.util.LoggerUtils;
import com.dailuobo.api.domain.util.ZooKeeperLock;
import com.mallcai.backend.common.redis.JedisProxy;
import com.mallcai.service.inventory.marketing.api.IStockWriteService;
import com.mallcai.service.inventory.marketing.enums.InventoryAdjustEnum;
import com.mallcai.service.inventory.marketing.request.AdjustInventoryRequest;
import com.mallcai.service.inventory.marketing.request.IvyMarketingInventoryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
@Transactional
public class SOAMarketingInventoryService {
    @Autowired
    SOAMarketingInventoryMapper SOAMarketingInventoryMapper;
    @Autowired
    SOAMailSmsSender SOAMailSmsSender;
    @Autowired
    private ZooKeeperLock lock;
    @Autowired
    private PieceProductDao pieceProductDao;
    @Autowired
    private PieceProductService pieceProductService;

    @Autowired
    private InventoryRedisService inventoryRedisService;

    @Autowired
    private MarketingInventoryRedisService marketingInventoryRedisService;

    @Resource
    private IStockWriteService iStockWriteService;

    private boolean isTrue(String value) {
        return "1".equals(value);
    }

    public Map<Integer, Boolean> isAvailable(Map<SOAStoreProduct, Integer> map) {
        //LoggerUtils.getLogger().info(">>>>>库存是否可用，map={}", map);
        List<String> fields = new ArrayList<>();
        //for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        for (Map.Entry<SOAStoreProduct, Integer> entry : map.entrySet()) {
            if (entry.getKey().getIsShare() != null && entry.getKey().getIsShare() == 1) {//如果是共享库存
                fields.add(entry.getKey().getCityProductId() + ":" + 0);//如果是共享库存则查看storeid为0的库存
            } else {
                fields.add(entry.getKey().getCityProductId() + ":" + entry.getValue());//如果不是共享库存则查看门店库存
            }

        }
        Map<String, String> codisData = JedisProxy.getInstance().getHashMultiField(InventoryConstant.MARKETING_INVENTORY_FLAG_REDIS_KEY, fields.toArray(new String[]{}));
        Map<Integer, Boolean> resultMap = new HashMap<>();
        for (Map.Entry<String, String> entry : codisData.entrySet()) {
            resultMap.put(Integer.parseInt(entry.getKey().split(":")[0]), isTrue(entry.getValue()));
        }
        return resultMap;
    }

    public StandardResult setMarketingInventoryV2(SOAMarketingInventoryVo miv) {
        try {
            if (miv.getDelta() == null) {
                miv.setDelta(0);

            }
            miv.setSource(1);
            SOAMarketingInventoryMapper.setMarketingInventoryV2(miv);

            JedisProxy proxy = JedisProxy.getInstance();
            MarketingInventoryVo vo = SOAMarketingInventoryMapper.getMarketingInventory(miv.getCityProductId(), miv.getStoreId());
            //刷新库存缓存
            List<Integer> cityProductIdList = new ArrayList<>();
            cityProductIdList.add(miv.getCityProductId());
            marketingInventoryRedisService.refreshInventoryByCityProductIds(cityProductIdList);

            alarm(proxy, vo);
            return StandardResult.getDefaultSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.getLogger().error(e.getMessage(), e);
            return StandardResult.getDefaultFailedMsg(e.getMessage());
        }
    }

    private void alarm(JedisProxy proxy, MarketingInventoryVo vo) {
        String key = InventoryConstant.MARKETING_INVENTORY_ALARMED_REDIS_KEY + ":cityId:" + vo.getCityId();
        String fieldName = vo.getCityProductId() + ":" + vo.getStoreId();

        if (vo.getAvailable() <= vo.getThreshold()) {  //是否达到预警数量

            if (!isTrue(proxy.getHashField(key, fieldName))) {  //判断是否已经预警

                /**********zyc 添加于20180523 用于获取单商品预警提醒用户信息*****/
                String alarmKey = RedisKeyGenerator.generateMarketingUser(vo.getCityId(), vo.getCityProductId());
                String alarmFieldName_phone = InventoryConstant.MARKETING_INVENTORY_ALARMED_USER_PHONE;
                String alarmFieldName_email = InventoryConstant.MARKETING_INVENTORY_ALARMED_USER_EMAIL;
                String phoneStr = proxy.getHashField(alarmKey, alarmFieldName_phone);
                String mailStr = proxy.getHashField(alarmKey, alarmFieldName_email);
                if (phoneStr == null || mailStr == null || phoneStr.isEmpty() || mailStr.isEmpty()) {
                    refashAlarmUser(vo.getCityId(), vo.getCityProductId());
                    phoneStr = proxy.getHashField(alarmKey, alarmFieldName_phone);
                    mailStr = proxy.getHashField(alarmKey, alarmFieldName_email);
                }
                /***********************************************************/
                try {
                    List<String> phoneList = new ArrayList<>();
                    List<String> emailList = new ArrayList<>();
                    if (!StringUtils.isEmpty(phoneStr)) {
                        phoneList = Arrays.asList(phoneStr.replaceAll(" ", "").split(","));
                    }
                    if (!StringUtils.isEmpty(mailStr)) {
                        emailList = Arrays.asList(mailStr.replaceAll(" ", "").split(","));
                    }

                    if (phoneList.size() > 0) {
                        SOAMailSmsSender.sendStockAlarmSMS(phoneList, vo.getShowName(), vo.getProductNo());
                    }
                    if (emailList.size() > 0) {
                        if (vo.getDeliveryMode() == 1) {
                            SOAMailSmsSender.sendHtmlMail(emailList, "库存预警", "城市：" + vo.getCityName() + "，门店：" + vo.getStoreName() + "，城市商品ID：" + vo.getCityProductId() + "，商品编号：" + vo.getProductNo() + "，投放名称：" + vo.getShowName() + "，库存可用量（" + vo.getAvailable() + "）低于预警值（" + vo.getThreshold() + "）！");
                        } else {
                            SOAMailSmsSender.sendHtmlMail(emailList, "库存预警", "城市：" + vo.getCityName() + "，仓库：" + vo.getStoreName() + "，城市商品ID：" + vo.getCityProductId() + "，商品编号：" + vo.getProductNo() + "，投放名称：" + vo.getShowName() + "，库存可用量（" + vo.getAvailable() + "）低于预警值（" + vo.getThreshold() + "）！");
                        }
                    }
                    proxy.setHashField(key, fieldName, "1");
                } catch (Throwable t) {
                    t.printStackTrace();
                    LoggerUtils.getLogger().error(t.getMessage(), t);
                }
            }
        } else {
            proxy.setHashField(key, fieldName, "0");
        }
    }

    private void refashAlarmUser(Integer cityId, Integer cityProductId) {
        MarketingInventoryVo alarmUser = SOAMarketingInventoryMapper.getAlarmUser(cityId, cityProductId);
        if (alarmUser != null) {
            JedisProxy proxy = JedisProxy.getInstance();
            String alarmKey = RedisKeyGenerator.generateMarketingUser(cityId, cityProductId);
            String alarmFieldName_phone = InventoryConstant.MARKETING_INVENTORY_ALARMED_USER_PHONE;
            String alarmFieldName_email = InventoryConstant.MARKETING_INVENTORY_ALARMED_USER_EMAIL;
            if (!alarmUser.getPhoneStr().isEmpty()) {
                proxy.setHashField(alarmKey, alarmFieldName_phone, alarmUser.getPhoneStr());
            }
            if (!alarmUser.getEmailStr().isEmpty()) {
                proxy.setHashField(alarmKey, alarmFieldName_email, alarmUser.getEmailStr());
            }
        }
    }


    public StandardResult setMarketingInventoryShare(Integer cityProductId, Integer available, Integer threshold, Integer type, Integer delta, Integer updateUserId) {
        try {
            available = available == null ? 0 : available;
            threshold = threshold == null ? 0 : threshold;
            delta = delta == null ? 0 : delta;
            SOAMarketingInventoryMapper.setMarketingInventoryShare(cityProductId, available, threshold, type, delta, updateUserId);
            //刷新库存缓存
            List<Integer> cityProductIdList = new ArrayList<>();
            cityProductIdList.add(cityProductId);
            marketingInventoryRedisService.refreshInventoryByCityProductIds(cityProductIdList);
            //库存变更，删除商品库存值redis
            inventoryRedisService.delStoreWareHouseProductAvailableRedisKey(cityProductId,0,-1);
            return StandardResult.getDefaultSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.getLogger().error(e.getMessage(), e);
            return StandardResult.getDefaultFailedMsg(e.getMessage());
        }
    }

    public StandardResult setMarketingInventoryShareWarehouse(StoreMarketingInventory inventory) {
        try {
            SOAMarketingInventoryMapper.setMarketingInventoryShareWarehouse(inventory);
            //刷新库存缓存
            List<Integer> cityProductIdList = new ArrayList<>();
            cityProductIdList.add(inventory.getCityProductId());
            marketingInventoryRedisService.refreshInventoryByCityProductIds(cityProductIdList);
            return StandardResult.getDefaultSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.getLogger().error(e.getMessage(), e);
            return StandardResult.getDefaultFailedMsg(e.getMessage());
        }
    }


    public StandardResult setMarketingInventory(Integer cityProductId, Integer orgId, Integer delta, Integer threshold, Integer source) throws Exception {
        try {
            if (delta == null) {
                delta = 0;
            }
            SOAMarketingInventoryMapper.setMarketingInventory(cityProductId, orgId, delta, threshold, source);

            JedisProxy proxy = JedisProxy.getInstance();
            String fieldName = cityProductId + ":" + orgId;

            MarketingInventoryVo vo = SOAMarketingInventoryMapper.getMarketingInventory(cityProductId, orgId);
            if (vo.getAvailable() > 0) {
                proxy.setHashField(InventoryConstant.MARKETING_INVENTORY_FLAG_REDIS_KEY, fieldName, "1");
            } else {
                proxy.setHashField(InventoryConstant.MARKETING_INVENTORY_FLAG_REDIS_KEY, fieldName, "0");
            }

            alarm(proxy, vo);
            return StandardResult.getDefaultSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.getLogger().error(e.getMessage(), e);
            return StandardResult.getDefaultFailedMsg(e.getMessage());
        }
    }

    public StandardResult deleteMarketingInventory(List<Integer> cityProductIds) {
        try {
            //删除redis
            marketingInventoryRedisService.deleteInventoryByCityProductIds(cityProductIds);
            SOAMarketingInventoryMapper.deleteMarketingInventory(cityProductIds);
            return StandardResult.getDefaultSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.getLogger().error(e.getMessage(), e);
            return StandardResult.getDefaultFailedMsg(e.getMessage());
        }
    }

    public void marketingInit(boolean flag,Integer cityId) {

        if (lock.lock(InventoryConstant.MARKETING_INVENTORY_LOCK_PREFIX, 0)) {
            try {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LoggerUtils.getLogger().info("营销库存服务实现类定时任务启动...");
                        try {
                            List<MarketingInventoryVo> vos = SOAMarketingInventoryMapper.selectAllByCityId(cityId);
                            List<Integer> cityProductIds = new ArrayList<>();
                            vos.forEach(marketingInventoryVo -> cityProductIds.add(marketingInventoryVo.getCityProductId()));
                            if(!flag){
                                marketingInventoryRedisService.refreshInventoryByCityProductIds(cityProductIds);
                            }else{
                                marketingInventoryRedisService.refreshInventoryByCityProductIdsFromOld(cityProductIds);
                            }
                            LoggerUtils.getLogger().info("营销库存服务实现类定时任务结束...");
                        } catch (Throwable t) {
                            t.printStackTrace();
                            LoggerUtils.getLogger().error(t.getMessage(), t);
                        }
                    }
                }).start();
            } finally {
                lock.unlock();
            }
        }

    }

    public StandardResult setMarketingInventoryTask(Integer cityProductId, Integer orgId, Integer delta) {
        try {
            if (delta == null) {
                delta = 0;
            }
            SOAMarketingInventoryMapper.setMarketingInventoryTask(cityProductId, orgId, delta);

            JedisProxy proxy = JedisProxy.getInstance();
            String fieldName = cityProductId + ":" + orgId;

            MarketingInventoryVo vo = SOAMarketingInventoryMapper.getMarketingInventory(cityProductId, orgId);
            if (vo.getAvailable() > 0) {
                proxy.setHashField(InventoryConstant.MARKETING_INVENTORY_FLAG_REDIS_KEY, fieldName, "1");
            } else {
                proxy.setHashField(InventoryConstant.MARKETING_INVENTORY_FLAG_REDIS_KEY, fieldName, "0");
            }

            alarm(proxy, vo);
            return StandardResult.getDefaultSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtils.getLogger().error(e.getMessage(), e);
            return StandardResult.getDefaultFailedMsg(e.getMessage());
        }
    }

    //对共享库存进行操作

    public void checkPiece(Integer cityProductId, Integer delta, Integer cityId) throws Exception {
        //TODO 对于萝卜拼中的商品，库存新增；该商品正在进行中，不可减少库存
        List<Map> pieceList = pieceProductDao.getPieceByCityProductId(cityProductId);
        if (null != pieceList && pieceList.size() > 0) {
            List<Integer> listIds = new ArrayList<>();
            for (Map map : pieceList) {
                Integer status = (Integer) map.get("status");
                Integer id = (Integer) map.get("id");
                Integer pieceType = (Integer) map.get("pieceType");
                if (status == 1 && delta < 0) {
                    throw new Exception("该商品在有效拼团中，无法减少库存");
                } else {
                    listIds.add(id);
                    //
                    JedisProxy.getInstance().delKey(RedisKeyGenerator.generateSpellProductList(cityId, pieceType));//（小程序1.2.0上线后注释改行）

                    Map param = new HashMap();
                    //通过pieceId查询eventId
                    param.put("pieceId", id);
                    PieceGroup piece = pieceProductService.getPieceInfoById(param);
                    JedisProxy.getInstance().delKey(RedisKeyGenerator.generateSpellProductListByEvtId(cityId, piece.getEventId()));

                    JedisProxy.getInstance().delKey(RedisKeyGenerator.generateCityPieceProductKey(id, cityId));
                    JedisProxy.getInstance().delKey(RedisKeyGenerator.generateSpellDealsList(cityId, id));
                }
            }
            pieceProductDao.updateAvaible(delta, listIds);
        }

    }

}
