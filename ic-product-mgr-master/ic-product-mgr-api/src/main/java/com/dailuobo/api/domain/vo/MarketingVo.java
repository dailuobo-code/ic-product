package com.dailuobo.api.domain.vo;

import java.io.Serializable;

public class MarketingVo implements Serializable {
	private static final long serialVersionUID = 1;
	private Integer cityProductId;
	private String productNo;
	private String cityProductIcon;
	private String hqProductName;
	private String showName;
	private Integer isShare;
	private String c2Name;
	private String c1Name;
	private Integer availableBase;
	private Integer threshold;
	private Integer available;
	/**
	 * 在单量(2019-08-21新增)
	 */
	private Integer inQuantity;
	/**
	 * 分类1级类目,2级类目
	 */
	private String l1L2Names ;
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
	public String getCityProductIcon() {
		return cityProductIcon;
	}
	public void setCityProductIcon(String cityProductIcon) {
		this.cityProductIcon = cityProductIcon;
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
	public Integer getIsShare() {
		return isShare;
	}
	public void setIsShare(Integer isShare) {
		this.isShare = isShare;
	}
	public String getC2Name() {
		return c2Name;
	}
	public void setC2Name(String c2Name) {
		this.c2Name = c2Name;
	}
	public String getC1Name() {
		return c1Name;
	}
	public void setC1Name(String c1Name) {
		this.c1Name = c1Name;
	}
	public Integer getAvailableBase() {
		return availableBase;
	}
	public void setAvailableBase(Integer availableBase) {
		this.availableBase = availableBase;
	}
	public Integer getThreshold() {
		return threshold;
	}
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}

	public void setInQuantity(Integer inQuantity) {
		this.inQuantity = inQuantity;
	}

	public Integer getInQuantity() {
		return inQuantity;
	}

	public String getL1L2Names() {
		return l1L2Names;
	}

	public void setL1L2Names(String l1L2Names) {
		this.l1L2Names = l1L2Names;
	}
}
