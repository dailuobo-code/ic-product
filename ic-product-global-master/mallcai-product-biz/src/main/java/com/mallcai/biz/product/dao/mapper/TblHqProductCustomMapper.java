package com.mallcai.biz.product.dao.mapper;

import com.mallcai.domain.dataobject.product.TblHqProduct;
import com.mallcai.domain.product.request.HqProductVo;
import com.mallcai.domain.product.request.PddProductAttr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblHqProductCustomMapper {

    long delete(List<Integer> includeProductIds);

    void addHqProduct(HqProductVo hqProductVo);

    List<TblHqProduct> selectHqProductByIds(List<Long> productNos);

    void addPddProductAttr(PddProductAttr pddProductAttr);

    List<PddProductAttr> getPddProductAttrByPddGoodIds(@Param("list") List<String> goodIds);
}
