package com.mallcai.bs.service;

import com.mallcai.backend.common.redis.generator.RedisKeyGenerator;
import com.mallcai.backend.common.redis.utils.RedisHash;
import com.mallcai.bs.dao.CityProductDao;
import com.dailuobo.api.domain.entity.CityProduct;
import com.mallcai.bs.mapper.SOAMarketingInventoryMapper;
import com.dailuobo.api.domain.soa.inventory.InventoryConstant;
import com.dailuobo.api.domain.soa.inventory.MarketingInventoryVo;
import org.apache.commons.lang3.StringUtils;
import com.mallcai.backend.common.redis.DefaultMasterJedisProxy;
import com.mallcai.backend.common.redis.JedisProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 库存redis操作service.
 * @author zhanghao
 */
@Service
public class MarketingInventoryRedisService {

    @Autowired
    private SOAMarketingInventoryMapper SOAMarketingInventoryMapper;

    @Autowired
    private CityProductDao cityProductDao;

    /**
     * 删除库存，刷新redis.
     * @param cityProductIdList
     */
    public void deleteInventoryByCityProductIds(List<Integer> cityProductIdList){
        if(cityProductIdList == null || cityProductIdList.isEmpty()){
            return;
        }

        JedisProxy proxy = JedisProxy.getInstance();
        List<MarketingInventoryVo> voList = SOAMarketingInventoryMapper.selectAll(cityProductIdList);
        List<String> keys = new ArrayList<>();
        for (MarketingInventoryVo vo : voList) {
            String hashFieldKey = RedisKeyGenerator.generatorMarketingInventoryPrimaryKey(vo.getCityProductId());
            keys.add(hashFieldKey);

            String fieldName = vo.getCityProductId() + ":" + vo.getStoreId();
            proxy.delHashField(InventoryConstant.MARKETING_INVENTORY_ALARMED_REDIS_KEY + ":cityId:" + vo.getCityId(), fieldName);
        }
        if (keys != null && !keys.isEmpty()) {
            proxy.delMultiKey(keys.toArray(new String[]{}));
        }
    }

    /**
     * 刷新商品库存.
     * @param cityProductIdList
     */
    public void refreshInventoryByCityProductIds(List<Integer> cityProductIdList){
        if(cityProductIdList == null || cityProductIdList.isEmpty()){
            return;
        }
        Map<Integer, Boolean> map = judgeCityProductIsNeedWarehouse(cityProductIdList);
        if(null == map){
            return;
        }
        JedisProxy proxy = JedisProxy.getInstance();
        List<MarketingInventoryVo> voList = SOAMarketingInventoryMapper.selectAll(cityProductIdList);
        List<String> keys = new ArrayList<>();
        List<RedisHash> redisHashList = new ArrayList();
        RedisHash redisHash = null;
        for (MarketingInventoryVo marketingInventoryVo : voList) {
            String hashFieldKey = RedisKeyGenerator.generatorMarketingInventoryPrimaryKey(marketingInventoryVo.getCityProductId());

            keys.add(hashFieldKey);
            redisHash = new RedisHash();
            redisHash.setKey(hashFieldKey);

            //如果是多仓开放的城市，且商品是共享的，field不同
            if(map.get(marketingInventoryVo.getCityProductId()) == null){
                continue;
            }
            boolean flag = map.get(marketingInventoryVo.getCityProductId());
            if(flag && marketingInventoryVo.getStoreId() == 0){
                redisHash.setField(RedisKeyGenerator.generatorWareHouseHashKey(marketingInventoryVo.getStoreId(),marketingInventoryVo.getWarehouseId()));
            } else {
                redisHash.setField(RedisKeyGenerator.generatorNoWareHouseHashKey(marketingInventoryVo.getStoreId()));
            }
            if (marketingInventoryVo.getAvailable() != null && marketingInventoryVo.getAvailable() > 0) {
                redisHash.setValue("1");
            } else {
                redisHash.setValue("0");
            }
            redisHashList.add(redisHash);
        }
        if (keys != null && !keys.isEmpty()) {
            proxy.delMultiKey(keys.toArray(new String[]{}));
        }

        if (redisHashList != null && !redisHashList.isEmpty()) {
            redisHashList.forEach(redisHash1 -> proxy.setHashField(redisHash1.getKey(),redisHash1.getField(),redisHash1.getValue()));
        }
    }

    /**
     * 刷新商品库存--未分仓.
     * @param cityProductIdList
     */
    public void refreshInventoryByCityProductIdsFromOld(List<Integer> cityProductIdList){
        if(cityProductIdList == null || cityProductIdList.isEmpty()){
            return;
        }
        Map<Integer, Boolean> map = judgeCityProductIsNeedWarehouseOld(cityProductIdList);
        if(null == map){
            return;
        }
        JedisProxy proxy = JedisProxy.getInstance();
        List<MarketingInventoryVo> voList = SOAMarketingInventoryMapper.selectAll(cityProductIdList);
        List<String> keys = new ArrayList<>();
        List<RedisHash> redisHashList = new ArrayList();
        RedisHash redisHash = null;
        for (MarketingInventoryVo marketingInventoryVo : voList) {
            String hashFieldKey = RedisKeyGenerator.generatorMarketingInventoryPrimaryKey(marketingInventoryVo.getCityProductId());

            keys.add(hashFieldKey);
            redisHash = new RedisHash();
            redisHash.setKey(hashFieldKey);

            //如果是多仓开放的城市，且商品是标品、共享的，field不同
            if(map.get(marketingInventoryVo.getCityProductId()) == null){
                continue;
            }
            boolean flag = map.get(marketingInventoryVo.getCityProductId());
            if(flag && marketingInventoryVo.getStoreId() == 0){
                redisHash.setField(RedisKeyGenerator.generatorWareHouseHashKey(marketingInventoryVo.getStoreId(),marketingInventoryVo.getWarehouseId()));
            } else {
                redisHash.setField(RedisKeyGenerator.generatorNoWareHouseHashKey(marketingInventoryVo.getStoreId()));
            }
            if (marketingInventoryVo.getAvailable() != null && marketingInventoryVo.getAvailable() > 0) {
                redisHash.setValue("1");
            } else {
                redisHash.setValue("0");
            }
            redisHashList.add(redisHash);
        }
        if (keys != null && !keys.isEmpty()) {
            proxy.delMultiKey(keys.toArray(new String[]{}));
        }
        if (redisHashList != null && !redisHashList.isEmpty()) {
            redisHashList.forEach(redisHash1 -> proxy.setHashField(redisHash1.getKey(),redisHash1.getField(),redisHash1.getValue()));
        }
    }

    /**
     * 判断商品是否可以使用多仓.
     * 城市在开发城市中，标品且共享
     * @param cityProductIdList
     * @return
     */
    public Map<Integer,Boolean> judgeCityProductIsNeedWarehouse(List<Integer> cityProductIdList){
        //共享标品 多仓 开发城市
        List<CityProduct> list = cityProductDao.selectCityProductListByCityProductIds(cityProductIdList);
        if(list != null && !list.isEmpty()){
            Map<Integer,Boolean> resultMap = new HashMap<>(16);
            list.forEach(cityProduct -> {
                resultMap.put(cityProduct.getCityProductId(),judgeCityProductIsShareAndStandard(cityProduct.getCityId(),cityProduct.getIsShare(),cityProduct.getIsStandard()));
            });
            return resultMap;
        }
        return null;
    }

    /**
     * 判断商品是否可以使用多仓.
     * 城市在开发城市中，标品且共享
     * @param cityProductIdList
     * @return
     */
    public Map<Integer,Boolean> judgeCityProductIsNeedWarehouseOld(List<Integer> cityProductIdList){
        //共享标品 多仓 开发城市
        List<CityProduct> list = cityProductDao.selectCityProductListByCityProductIds(cityProductIdList);
        if(list != null && !list.isEmpty()){
            Map<Integer,Boolean> resultMap = new HashMap<>(16);
            list.forEach(cityProduct -> {
                resultMap.put(cityProduct.getCityProductId(),judgeCityProductIsShareAndStandardOld(cityProduct.getCityId(),cityProduct.getIsShare(),cityProduct.getIsStandard()));
            });
            return resultMap;
        }
        return null;
    }

    /**
     * 判断商品是否可以设置共享标品多仓
     * @param cityProductIdList
     * @return
     */
    public Map<Integer,Boolean> judgeCityProductIsAllowSetShare(List<Integer> cityProductIdList){
        //共享标品 多仓 开发城市
        List<CityProduct> list = cityProductDao.selectCityProductListByCityProductIds(cityProductIdList);
        if(list != null && !list.isEmpty()){
            Map<Integer,Boolean> resultMap = new HashMap<>(16);
            list.forEach(cityProduct -> {
                resultMap.put(cityProduct.getCityProductId(),judgeCityIsHaveMoreWarehouse(cityProduct.getCityId()));
            });
            return resultMap;
        }
        return null;
    }

    /**
     * 判断商品是否可以使用多仓.
     * 城市在开发城市中，标品且共享
     * 非标品，走生鲜仓
     * @param cityId
     * @param isShare
     * @param isStandard
     * @return
     */
    private boolean judgeCityProductIsShareAndStandardOld(Integer cityId,Integer isShare,Integer isStandard){
        return judgeCityIsHaveMoreWarehouse(cityId) && isShare == 1 && isStandard == 1;
    }

    /**
     * 判断商品是否可以使用多仓.
     * 城市在开发城市中，标品且共享
     * 非标品，走生鲜仓
     * @param cityId
     * @param isShare
     * @param isStandard
     * @return
     */
    private boolean judgeCityProductIsShareAndStandard(Integer cityId,Integer isShare,Integer isStandard){
        return judgeCityIsHaveMoreWarehouse(cityId) && isShare == 1;
    }

    /**
     * 判断城市是否在 开发多仓的城市里.
     * @param cityId
     * @return
     */
    public boolean judgeCityIsHaveMoreWarehouse(Integer cityId){
        String wareCityIds = DefaultMasterJedisProxy.getInstance().get(InventoryConstant.MGR_CITY_MOREWAREHOUSE_CITYIDS_KEY);
        if(StringUtils.isEmpty(wareCityIds)){
            return false;
        }
        if(makeStringAppendComma(wareCityIds).contains(makeStringAppendComma(cityId.toString()))){
            return true;
        }
        return false;
    }

    private String makeStringAppendComma(String str){
        return ","+str+",";
    }
}
