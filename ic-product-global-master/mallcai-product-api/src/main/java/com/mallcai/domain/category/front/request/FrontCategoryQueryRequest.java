package com.mallcai.domain.category.front.request;

import com.mallcai.domain.RequestEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class FrontCategoryQueryRequest implements Serializable {
    private static final long serialVersionUID = 4695509643943144488L;

    /**
     * 父类目id
     */
    private Long parentCategoryId;

    /**
     * 类目id
     */
    private Long categoryId;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 生效状态,1:未启用,2:启用中
     */
    private Integer effectiveStatus;

    /**
     * 关联状态,1:未关联,2:已关联
     */
    private Integer associatedStatus;

    /**
     * 类目层级,从1开始计数
     */
    private Integer categoryLevel;
}
