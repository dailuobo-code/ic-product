package com.dailuobo.api.domain.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class HqProductLocalize implements Serializable {

    private static final long serialVersionUID = -1703032133963607564L;

    private Integer hqProductId;
    private Integer cityProductId;
    private String productNo;
    private String hqProductName;
    private String remark;
    private Integer status;
    private String hqProductPic;
    private String hqProductIcon;
    private Byte deliveryMode;
    private String barCode;
    private Integer keepType;
    private Integer qualityTime;
    private String length;
    private String wide;
    private String high;
    private Integer goodsType;
    private Integer cityId;
    private String cityProductIcon;
    private String cityProductPic;
    private int isShare;//是否共享
    private Integer merchantId;//商家ID
    private Integer userId;
    private String productDesc;

    /**
     * 是否走采销预测
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
}
