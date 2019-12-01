package com.mallcai.biz.erp.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ErpPurchaseOrderDO {
    private Integer id;

    private Integer seq;

    private Integer cityId;

    private Integer warehouseId;

    private Integer planId;

    private String purchaseOrderNo;

    private Byte status;

    private String purchaseUserName;

    private Date purchaseDate;

    private Integer createUserId;

    private Date createTime;

    private Integer updateUserId;

    private Date updateTime;

    private BigDecimal totalPrice;

    private BigDecimal otherSku;

    private Integer supplierId;

    private Integer paymentMethod;

    private Integer orderType;

    private Integer warehousing;

    private Integer distributionArea;

    private String remark;

    private BigDecimal withdrawing;

    private BigDecimal realTotalPrice;

    private Integer repair;

    private Integer organizationId;

    private String realPurchaseDate;

    private Integer repairOrderId;

    private String instructionsId;

    private Integer purchaseTaskId;

    private Date planSalesDate;

    private Date planReceivedDate;

    private Byte planReceivedPeriod;

    private Byte needUnloadHelp;


    private List<ErpPurchaseOrderDetailDO> detailList;


}