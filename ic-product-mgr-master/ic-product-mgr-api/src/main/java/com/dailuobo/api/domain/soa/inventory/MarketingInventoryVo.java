package com.dailuobo.api.domain.soa.inventory;

import java.io.Serializable;

/**
 * Created by Cowboy on 5/14/2016.
 */
public class MarketingInventoryVo implements Serializable {
    private static final long serialVersionUID = -4204100760666154808L;
    private Integer available;
    private Integer threshold;
    private Integer cityProductId;
    private Integer storeId;
    private String 	showName;
    private String 	productNo;
    private String 	storeName;
    private Integer cityId;
    private String 	cityName;
    private Byte 	deliveryMode;
    private String 	phoneStr;
    private String 	emailStr;
    private Integer warehouseId;
    

    public Integer getAvailable() {
        return available;
    }

    public String getPhoneStr() {
		return phoneStr;
	}

	public void setPhoneStr(String phoneStr) {
		this.phoneStr = phoneStr;
	}

	public String getEmailStr() {
		return emailStr;
	}

	public void setEmailStr(String emailStr) {
		this.emailStr = emailStr;
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

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Byte getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Byte deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }
}
