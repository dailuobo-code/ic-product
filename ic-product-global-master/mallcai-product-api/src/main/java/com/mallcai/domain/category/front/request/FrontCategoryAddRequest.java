package com.mallcai.domain.category.front.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class FrontCategoryAddRequest implements Serializable {
    private static final long serialVersionUID = 4695509643943144488L;

    /**
     * 父类目ID
     */
    private Long parentCategoryId;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 类目描述
     */
    private String categoryDesc;

    /**
     * 排序,数值
     */
    private Integer sort;

    /**
     * 操作人
     */
    private Integer operatorId;
}
