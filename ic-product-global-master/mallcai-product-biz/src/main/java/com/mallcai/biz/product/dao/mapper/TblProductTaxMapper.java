package com.mallcai.biz.product.dao.mapper;

import com.mallcai.biz.product.model.HqProductTax;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TblProductTaxMapper {

    List<HqProductTax> getProductTaxesByIds(@Param("ids") List<Integer> ids);
    List<HqProductTax> getProductTaxesByClassifyCode(@Param("codes") List<String> code);

}