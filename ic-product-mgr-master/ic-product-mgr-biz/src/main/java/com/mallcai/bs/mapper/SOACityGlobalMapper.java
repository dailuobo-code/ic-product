package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.soa.SOAAdverts;
import com.dailuobo.api.domain.soa.SOAAdvertsProductList;
import com.dailuobo.api.domain.soa.SOAHeadlineProduct;
import com.dailuobo.api.domain.soa.SOAStoreAdverts;
import com.dailuobo.api.domain.soa.city.CityProductDto;
import com.dailuobo.api.domain.soa.city.CityProductIds;
import com.dailuobo.api.domain.soa.city.SalesSpecDto;
import com.dailuobo.api.domain.soa.city.SystemParamDto;
import com.mallcai.backend.common.dao.vo.StorageCity;
import com.mallcai.backend.common.dao.vo.StorageCityConfig;
import com.mallcai.backend.common.dao.vo.StorageStore;
import org.apache.ibatis.annotations.Param;


import java.util.List;
import java.util.Map;

public interface SOACityGlobalMapper {

	List<CityProductDto> getCityProductByCityId(Integer cityId);

	List<SalesSpecDto> getProductSpecByCityProductId(Integer productId);
	
	public CityProductDto getProductByCityProductId(Integer cityProductId);
	
	public List<StorageStore> getCityStoreList(@Param("cityId") Integer cityId);

	SOAAdverts getAdvertsById(Integer adId);

    public List<SOAAdvertsProductList> getAdvertsProductListById(Integer adId);
    
	public List<SOAHeadlineProduct> getHeadlineProductListByHeadlineId(Integer headlineId);

	List<SystemParamDto> getSysParam();

	List<SOAStoreAdverts> getStoreAdverts(@Param("cityId")Integer cityId, @Param("storeId")Integer storeId);

	List<StorageCity> getEstableCity();

	StorageCity getCityLimit(@Param("cityId")Integer cityId);

	List<StorageCityConfig> getCityConfigList(@Param("cityId")Integer cityId, @Param("storeId")Integer storeId);

	public StorageStore getById(@Param("storeId")Integer storeId);

	List<CityProductDto> getProductByCityHqProductId(Integer hqProductId);

	List<SalesSpecDto> getProductSpecByHqProductId(Integer hqProductId);

	List<CityProductIds> getAllProductIds();

	List<CityProductIds> getProductIdsByCityId(Integer cityId);

	List<SalesSpecDto> getProductSpecByCityProductIds(Map<String, Object> param);
	

}
