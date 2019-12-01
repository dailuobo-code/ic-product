package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private String storeName;
    private Date pickupTime;
    private Integer productNo;
    private String productName;
    private Integer salesAmount;
    private Integer salesReturnAmount;
    
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Date getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(Date pickupTime) {
		this.pickupTime = pickupTime;
	}
	public Integer getProductNo() {
		return productNo;
	}
	public void setProductNo(Integer productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getSalesAmount() {
		return salesAmount;
	}
	public void setSalesAmount(Integer salesAmount) {
		this.salesAmount = salesAmount;
	}
	public Integer getSalesReturnAmount() {
		return salesReturnAmount;
	}
	public void setSalesReturnAmount(Integer salesReturnAmount) {
		this.salesReturnAmount = salesReturnAmount;
	}
	
	
    
    
}
