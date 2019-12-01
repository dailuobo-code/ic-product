package com.dailuobo.ic.api.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品货品关联关系VO
 *
 * @author lgh
 * @date 2019/9/29
 */
@Data
public class ProductGoodsRelVO extends BaseVO{

    /** 货品关联Id **/
    private Integer relId;

    /** 城市id **/
    private Integer cityId;

    /** 城市商品id **/
    private Integer cityProductId;

    /** 货品编号 **/
    private String goodsId;

    /** 货品名称 **/
    private String goodsName;

    /** 货品计量单位 **/
    private String goodsUnit;

    /** 货品关联数量 **/
    private BigDecimal relNum;

    /** 货品存储方式 **/
    private Integer keepType;

    /** 是否标品 **/
    private Integer isStandard;
}
