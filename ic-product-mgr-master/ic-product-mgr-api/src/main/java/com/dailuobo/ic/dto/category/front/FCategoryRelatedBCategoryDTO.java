package com.dailuobo.ic.dto.category.front;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 前台类目关联的后台类目列表
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FCategoryRelatedBCategoryDTO implements Serializable {
    private static final long serialVersionUID = -8028136019800803720L;

    private BigInteger id;
    /**
     * 后台二级类目Id;
     */
    private Integer categoryId;

    /**
     * 关联Id
     */
    private BigInteger relationId;

    /**
     * 城市id
     */
    private Integer cityId;
    /**
     * 后台类目展示名称
     */
    private String categoryShowName;

    /**
     * 展示序列
     */
    private Integer displayOrder;
}
