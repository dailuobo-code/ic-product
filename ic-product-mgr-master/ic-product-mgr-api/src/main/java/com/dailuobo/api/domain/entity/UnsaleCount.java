package com.dailuobo.api.domain.entity;

import java.io.Serializable;

public class UnsaleCount implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer cityId;
	private Integer storeId;
	private String storeName;
	private Integer cityProductId;
	private String productNo;
	private String prodShowName;
	private Integer actualPrice;
	private Integer num;
	private String pickupTime;
	private String classifyName;
	
	public String getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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
	public String getProdShowName() {
		return prodShowName;
	}
	public void setProdShowName(String prodShowName) {
		this.prodShowName = prodShowName;
	}
	public Integer getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(Integer actualPrice) {
		this.actualPrice = actualPrice;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
}
