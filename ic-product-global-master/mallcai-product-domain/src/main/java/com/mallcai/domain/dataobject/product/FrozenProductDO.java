package com.mallcai.domain.dataobject.product;

import lombok.Data;

import java.io.Serializable;

/**
 * 冻品接口返回数据
 * @author tianjunwei
 */
@Data
public class FrozenProductDO implements Serializable {

    /**
     * 城市id
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
     * 商品 bar code
     */
    private String barCode;

    /**
     * 商品展示名称
     */
    private String showName;

    /**
     * 商品单位
     */
    private String unit;
}
