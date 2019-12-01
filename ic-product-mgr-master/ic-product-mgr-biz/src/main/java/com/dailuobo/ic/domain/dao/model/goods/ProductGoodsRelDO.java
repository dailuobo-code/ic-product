package com.dailuobo.ic.domain.dao.model.goods;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品货品关联关系DO
 *
 * @author lgh
 * @date 2019/9/29
 */
@Data
public class ProductGoodsRelDO {

    /** 关联记录id **/
    private Integer id;

    /** 城市id */
    private Integer cityId;

    /** 城市商品id */
    private Integer cityProductId;

    /** 货品编号 **/
    private String goodsId;

    /** 货品名称 **/
    private String goodsName;

    /** 货品计量单位 **/
    private String goodsUnit;

    /** 货品关联数量 **/
    private BigDecimal relNum;

    /** 创建用户Id **/
    private Integer createUserId;

    /** 更新用户id **/
    private Integer updateUserId;

    /** 创建时间 **/
    private Date    createTime;

    /** 更新时间 **/
    private Date    updateTime;

    /** 是否关联标识 **/
    private Integer isRel;

    /** 货品存储方式 **/
    private Integer keepType;

    /** 是否标品 **/
    private Integer isStandard;

}
