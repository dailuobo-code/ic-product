package com.mallcai.domain.category.front.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FrontCategoryAssociatedSaveRequest implements Serializable {
    private static final long serialVersionUID = 4596731653285255597L;

    /**
     * 类目ID
     */
    private Long categoryId;

    /**
     * 关联类型'关联类型 1.后台类目，2.商品'
     */
    private Integer associatedType;

    /**
     * 关联数据
     */
    private List<FrontCategoryAssociatedItemRequest> associatedItemRequestList;

    /**
     * 操作人
     */
    private Integer operatorId;
}
