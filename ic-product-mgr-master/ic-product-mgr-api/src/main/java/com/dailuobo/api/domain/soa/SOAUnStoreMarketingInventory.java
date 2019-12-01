package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAUnStoreMarketingInventory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7877200650538382410L;
	private Integer undSpecId;
    private Integer cityProductId;
    private String productNo;
    private String hqProductName;
    private String showName;
    private String c1Name;
    private String c2Name;
    private String cityProductIcon;
    private Integer storeId;
    private String storeName;
    private Integer available;
    private Integer threshold;
    private Integer availableBase;
    
	private Integer createUserId;
	private Integer updateUserId;
	private Integer delta;
	private Integer totalAvailable;
	
	public Integer getUndSpecId() {
		return undSpecId;
	}
	public void setUndSpecId(Integer undSpecId) {
		this.undSpecId = undSpecId;
	}
	public Integer getDelta() {
		return delta;
	}
	public void setDelta(Integer delta) {
		this.delta = delta;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getTotalAvailable() {
		return totalAvailable;
	}

	public void setTotalAvailable(Integer totalAvailable) {
		this.totalAvailable = totalAvailable;
	}
}
