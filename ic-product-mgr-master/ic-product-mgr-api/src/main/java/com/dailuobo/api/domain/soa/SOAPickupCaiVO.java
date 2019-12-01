package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SOAPickupCaiVO.
 */
public class SOAPickupCaiVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 688167142071191060L;
	
	/** The pickup time. */
	private String pickupTime;
	
	/** The pickup start time. */
	private String pickupStartTime;
	
	/** The pickup end time. */
	private String pickupEndTime;
	
	/** The pickup code. */
	private String pickupCode;
	
	/** The order id. */
	private String orderId;
	
	/** The store id. */
	private Integer storeId;

	/**
	 * 获取  pickupTime
	 * @return pickupTime
	 */
	public String getPickupTime() {
		return pickupTime;
	}

	/**
	 * 设置 pickupTime
	 * @param pickupTime
	 */
	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}

	/**
	 * 获取  pickupStartTime
	 * @return pickupStartTime
	 */
	public String getPickupStartTime() {
		return pickupStartTime;
	}

	/**
	 * 设置 pickupStartTime
	 * @param pickupStartTime
	 */
	public void setPickupStartTime(String pickupStartTime) {
		this.pickupStartTime = pickupStartTime;
	}

	/**
	 * 获取  pickupEndTime
	 * @return pickupEndTime
	 */
	public String getPickupEndTime() {
		return pickupEndTime;
	}

	/**
	 * 设置 pickupEndTime
	 * @param pickupEndTime
	 */
	public void setPickupEndTime(String pickupEndTime) {
		this.pickupEndTime = pickupEndTime;
	}

	/**
	 * 获取  pickupCode
	 * @return pickupCode
	 */
	public String getPickupCode() {
		return pickupCode;
	}

	/**
	 * 设置 pickupCode
	 * @param pickupCode
	 */
	public void setPickupCode(String pickupCode) {
		this.pickupCode = pickupCode;
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
