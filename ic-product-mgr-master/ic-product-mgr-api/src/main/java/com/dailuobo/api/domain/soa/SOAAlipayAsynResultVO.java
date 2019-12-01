package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SOAAlipayAsynResultVO.
 */
public class SOAAlipayAsynResultVO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -510533766098864464L;
	
	/** The notify id. */
	private String notifyId;
	
	/** The thirdparty order id. */
	private String thirdpartyOrderId;
	
	/** The thirdparty user id. */
	private String thirdpartyUserId;
	
	/** The pay time. */
	private String payTime;
	
	/** The asyn trade status. */
	private String asynTradeStatus;
	
	/** The out trade no. */
	private String outTradeNo;
	
	/** The order id. */
	private String orderId;
	
	/** The pay status. */
	private String payStatus;
	
	/** The update time. */
	private String updateTime;
	
	/** The order status. */
	private Integer orderStatus;
	
	/** The asyn pay status. */
	private String asynPayStatus;
	
	/** The pickup time. */
	private String pickupTime;
	
	/** The pickup code. */
	private String pickupCode;
	
	private Integer orderPrice;

	private Integer userId;
	
	public Integer getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Integer orderPrice) {
		this.orderPrice = orderPrice;
	}

	/**
	 * 获取  notifyId
	 * @return notifyId
	 */
	public String getNotifyId() {
		return notifyId;
	}

	/**
	 * 设置 notifyId
	 * @param notifyId
	 */
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	/**
	 * 获取  thirdpartyOrderId
	 * @return thirdpartyOrderId
	 */
	public String getThirdpartyOrderId() {
		return thirdpartyOrderId;
	}

	/**
	 * 设置 thirdpartyOrderId
	 * @param thirdpartyOrderId
	 */
	public void setThirdpartyOrderId(String thirdpartyOrderId) {
		this.thirdpartyOrderId = thirdpartyOrderId;
	}

	/**
	 * 获取  thirdpartyUserId
	 * @return thirdpartyUserId
	 */
	public String getThirdpartyUserId() {
		return thirdpartyUserId;
	}

	/**
	 * 设置 thirdpartyUserId
	 * @param thirdpartyUserId
	 */
	public void setThirdpartyUserId(String thirdpartyUserId) {
		this.thirdpartyUserId = thirdpartyUserId;
	}

	/**
	 * 获取  payTime
	 * @return payTime
	 */
	public String getPayTime() {
		return payTime;
	}

	/**
	 * 设置 payTime
	 * @param payTime
	 */
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	/**
	 * 获取  asynTradeStatus
	 * @return asynTradeStatus
	 */
	public String getAsynTradeStatus() {
		return asynTradeStatus;
	}

	/**
	 * 设置 asynTradeStatus
	 * @param asynTradeStatus
	 */
	public void setAsynTradeStatus(String asynTradeStatus) {
		this.asynTradeStatus = asynTradeStatus;
	}

	/**
	 * 获取  outTradeNo
	 * @return outTradeNo
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}

	/**
	 * 设置 outTradeNo
	 * @param outTradeNo
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
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
	 * 获取  payStatus
	 * @return payStatus
	 */
	public String getPayStatus() {
		return payStatus;
	}

	/**
	 * 设置 payStatus
	 * @param payStatus
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * 获取  updateTime
	 * @return updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置 updateTime
	 * @param updateTime
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取  orderStatus
	 * @return orderStatus
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 设置 orderStatus
	 * @param orderStatus
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * 获取  asynPayStatus
	 * @return asynPayStatus
	 */
	public String getAsynPayStatus() {
		return asynPayStatus;
	}

	/**
	 * 设置 asynPayStatus
	 * @param asynPayStatus
	 */
	public void setAsynPayStatus(String asynPayStatus) {
		this.asynPayStatus = asynPayStatus;
	}

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
