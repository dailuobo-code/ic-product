package com.mallcai.domain.category.front.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FrontCategoryAssociatedDTO implements Serializable {
    private static final long serialVersionUID = 8398165784498861890L;

    private Long frontCategoryId;

    private String frontCategoryName;

    private Long frontParentCategoryId;

    private String frontParentCategoryName;

    private Integer associatedType;

}
