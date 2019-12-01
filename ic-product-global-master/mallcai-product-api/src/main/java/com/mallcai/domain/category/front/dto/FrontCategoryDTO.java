package com.mallcai.domain.category.front.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class FrontCategoryDTO implements Serializable {
    private static final long serialVersionUID = 2475720366756478518L;

    /**
     * 前台类目编号
     */
    private Long categoryId;

    /**
     * 父类目编号
     */
    private Long parentCategoryId;

    /**
     * 前台类目名称
     */
    private String categoryName;

    /**
     * 前台类目描述
     */
    private String categoryDesc;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 生效状态,1:禁用,2:启用
     */
    private Integer effectiveStatus;

    /**
     * 关联状态,1:未关联,2:已关联
     */
    private Integer associatedStatus;

    /**
     * 类目层级,从1开始
     */
    private Integer categoryLevel;

    /**
     * 子类目
     */
    private List<FrontCategoryDTO> childFrontCategorys = new ArrayList<>();

    /**
     * 是否失效,true:失效
     */
    private boolean disabled;
}
