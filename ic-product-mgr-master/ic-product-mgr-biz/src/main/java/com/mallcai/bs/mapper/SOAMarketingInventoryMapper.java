package com.mallcai.bs.mapper;

import java.util.List;

import com.dailuobo.api.domain.entity.StoreMarketingInventory;
import org.apache.ibatis.annotations.Param;

import com.dailuobo.api.domain.soa.SOAMarketingInventoryVo;
import com.dailuobo.api.domain.soa.inventory.MarketingInventoryVo;

public interface SOAMarketingInventoryMapper {

	void setMarketingInventoryV2(SOAMarketingInventoryVo miv);

	MarketingInventoryVo getMarketingInventory(@Param("cityProductId") Integer cityProductId, @Param("storeId") Integer storeId);

	MarketingInventoryVo getAlarmUser(@Param("cityId") Integer cityId, @Param("cityProductId") Integer cityProductId);
	
	int setMarketingInventoryShare(@Param("cityProductId")Integer cityProductId,  @Param("available")Integer available,  @Param("threshold")Integer threshold,  @Param("type")Integer type,@Param("delta")Integer delta,@Param("updateUserId")Integer updateUserId);
    
	List<MarketingInventoryVo> selectAll(@Param("cityProductIds") List<Integer> cityProductIds);

	List<MarketingInventoryVo> selectAllByCityId(@Param("cityId") Integer cityId);


	int setMarketingInventory(@Param("cityProductId") Integer cityProductId, @Param("storeId") Integer storeId, @Param("delta") Integer delta, @Param("threshold") Integer threshold, @Param("source") Integer source);

	int deleteMarketingInventory(@Param("cityProductIds") List<Integer> cityProductIds);

	int setMarketingInventoryTask(@Param("cityProductId") Integer cityProductId, @Param("storeId") Integer storeId, @Param("delta") Integer delta);

	int setMarketingInventoryShareWarehouse(StoreMarketingInventory inventory);
	
}
