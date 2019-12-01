package com.mallcai.domain.category.dto;

import com.mallcai.domain.BaseEntity;
import com.mallcai.domain.enums.CategoryLevelEnum;
import com.mallcai.domain.enums.EffectScopeEnum;
import com.mallcai.domain.enums.RelationStatusEnum;
import com.mallcai.domain.enums.RelationTypeEnum;

import java.io.Serializable;
import java.util.List;

public class FrontCategoryDTO extends BaseEntity implements Serializable {
    private Long id;
    /**
     * 级别
     */
    private CategoryLevelEnum level;
    /**
     * 父级id
     */
    private Integer parentId;
    /**
     * 展示序列
     */
    private Integer displayOrder;
    /**
     * 描述
     */
    private String description;

    /**
     * 关联状态 ： 1。禁用 2 。启用
     */
    private RelationStatusEnum status;

    /**
     * 关联类型 ：1。类目 2。商品
     */
    private RelationTypeEnum relationType;


    /**
     * 生效范围
     */
    private EffectScopeEnum effectScope;


    /**
     * 生效范围的门店 todo，指定门店，要不要跟指定的组，分类存储？
     */
    private List<Integer>  effectEntityIdList;


}
