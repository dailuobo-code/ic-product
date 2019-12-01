package com.dailuobo.ic.domain.dao.model.redis;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * 城市前台类目关联的后台类目或者商品
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CityCategoryModel implements Serializable {


    /**
     * 关联Id
     */
    private BigInteger relationId;
    /**
     * 前台二级类目Id
     */
    private BigInteger frontCategoryLv2Id;
    /**
     * 影响范围：1。全部门店，2 门店组，3 指定门店
     */
    private Integer effectScope;
    /**
     * 范围列表
     */
    private List<Integer> effectScopeList;

    /**
     * 关联类型 1，后台类目，2，城市商品
     */
    private Integer relationType;
    /**
     * 关联实体 商品/类目
     */
    private List<CategoryRelatedEntity> items;
}
