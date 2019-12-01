package com.dailuobo.api.domain.vo;

import com.dailuobo.api.domain.entity.StoreNoSale;

import java.io.Serializable;

public class NoSaleVo extends StoreNoSale implements Serializable {
	private static final long serialVersionUID = 1;
	private String storeName;
	private String createUsername;
	private String updateUsername;
	private String showName;
	private String productNo;
	
	
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
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
	public String getCreateUsername() {
		return createUsername;
	}
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}
	public String getUpdateUsername() {
		return updateUsername;
	}
	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
	}
	
	

}
