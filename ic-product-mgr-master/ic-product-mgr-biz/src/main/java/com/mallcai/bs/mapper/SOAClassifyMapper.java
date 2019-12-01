package com.mallcai.bs.mapper;

import com.mallcai.bs.common.CustomDataSource;
import com.dailuobo.api.domain.soa.SOAHqProduct;
import com.dailuobo.api.domain.soa.city.ProductClassifyRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SOAClassifyMapper {

	List<ProductClassifyRel> getProductClassifyRel();


	List<ProductClassifyRel> getProductClassifyRelList(@Param("cityId")Integer cityId,@Param("beforeClassifyId")Integer beforeClassifyId,@Param("afterClassifyId")Integer afterClassifyId);


	/**
	 * @param cityProductId  城市商品Id
	 * @param cityId 城市Id
	 * @return
	 */
	ProductClassifyRel  loadCityProductClassifyRel(@Param("cityProductId")Integer cityProductId,@Param("cityId")Integer cityId);

	
	
	
	List<ProductClassifyRel> findCityProduct(@Param("hpProductIdList") List<Integer> hpProductIdList);
	
	
	@CustomDataSource(CustomDataSource.GLOBAL)
	List<ProductClassifyRel> findProductClassify();

	SOAHqProduct getClassifyByCityProductId(@Param("cityProductId") Integer cityProductId);

	List<SOAHqProduct> getClassifyByCityProductIds(@Param("cityProductIds") List<Integer> cityProductIds);

}
