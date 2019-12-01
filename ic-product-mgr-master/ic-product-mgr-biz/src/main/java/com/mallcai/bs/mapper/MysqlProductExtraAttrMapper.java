package com.mallcai.bs.mapper;

import com.mallcai.bs.domain.ProductExtraAttr;
import com.mallcai.service.enums.ProductExtraAttrTypeEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wangshifeng
 * @date 2019-07-25 11:39
 */
public interface MysqlProductExtraAttrMapper{

    List<ProductExtraAttr> selectListByCityProductIds(@Param("cityProductIds") List<Integer> cityProductIds, @Param("attrType") ProductExtraAttrTypeEnum attrType);

    void insertBatch(List<ProductExtraAttr> attrDOs);

    void deleteByCityProductId(@Param("cityProductId") Integer cityProductId, @Param("attrType") ProductExtraAttrTypeEnum attrType);

    void insertHisBatch(@Param("extraAttrs") List<ProductExtraAttr> extraAttrs);
}
