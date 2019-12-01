package com.dailuobo.ic.dto.category.front;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * 前台类目关联的商品
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FrontCategoryRelatedCityProductDTO implements Serializable {
    private static final long serialVersionUID = -4759182268028858236L;

    /**
     * 主键Id
     */
    private BigInteger id;

    /**
     * 关联Id
     */
    private BigInteger relationId;

    /**
     * 城市Id
     */
    private Integer cityId;

    /**
     * 城市商品id
     */
    private Integer cityProductId;
    /**
     * 商品编号
     */
    private String productNo;
    /**
     * 城市商品展示名称
     */
    private String productShowName;


    private String productName;


    private String L1L2Names;

    /**
     * 图标链接
     */
    private String productIcon;

    /**
     * 展示序列
     */
    private Integer displayOrder;
}
