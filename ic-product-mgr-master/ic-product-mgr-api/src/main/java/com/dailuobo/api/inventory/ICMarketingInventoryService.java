package com.dailuobo.api.inventory;

import com.dailuobo.api.common.ICResponse;
import com.dailuobo.api.domain.entity.MarketingInventory;
import com.dailuobo.api.domain.entity.StoreMarketingInventory;

import java.util.List;
import java.util.Map;

public interface ICMarketingInventoryService {

    ICResponse<List<StoreMarketingInventory>> getStoreMarketingInventories(Map<String, Object> param);

    ICResponse<List<MarketingInventory>> getMarketingInventories(Integer cityId, Integer cityProductId);

    ICResponse<List<MarketingInventory>> getMarketingShares(Integer storeId, Integer cityProductId);

    ICResponse<List<MarketingInventory>> getBulkMarketingShares(List<Integer> cityProductIds);

    ICResponse<List<MarketingInventory>> getWareHouseMarketingShares(Integer storeId, Integer cityProductId,Integer cityId);

    ICResponse<Integer> getCityProductWareHouseTypeByCityProductId(Integer cityProductId);


    ICResponse<Map<Integer,Integer>> getCityProductWareHouseType(List<Integer> cityProductIdList);


    ICResponse updateMarketingShares(Integer isShare, Integer cityId,
                               Integer cityProductId,Integer updateUserId);

    ICResponse<String> checkProduct(Integer cityProductId);


    ICResponse<Integer> checkProductPiece(Integer cityProductId);

    ICResponse updateMarketLog(Map<String, Object> param);

    ICResponse updateBannerProductOrder(Integer cityProductId);

    ICResponse updateTileProductOrder(Integer cityProductId);

    ICResponse updateThemeProductOrder(Integer cityProductId);

    ICResponse<List<Integer>> getBannerIdsByProductId(Integer cityProductId);

    ICResponse<List<Integer>> getTileIdsByProductId(Integer cityProductId);

    ICResponse<List<Integer>> getThemeIdsByProductId(Integer cityProductId);


    ICResponse<String> queryNoIsshareProduct(List<StoreMarketingInventory> list,Integer cityId);

    ICResponse<Integer> queryCityProductId(String productNo,Integer cityId);

    ICResponse  ManipulationData(List<StoreMarketingInventory> list ,Integer userId, Integer cityId) throws Exception;

    ICResponse refreshMarketing(List<StoreMarketingInventory> list);

    ICResponse saveImportLog(List<StoreMarketingInventory> list,Integer createuser);
}
