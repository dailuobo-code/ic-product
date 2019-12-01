package com.mallcai.bs.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dailuobo.api.domain.soa.SOAUnStoreMarketingInventory;

public interface UnMarketingMapper {

	List<SOAUnStoreMarketingInventory> getStoreMarketingInventories(
			Map<String, Object> param);

	List<SOAUnStoreMarketingInventory> getUnMarketingInventories(
			Map<String, Object> param);

	int updateInventory(Map<String,Object> param);

	int countUnMarketingInventory(Map<String,Object> param);

	int insertUnMarketingInventory(Map<String,Object> param);

	int updateAvailable(Map<String,Object> param);

	int replaceAvailable(Map<String,Object> param);

	SOAUnStoreMarketingInventory getUndSalesSpec(@Param("cityProductId") Integer cityProductId);
	List<Map<String,Object>> getCityProductTotalAvailable(@Param("cityIdList") List<Integer> cityIdList, @Param("cityProductIdList") List<Integer> cityProductIdList);

	//香烟调拨
	int countAvailableInventory(@Param("storeId") Integer storeId, @Param("cityProductId") Integer cityProductId,
								@Param("available") Integer available);
}
