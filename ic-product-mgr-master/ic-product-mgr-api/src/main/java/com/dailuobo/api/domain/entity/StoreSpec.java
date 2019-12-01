package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StoreSpec implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer cityProductId;
    private String productNo;
    private String hqProductName;
    private String showName;
    private String c1Name;
    private String c2Name;
    private String cityProductIcon;
    private Double disablePrice;
    private Double realPrice;
    private Double avgPrice;
    private String avgUnit;
    private Byte unitType;
    private Integer threshold;
    private Integer packageMaxWeight;
    private Byte changeFlag;
    private Integer packageQuantity;
    private String weightUnit;
    private String numUnit;
    private Integer storeId;
    private String storeName;
    private Double advisePrice;
    private String cityKeyword;
    private String storeKeyword;
    private String cityIconTip;
    private String storeIconTip;
    private Integer available;
    private Integer thresholdForAlarm;
    private Integer deliveryMode;
    private Double delta;
    private Integer upperLimit;
    private Integer lowerLimit;
    private Integer merchantId;//商家ID
    private String merchantName;//商家名称
    private Double avgPriceFloor;
    private Double avgPriceCeiling;
    private Long skuId;
    private Long itemId;

    /**
     * 0 非多规格  1 多规格
     * 是否是多规格商品
     */
    public Integer getIsMultiProduct() {
        if(itemId==null&&skuId==null){
            return 0;
        }
        if(itemId!=null&&itemId.intValue()>0){
            return 1;
        }
        if(skuId!=null&&skuId.intValue()>0){
            return 1;
        }
        return 0;
    }


    private Integer isMultiProduct;


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

    public Double getDisablePrice() {
        return disablePrice;
    }

    public void setDisablePrice(Double disablePrice) {
        this.disablePrice = disablePrice;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getAvgUnit() {
        return avgUnit;
    }

    public void setAvgUnit(String avgUnit) {
        this.avgUnit = avgUnit;
    }

    public Byte getUnitType() {
        return unitType;
    }

    public void setUnitType(Byte unitType) {
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

    public Byte getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(Byte changeFlag) {
        this.changeFlag = changeFlag;
    }

    public Integer getPackageQuantity() {
        return packageQuantity;
    }

    public void setPackageQuantity(Integer packageQuantity) {
        this.packageQuantity = packageQuantity;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getNumUnit() {
        return numUnit;
    }

    public void setNumUnit(String numUnit) {
        this.numUnit = numUnit;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Double getAdvisePrice() {
        return advisePrice;
    }

    public void setAdvisePrice(Double advisePrice) {
        this.advisePrice = advisePrice;
    }

    public String getCityKeyword() {
        return cityKeyword;
    }

    public void setCityKeyword(String cityKeyword) {
        this.cityKeyword = cityKeyword;
    }

    public String getStoreKeyword() {
        return storeKeyword;
    }

    public void setStoreKeyword(String storeKeyword) {
        this.storeKeyword = storeKeyword;
    }

    public String getCityIconTip() {
        return cityIconTip;
    }

    public void setCityIconTip(String cityIconTip) {
        this.cityIconTip = cityIconTip;
    }

    public String getStoreIconTip() {
        return storeIconTip;
    }

    public void setStoreIconTip(String storeIconTip) {
        this.storeIconTip = storeIconTip;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getThresholdForAlarm() {
        return thresholdForAlarm;
    }

    public void setThresholdForAlarm(Integer thresholdForAlarm) {
        this.thresholdForAlarm = thresholdForAlarm;
    }

    public Integer getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Integer deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public Double getDelta() {
        return delta;
    }

    public void setDelta(Double delta) {
        this.delta = delta;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Integer getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Integer lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Double getAvgPriceFloor() {
        return avgPriceFloor;
    }

    public void setAvgPriceFloor(Double avgPriceFloor) {
        this.avgPriceFloor = avgPriceFloor;
    }

    public Double getAvgPriceCeiling() {
        return avgPriceCeiling;
    }

    public void setAvgPriceCeiling(Double avgPriceCeiling) {
        this.avgPriceCeiling = avgPriceCeiling;
    }


    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
}
