package com.mallcai.biz.erp.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ErpPurchaseOrderDetailDO {
    private Integer id;

    private Integer purchaseOrderId;

    private String purchaseOrderNo;

    private Integer cityProductId;

    private String productNo;

    private String showName;

    private BigDecimal purchaseWeight;

    private BigDecimal price;

    private BigDecimal purchasePrice;

    private BigDecimal adjustPurchaseWeight;

    private BigDecimal adjustPurchasePrice;

    private String remark;

    private String avgUnit;

    private String numUnit;

    private Byte unitType;

    private Byte packageQuantity;

    private String showName2;

    private Integer packageMaxWeight;

    private BigDecimal convertedQuantity;

    private BigDecimal collectWeight;

    private BigDecimal rejectionWeight;

    private BigDecimal returnWeight;

    private BigDecimal realPurchasePrice;

    private BigDecimal adjustPrice;

    private Byte status;

    private Integer createUserId;

    private Date createTime;

    private Integer updateUserId;

    private Date updateTime;

    private BigDecimal actualLossRate;

    private BigDecimal fixedLossRate;

    private BigDecimal settleQty;

    private Byte fuxiliaryUnits;

    private BigDecimal fuxiliaryUnitsQty;

    private BigDecimal fuxiliaryOrderNum;

    private Boolean isFuxiliary;

}