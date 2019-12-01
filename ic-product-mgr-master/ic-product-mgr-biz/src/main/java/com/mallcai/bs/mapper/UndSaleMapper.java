package com.mallcai.bs.mapper;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dailuobo.api.domain.entity.UnsaleCount;

public interface UndSaleMapper {
	
	 public void pickupLog(@Param("cityId") Integer cityId, @Param("storeId") Integer storeId, @Param("operatorId") Integer operatorId, @Param("json") String json);
	
	 public List<UnsaleCount> selectCount(Map<String, Object> param);

	public Integer getCityProduct(Map<String, Object> param);

	public List<UnsaleCount> selectAllStoreCount(Map<String, Object> param);

	public int checkProductStatus(Map<String, Object> param);

	public int checkProductClassify(Map<String, Object> param);

	Integer checkIsSmoke(Map<String, Object> param);

	Integer checkIsSmoke2(HashMap<String, Object> paramMap);
}
