package com.dailuobo.ic.dto.category.front;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FrontCategoryRelatedItemDTO implements Serializable {

    /**
     * 主键Id
     */
    private BigInteger frontCategoryId;


    private BigInteger parentCategoryId;

    private String frontCategoryName;

    private String parentFrontCategoryName;


    /**
     * 关联Id
     */
    private BigInteger relationId;


    /**
     * 关联类型
     */
    private Integer relationType;

    /**
     * 城市Id
     */
    private Integer cityId;


    /**
     * 影响范围
     */
    private Integer effectScope;


    private List<Integer> effectScopeList;


    /**
     * 关联的后台类目
     */
    private List<FCategoryRelatedBCategoryDTO> relatedBackendCategoryList;


    /**
     * 关联的后台类目
     */
    private List<FrontCategoryRelatedCityProductDTO> relatedCityProductList;

}
