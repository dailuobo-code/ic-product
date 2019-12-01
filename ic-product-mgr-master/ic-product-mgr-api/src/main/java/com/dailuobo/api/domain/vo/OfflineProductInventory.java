package com.dailuobo.api.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 线下商品可用库存信息
 */
public class OfflineProductInventory implements Serializable {
    private static final long serialVersionUID = 1;

    private Integer cityProductId;
    private String showName;
    private String productNo;
    private Integer available;
    private BigDecimal auditPrice;

    public BigDecimal getAuditPrice() {
        return auditPrice;
    }

    public void setAuditPrice(BigDecimal auditPrice) {
        this.auditPrice = auditPrice;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
