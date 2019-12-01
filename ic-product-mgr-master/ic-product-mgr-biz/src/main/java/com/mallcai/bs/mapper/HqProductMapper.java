package com.mallcai.bs.mapper;

import com.dailuobo.api.domain.entity.HqProduct;
import com.dailuobo.api.domain.vo.CityProductIdAndMode;
import com.mallcai.bs.common.CustomDataSource;
import com.mallcai.bs.model.TblHqProductPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HqProductMapper {
    @CustomDataSource(CustomDataSource.GLOBAL)
    List<HqProduct> selectAll(Map<String, Object> param);

    @CustomDataSource(CustomDataSource.GLOBAL)
    void status(Map<String, Object> param);

    @CustomDataSource(CustomDataSource.GLOBAL)
    void add(HqProduct hqproduct);

    @CustomDataSource(CustomDataSource.GLOBAL)
    void duplicate(HqProduct hqproduct);

    @CustomDataSource(CustomDataSource.GLOBAL)
    void delete(List<Integer> ids);

    @CustomDataSource(CustomDataSource.GLOBAL)
    void update(HqProduct hq);

    @CustomDataSource(CustomDataSource.GLOBAL)
    void updateProductName(@Param("hqProductId") Integer hqProductId, @Param("newName") String newName);

    List<CityProductIdAndMode> getCityProductIds(List<Integer> hqProductIds);
    @CustomDataSource(CustomDataSource.GLOBAL)
    int exist(String productNo);
    @CustomDataSource(CustomDataSource.GLOBAL)
    int exist2(Map<String, Object> param);

    Integer getPointProduct(HqProduct hqProduct);

    HqProduct selectHqProductById(@Param("hqProductId") Integer hqProductId);

    List<HqProduct> selectHqProductByIds(@Param("hqProductIds") List<Integer> hqProductIds);

    void updateCityProductStatus(Map<String,Object> param);

    List<Integer> getClassifyIdsByProductNos(@Param("productNOs") List<String> productNOs);

    List<TblHqProductPO> selectHqProductBySelective(@Param("hqProductIds") List<Integer> hqProductIds);
}
