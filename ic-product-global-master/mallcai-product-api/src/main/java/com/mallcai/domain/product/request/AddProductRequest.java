package com.mallcai.domain.product.request;

import com.mallcai.domain.enums.AuditStatus;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class AddProductRequest implements Serializable {

    private static final long serialVersionUID = -246403372229826116L;


    /**
     * 总部商品Id
     */
    private Integer hqProductId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品编号
     */
    private String productNo;
    /**
     * 二级类目ID
     */
    private Integer categoryId;
    /**
     * 商品描述
     */
    private String remark;
    /**
     * 商品icon
     */
    private String productIcon;
    /**
     * 商品图片
     */
    private List<String> productPics;
    /**
     * 配送方式,1:门店自提,2:送货上门
     */
    private Integer deliveryMode;
    /**
     * 商品条形码
     */
    private String barCode;
    /**
     * 储存方式,1-常温 2-冷藏 3-冷冻
     */
    private Integer keepType;
    /**
     * 保持期
     */
    private Integer qualityTime;
    /**
     * 是否标品 0-未维护的 1-是 2-否
     */
    private Integer isStandard;
    /**
     * 门店取货标签
     */
    private Integer pickClassify;
    /**
     * 税率
     */
    private BigDecimal productRate;
    /**
     * 长,单位m
     */
    private String length;
    /**
     * 宽,单位m
     */
    private String width;
    /**
     * 高,单位m
     */
    private String high;
    /**
     * 商品类型 1:普通商品，2:新人专享
     */
    private Integer productType;

    /**
     * 操作用户ID
     */
    private Integer userId;

    /**
     * 税收分类编码
     */
    private Integer productTaxId;

    /**
     * 是否走采销预测
     * 0表示否 1表示是
     */
    private Integer isForecast;

    /**
     * 上新类型
     * 0表示常规品  1表示新品
     */
    private Integer newArrivalType;

    /**
     * 售卖季节性
     */
    private Integer seasonal;

    /**
     * 发货时间
     * 配送方式,2:送货上门 值不可为空
     */
    private Integer arrivalDay;

    private Long itemId;


    private Long skuId;

    /**
     * 审核状态
     */
    private AuditStatus auditStatus;
}
