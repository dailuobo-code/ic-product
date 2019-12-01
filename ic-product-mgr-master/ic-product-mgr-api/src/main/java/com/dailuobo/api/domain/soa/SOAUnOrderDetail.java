package com.dailuobo.api.domain.soa;

import java.io.Serializable;


public class SOAUnOrderDetail implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -399894322698827908L;
	private Integer id;
    private String prodShowName;
    private Double price;
    private Integer actualPrice;
    private Integer actualWeight;
    private Integer productNum;
    private Integer starNum;
    private String productNo;
    private Integer pickupOrder;
    private Integer cityProductId;
    private Double refundPrice;
    private String phone;
    private String packageQuantity;
    private String avgUnit;
    
    
	public String getPackageQuantity() {
		return packageQuantity;
	}
	public void setPackageQuantity(String packageQuantity) {
		this.packageQuantity = packageQuantity;
	}
	public String getAvgUnit() {
		return avgUnit;
	}
	public void setAvgUnit(String avgUnit) {
		this.avgUnit = avgUnit;
	}
	public Integer getActualWeight() {
		return actualWeight;
	}
	public void setActualWeight(Integer actualWeight) {
		this.actualWeight = actualWeight;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Double getRefundPrice() {
		return refundPrice;
	}
	public void setRefundPrice(Double refundPrice) {
		this.refundPrice = refundPrice;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProdShowName() {
		return prodShowName;
	}
	public void setProdShowName(String prodShowName) {
		this.prodShowName = prodShowName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Integer getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(Integer actualPrice) {
		this.actualPrice = actualPrice;
	}
	public Integer getProductNum() {
		return productNum;
	}
	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}
	public Integer getStarNum() {
		return starNum;
	}
	public void setStarNum(Integer starNum) {
		this.starNum = starNum;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public Integer getPickupOrder() {
		return pickupOrder;
	}
	public void setPickupOrder(Integer pickupOrder) {
		this.pickupOrder = pickupOrder;
	}
	public Integer getCityProductId() {
		return cityProductId;
	}
	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}
    
    
}
