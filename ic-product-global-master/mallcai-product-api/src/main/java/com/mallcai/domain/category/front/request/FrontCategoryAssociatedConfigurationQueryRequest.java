package com.mallcai.domain.category.front.request;

import com.mallcai.domain.RequestEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class FrontCategoryAssociatedConfigurationQueryRequest extends RequestEntity implements Serializable {
    private static final long serialVersionUID = 4596731653285255597L;

    /**
     * 类目ID
     */
    private Long categoryId;
}
