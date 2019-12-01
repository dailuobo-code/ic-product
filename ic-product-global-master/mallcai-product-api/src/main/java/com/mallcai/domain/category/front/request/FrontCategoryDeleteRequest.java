package com.mallcai.domain.category.front.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class FrontCategoryDeleteRequest implements Serializable {
    private static final long serialVersionUID = 4695509643943144488L;

    /**
     * 类目ID
     */
    private Long categoryId;

    /**
     * 操作人
     */
    private Integer operatorId;

}
