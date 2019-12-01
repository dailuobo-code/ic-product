package com.mallcai.domain.category.front.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class FrontCategoryEditRequest implements Serializable {
    private static final long serialVersionUID = 4695509643943144488L;

    /**
     * 类目ID
     */
    private Long categoryId;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 类目描述
     */
    private String categoryDesc;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 操作人
     */
    private Integer operatorId;
}
