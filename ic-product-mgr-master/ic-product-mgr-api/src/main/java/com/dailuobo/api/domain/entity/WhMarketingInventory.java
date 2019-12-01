package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WhMarketingInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer cityProductId;
    private String productNo;
    private String hqProductName;
    private String showName;
    private String c1Name;
    private String c2Name;
    private String cityProductIcon;
    private Integer warehouseId;
    private String warehouseName;
    private Integer available;
    private Integer threshold;
    private Integer availableBase;

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getHqProductName() {
        return hqProductName;
    }

    public void setHqProductName(String hqProductName) {
        this.hqProductName = hqProductName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getC1Name() {
        return c1Name;
    }

    public void setC1Name(String c1Name) {
        this.c1Name = c1Name;
    }

    public String getC2Name() {
        return c2Name;
    }

    public void setC2Name(String c2Name) {
        this.c2Name = c2Name;
    }

    public String getCityProductIcon() {
        return cityProductIcon;
    }

    public void setCityProductIcon(String cityProductIcon) {
        this.cityProductIcon = cityProductIcon;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getAvailableBase() {
        return availableBase;
    }

    public void setAvailableBase(Integer availableBase) {
        this.availableBase = availableBase;
    }
}
