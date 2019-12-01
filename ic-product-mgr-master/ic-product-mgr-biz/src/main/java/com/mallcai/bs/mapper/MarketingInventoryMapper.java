package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.entity.MarketingInventory;
import com.dailuobo.api.domain.entity.OrderInventory;
import com.dailuobo.api.domain.entity.StoreMarketingInventory;
import com.dailuobo.api.domain.soa.inventory.MarketingInventoryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Cowboy on 2016/4/13.
 */
public interface MarketingInventoryMapper {
    List<StoreMarketingInventory> getStoreMarketingInventories(Map<String, Object> param);

    List<MarketingInventory> getMarketingInventories(@Param("cityId") Integer cityId, @Param("cityProductId") Integer cityProductId);

	List<MarketingInventory> getMarketingShares(@Param("storeId") Integer storeId, @Param("cityProductId") Integer cityProductId);

	List<MarketingInventory> getBulkMarketingShares(@Param("cityProductIds") List<Integer> cityProductIds);

	List<MarketingInventory> getWareHouseMarketingShares(@Param("storeId") Integer storeId, @Param("cityProductId") Integer cityProductId, @Param("cityId") Integer cityId,@Param("wareHouseType")Integer wareHouseType);

	void updateMarketingShares(@Param("isShare")Integer isShare, @Param("cityId")Integer cityId,
			@Param("cityProductId")Integer cityProductId,@Param("updateUserId")Integer updateUserId);

	String checkProduct(@Param("cityProductId")Integer cityProductId);

	/**
	 * Description:  该商品是不是萝卜拼
	 *
	 * @author liuwei
	 * @param
	 * @CreateDate 2019/1/17 9:29
	 */
	int checkProductPiece(@Param("cityProductId")Integer cityProductId);

	void updateMarketLog(Map<String, Object> param);

	void updateThemeProductOrder(@Param("cityProductId") Integer cityProductId);

	void updateBannerProductOrder(@Param("cityProductId") Integer cityProductId);

	void updateTileProductOrder(@Param("cityProductId") Integer cityProductId);

	List<Integer> getBannerIdsByProductId(@Param("cityProductId") Integer cityProductId);

	List<Integer> getThemeIdsByProductId(@Param("cityProductId") Integer cityProductId);

	List<Integer> getTileIdsByProductId(@Param("cityProductId") Integer cityProductId);

	String queryNoIsshareProduct(Map<String,Object> param);

	Integer queryNoProduct(Map<String,Object> param);

	String queryPieceByCityProductIds(Map<String,Object> param);

	void updateMarketingInventory(Map<String,Object> param);

	List<MarketingInventoryVo> selectCityProductId(Map<String,Object> param);

	void saveImportLog(Map<String, Object> param);

	List<OrderInventory> getOrderInventoryListByParam(Map<String,Object> param);

	List<OrderInventory> batchQueryOrderInventoryByParam(Map<String, Object> mapParam);

    int countMultiSpec(Map<String, Object> mapParam);
}
