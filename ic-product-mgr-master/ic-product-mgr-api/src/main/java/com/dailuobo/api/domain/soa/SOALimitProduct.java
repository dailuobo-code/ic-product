package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SOALimitProduct.
 */
public class SOALimitProduct implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7669012616464933018L;
	
	/** The user id. */
	private Integer userId;
	
	/** The city id. */
	private Integer cityId;
	
	/** The product id. */
	private Integer productId;
	
	/** The spec id. */
	private Integer specId;
	
	/** The threshold. */
	private Integer threshold;
	
	/** The limit time. */
	private String limitTime;
	
	/** The purchase num. */
	private Integer purchaseNum;
	
	/** The order id. */
	private String orderId;
	
	/** The store id. */
	private Integer storeId;
	
	/**
	 * 获取  userId.
	 *
	 * @return userId
	 */
	public Integer getUserId() {
		return userId;
	}
	
	/**
	 * 设置 userId.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取  cityId.
	 *
	 * @return cityId
	 */
	public Integer getCityId() {
		return cityId;
	}
	
	/**
	 * 设置 cityId.
	 *
	 * @param cityId the new city id
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	/**
	 * 获取  threshold.
	 *
	 * @return threshold
	 */
	public Integer getThreshold() {
		return threshold;
	}
	
	/**
	 * 设置 threshold.
	 *
	 * @param threshold the new threshold
	 */
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}
	
	/**
	 * 获取  limitTime.
	 *
	 * @return limitTime
	 */
	public String getLimitTime() {
		return limitTime;
	}
	
	/**
	 * 设置 limitTime.
	 *
	 * @param limitTime the new limit time
	 */
	public void setLimitTime(String limitTime) {
		this.limitTime = limitTime;
	}
	
	/**
	 * 获取  purchaseNum.
	 *
	 * @return purchaseNum
	 */
	public Integer getPurchaseNum() {
		return purchaseNum;
	}
	
	/**
	 * 设置 purchaseNum.
	 *
	 * @param purchaseNum the new purchase num
	 */
	public void setPurchaseNum(Integer purchaseNum) {
		this.purchaseNum = purchaseNum;
	}

	/**
	 * 获取  productId.
	 *
	 * @return productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * 设置 productId.
	 *
	 * @param productId the new product id
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * 获取  specId.
	 *
	 * @return specId
	 */
	public Integer getSpecId() {
		return specId;
	}

	/**
	 * 设置 specId.
	 *
	 * @param specId the new spec id
	 */
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SOALimitProduct [\n userId=" + userId + "\n cityId=" + cityId + "\n productId=" + productId
				+ "\n specId=" + specId + "\n threshold=" + threshold + "\n limitTime=" + limitTime + "\n purchaseNum="
				+ purchaseNum + "\n orderId=" + orderId + "\n]";
	}

	/**
	 * 获取  orderId
	 * @return orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 设置 orderId
	 * @param orderId
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * 获取  storeId
	 * @return storeId
	 */
	public Integer getStoreId() {
		return storeId;
	}

	/**
	 * 设置 storeId
	 * @param storeId
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
}
