package com.dailuobo.api.domain.entity;

import java.io.Serializable;

/**
 * Created by Aministartor on 2016/12/1.
 */
public class HisSales implements Serializable {
    private static final long serialVersionUID = 1L;
    String productNo;
    String productName;
    String showName;
    String storeName;
    Float realPrice;
    Float avgPrice;
    String avgUnit;
    Integer unitType;
    Integer threshold;
    Integer packageMaxWeight;
    Integer changeFlag;
    Integer packageQuantity;
    String hisTime;
    Integer cityProductId;
    Integer storeId;
    Integer cityId;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Float getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Float realPrice) {
        this.realPrice = realPrice;
    }

    public Float getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Float avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getAvgUnit() {
        return avgUnit;
    }

    public void setAvgUnit(String avgUnit) {
        this.avgUnit = avgUnit;
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getPackageMaxWeight() {
        return packageMaxWeight;
    }

    public void setPackageMaxWeight(Integer packageMaxWeight) {
        this.packageMaxWeight = packageMaxWeight;
    }

    public Integer getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(Integer changeFlag) {
        this.changeFlag = changeFlag;
    }

    public Integer getPackageQuantity() {
        return packageQuantity;
    }

    public void setPackageQuantity(Integer packageQuantity) {
        this.packageQuantity = packageQuantity;
    }

    public String getHisTime() {
        return hisTime;
    }

    public void setHisTime(String hisTime) {
        this.hisTime = hisTime;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }
}
