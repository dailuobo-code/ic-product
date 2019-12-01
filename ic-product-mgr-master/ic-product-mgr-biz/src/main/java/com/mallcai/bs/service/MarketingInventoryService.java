package com.mallcai.bs.service;

import com.dailuobo.api.domain.entity.OrderInventory;
import com.google.common.collect.Lists;
import com.mallcai.bs.dao.CityProductDao;
import com.mallcai.bs.dao.CityWarehouseDao;
import com.mallcai.bs.dao.MarketingInventoryDao;
import com.dailuobo.api.domain.entity.CityProduct;
import com.dailuobo.api.domain.entity.MarketingInventory;
import com.dailuobo.api.domain.entity.StoreMarketingInventory;
import com.dailuobo.api.domain.soa.inventory.MarketingInventoryVo;
import com.dailuobo.api.domain.util.LoggerUtils;
import com.dailuobo.api.domain.vo.DDLWarehouse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Cowboy on 2016/4/13.
 */
@Service
public class MarketingInventoryService {
    @Autowired
    private MarketingInventoryDao marketingInventoryDao;
    @Autowired
    private CityWarehouseDao cityWarehouseDao;
    @Autowired
    private CityProductDao cityProductDao;

    @Autowired
    private MarketingInventoryRedisService marketingInventoryRedisService;

    @Transactional
    public List<StoreMarketingInventory> getStoreMarketingInventories(Map<String, Object> param) {
        return marketingInventoryDao.getStoreMarketingInventories(param);
    }

    public List<MarketingInventory> getMarketingInventories(Integer cityId, Integer cityProductId) {
        List<MarketingInventory> list = marketingInventoryDao.getMarketingInventories(cityId, cityProductId);

        if (list == null || list.isEmpty()) {
            return list;
        }
        List<Integer> storeIds = new ArrayList<>();
        list.forEach(marketingInventory -> {
            storeIds.add(marketingInventory.getStoreId());
        });
        //商品对应的仓库类型
        Integer wareHouseType = getCityProductWareHouseTypeByCityProductId(cityProductId);

        //共享库存在单量查询
        List<OrderInventory> orderInventoryList = marketingInventoryDao.getOrderInventoryListByCityProductId(cityProductId);
        Map<Integer, DDLWarehouse> storeWarehouseMap = cityWarehouseDao.getDDLWarehouseMapByStoreIdList(storeIds, wareHouseType);
        if (null == orderInventoryList || orderInventoryList.isEmpty()) {
            list.forEach(marketingInventory -> {
                DDLWarehouse dDLWarehouse = storeWarehouseMap.get(marketingInventory.getStoreId());
                if (null != dDLWarehouse) {
                    marketingInventory.setWarehouseId(dDLWarehouse.getWarehouseId());
                    marketingInventory.setWarehouseName(dDLWarehouse.getWarehouseName());
                }
            });
            return list;
        }

        list.forEach(marketingInventory -> {
            marketingInventory.setAvailableBase(null); //初使化库存量
        });

        for (OrderInventory orderInventory : orderInventoryList) {
            DDLWarehouse orderWarehouse = storeWarehouseMap.get(orderInventory.getStoreId());
            list.forEach(marketingInventory -> {
                if (marketingInventory.getStoreId().equals(orderInventory.getStoreId())) {
                    if (null != orderWarehouse) {
                        marketingInventory.setWarehouseId(orderWarehouse.getWarehouseId());
                        marketingInventory.setWarehouseName(orderWarehouse.getWarehouseName());
                    }
                    marketingInventory.setInQuantity((null == marketingInventory.getInQuantity() ? 0 : marketingInventory.getInQuantity()) + (null == orderInventory.getProductNum() ? 0 : orderInventory.getProductNum()));
                    //重新计算库存量
                    marketingInventory.setAvailableBase((null == marketingInventory.getAvailable() ? 0 : marketingInventory.getAvailable()) +
                            (null == marketingInventory.getInQuantity() ? 0 : marketingInventory.getInQuantity()));
                }
            });
        }
        return list;
    }

    public List<MarketingInventory> getMarketingShares(Integer storeId, Integer cityProductId) {
        Integer wareHouseType = getCityProductWareHouseTypeByCityProductId(cityProductId);
        List<MarketingInventory> marketingInventoryList = marketingInventoryDao.getMarketingShares(storeId, cityProductId);
        inQuantityCalcuShares(cityProductId, wareHouseType, marketingInventoryList);
        return marketingInventoryList;

    }

    public List<MarketingInventory> getBulkMarketingShares(List<Integer> cityProductIds) {
        return marketingInventoryDao.getBulkMarketingShares(cityProductIds);
    }


    public List<MarketingInventory> getWareHouseMarketingShares(Integer storeId, Integer cityProductId, Integer cityId) {
        Integer wareHouseType = getCityProductWareHouseTypeByCityProductId(cityProductId);
        List<MarketingInventory> marketingInventoryList = marketingInventoryDao.getWareHouseMarketingShares(storeId, cityProductId, cityId, wareHouseType);
        inQuantityCalcuShares(cityProductId, wareHouseType, marketingInventoryList);
        return marketingInventoryList;

    }

    /**
     * 获取单个商品仓库类型.
     *
     * @param cityProductId
     * @return
     */
    public Integer getCityProductWareHouseTypeByCityProductId(Integer cityProductId) {
        List<Integer> cityProductIdList = new ArrayList<>();
        cityProductIdList.add(cityProductId);
        Map<Integer, Integer> typeMap = getCityProductWareHouseType(cityProductIdList);
        Integer wareHouseType = typeMap.get(cityProductId);
        return wareHouseType == null ? -1 : wareHouseType;
    }

    /**
     * 获取商品仓库类型
     *
     * @param cityProductIdList
     * @return
     */
    public Map<Integer, Integer> getCityProductWareHouseType(List<Integer> cityProductIdList) {
        if (cityProductIdList == null || cityProductIdList.isEmpty()) {
            return new HashMap<>();
        }
        List<CityProduct> list = cityProductDao.selectCityProductListByCityProductIds(cityProductIdList);
        Map<Integer, Integer> resultMap = new HashMap<>();
        if (list != null && !list.isEmpty()) {
            list.forEach(cityProduct -> {
                Integer isStandard = cityProduct.getIsStandard();
                Integer wareHouseType = (isStandard != null && isStandard == 1) ? 2 : 1;
                resultMap.put(cityProduct.getCityProductId(), wareHouseType);

            });
        }
        return resultMap;
    }

    @Transactional
    public void updateMarketingShares(Integer isShare, Integer cityId,
                                      Integer cityProductId, Integer updateUserId) {
        // TODO 根据isShare更新
        marketingInventoryDao.updateMarketingShares(isShare, cityId, cityProductId, updateUserId);
    }

    public String checkProduct(Integer cityProductId) {
        // TODO Auto-generated method stub
        return marketingInventoryDao.checkProduct(cityProductId);
    }
/*2019-7-25 mwr注释
    public int checkProductPiece(Integer cityProductId) {
        // TODO Auto-generated method stub
        return marketingInventoryDao.checkProductPiece(cityProductId);
    }*/

    public void updateMarketLog(Map<String, Object> param) {
        // TODO Auto-generated method stub
        marketingInventoryDao.updateMarketLog(param);
    }

    public void updateBannerProductOrder(Integer cityProductId) {
        marketingInventoryDao.updateBannerProductOrder(cityProductId);
    }

    public void updateTileProductOrder(Integer cityProductId) {
        marketingInventoryDao.updateTileProductOrder(cityProductId);
    }

    public void updateThemeProductOrder(Integer cityProductId) {
        marketingInventoryDao.updateThemeProductOrder(cityProductId);
    }

    public List<Integer> getBannerIdsByProductId(Integer cityProductId) {
        return marketingInventoryDao.getBannerIdsByProductId(cityProductId);
    }

    public List<Integer> getTileIdsByProductId(Integer cityProductId) {
        return marketingInventoryDao.getTileIdsByProductId(cityProductId);
    }

    public List<Integer> getThemeIdsByProductId(Integer cityProductId) {
        return marketingInventoryDao.getThemeIdsByProductId(cityProductId);
    }

    public String queryNoIsshareProduct(List<StoreMarketingInventory> list, Integer cityId) {
        Map<String, Object> param = new HashMap<>();
        param.put("list", list);
        param.put("cityId", cityId);
        return marketingInventoryDao.queryNoIsshareProduct(param);
    }

    public Integer queryCityProductId(String productNo, Integer cityId) {
        Map<String, Object> param = new HashMap<>();
        param.put("productNo", productNo);
        param.put("cityId", cityId);
        return marketingInventoryDao.queryNoProduct(param);
    }

    /**
     * 批量设置导入商品的库存信息
     * 事务控制
     */
    @Transactional
    public void ManipulationData(List<StoreMarketingInventory> list, Integer userId, Integer cityId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("list", list);
        param.put("updateUserId", userId);
        param.put("type", 1);   //共享商品
        /*2019-7-25 mwr注释
        LoggerUtils.getLogger().info("执行关于萝卜拼商品校验开始" + new Date().getTime());
        String queryCityProductIds = marketingInventoryDao.queryPieceByCityProductIds(param);
         if(!StringUtils.isBlank(queryCityProductIds)){
                throw new Exception("商品在有效萝卜拼拼团中，无法设置库存库存:"+queryCityProductIds);
         }
        LoggerUtils.getLogger().info("执行关于萝卜拼商品校验结束" + new Date().getTime());*/

        LoggerUtils.getLogger().info("执行批量设置共享商品信息开始" + new Date().getTime());
        marketingInventoryDao.updateMarketingInventory(param);
        LoggerUtils.getLogger().info("执行批量设置共享商品信息结束" + new Date().getTime());

        LoggerUtils.getLogger().info("执行关于设置共享商品库存日志开始" + new Date().getTime());

        for (StoreMarketingInventory storeMarketingInventory : list) {
            //日志
            Map<String, Object> map = new HashMap<>();
            map.put("cityProductId", storeMarketingInventory.getCityProductId());
            map.put("updateUserId", userId);
            map.put("delta", storeMarketingInventory.getDelta());
            map.put("storeId", 0);
            map.put("cityId", cityId);
            updateMarketLog(map);
        }
        LoggerUtils.getLogger().info("执行关于设置共享商品库存日志结束" + new Date().getTime());
    }


    public void refreshMarketing(List<StoreMarketingInventory> list) {
        Map<String, Object> param = new HashMap<>();
        param.put("list", list);
        List<MarketingInventoryVo> voList = marketingInventoryDao.selectCityProductId(param);
        //刷新所有的门店库存数据
        List<Integer> cityProductIds = new ArrayList<>();
        voList.forEach(marketingInventoryVo -> cityProductIds.add(marketingInventoryVo.getCityProductId()));
        marketingInventoryRedisService.refreshInventoryByCityProductIds(cityProductIds);
    }

    public void saveImportLog(List<StoreMarketingInventory> list, Integer createuser) {
        Map<String, Object> param = new HashMap<>();
        param.put("list", list);
        param.put("createuser", createuser);
        marketingInventoryDao.saveImportLog(param);
    }

    /**
     * 在单量计算
     *
     * @param cityProductId
     * @param wareHouseType
     * @param marketingInventoryList
     */
    private void inQuantityCalcuShares(Integer cityProductId, Integer wareHouseType, List<MarketingInventory> marketingInventoryList) {
        if (CollectionUtils.isEmpty(marketingInventoryList)) {
            return;
        }
        if (!isMultiSpec(Lists.newArrayList(cityProductId))) {
            for (MarketingInventory marketingInventory : marketingInventoryList) {
                marketingInventory.setInQuantity(null == marketingInventory.getAvailableBase() ? 0 :
                        marketingInventory.getAvailableBase() - (null == marketingInventory.getAvailable() ? 0 : marketingInventory.getAvailable()));
                if (marketingInventory.getAvailable() == null) {
                    marketingInventory.setAvailable(0);
                }
            }
            return;
        }
        //订单在单量查询
        List<OrderInventory> orderInventoryList = marketingInventoryDao.getOrderInventoryListByCityProductId(cityProductId);
        if (null == orderInventoryList || orderInventoryList.isEmpty()) {
            return;
        }
        List<Integer> storeIds = new ArrayList<>();
        for (OrderInventory orderInventory : orderInventoryList) {
            storeIds.add(orderInventory.getStoreId());
        }
        //商品对应的仓库类型
        Map<Integer, DDLWarehouse> storeWarehouseMap = cityWarehouseDao.getDDLWarehouseMapByStoreIdList(storeIds, wareHouseType);
        for (OrderInventory orderInventory : orderInventoryList) {
            for (MarketingInventory marketingInventory : marketingInventoryList) {
                if (null != marketingInventory.getWarehouseId() && !marketingInventory.getWarehouseId().equals(-1)) {
                    //查询orderInventory门店对应的wareHouseId与marketingInventory对应wareHouseId进行比较,相同则累加在单量
                    DDLWarehouse orderWarehouse = storeWarehouseMap.get(orderInventory.getStoreId());
                    if (null != orderWarehouse && marketingInventory.getWarehouseId().equals(orderWarehouse.getWarehouseId())) {
                        marketingInventory.setInQuantity((null == marketingInventory.getInQuantity() ? 0 : marketingInventory.getInQuantity()) + (null == orderInventory.getProductNum() ? 0 : orderInventory.getProductNum()));
                    }
                } else {
                    marketingInventory.setInQuantity((null == marketingInventory.getInQuantity() ? 0 : marketingInventory.getInQuantity()) + (null == orderInventory.getProductNum() ? 0 : orderInventory.getProductNum()));
                }
                //重新计算库存量
                marketingInventory.setAvailableBase((null == marketingInventory.getAvailable() ? 0 : marketingInventory.getAvailable()) +
                        (null == marketingInventory.getInQuantity() ? 0 : marketingInventory.getInQuantity()));
            }
        }
    }

    private Boolean isMultiSpec(List<Integer> cityProductIds) {
        int count = marketingInventoryDao.countMultiSpec(cityProductIds);
        return count > 0;
    }
}
