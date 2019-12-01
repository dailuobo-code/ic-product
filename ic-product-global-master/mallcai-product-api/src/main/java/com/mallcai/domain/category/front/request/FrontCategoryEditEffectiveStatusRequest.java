package com.mallcai.domain.category.front.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class FrontCategoryEditEffectiveStatusRequest implements Serializable {
    private static final long serialVersionUID = 4695509643943144488L;

    /**
     * 类目ID
     */
    private Long categoryId;

    /**
     * 生效状态,1:未启用,2:启用中
     */
    private Integer effectiveStatus;

    /**
     * 操作人
     */
    private Integer operatorId;
}
