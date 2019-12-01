package com.mallcai.biz.category.dao.mapper;

import com.mallcai.domain.dataobject.category.FcatRelatedItemCntDO;
import com.mallcai.domain.dataobject.category.IcHqFcategoryRelation;
import com.mallcai.domain.dto.IcHqFcategoryRelationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IcHqFcategoryRelationCustomMapper {
    List<FcatRelatedItemCntDO> isAssociateBCategory(List<Long> fCatIds);

    List<FcatRelatedItemCntDO> isAssociateProduct(List<Long> fCatIds);
}