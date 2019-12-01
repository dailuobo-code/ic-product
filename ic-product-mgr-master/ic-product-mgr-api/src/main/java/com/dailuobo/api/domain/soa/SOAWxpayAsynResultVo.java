package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SOAWxpayAsynResultVo.
 */
public class SOAWxpayAsynResultVo implements Serializable {
	
	private static final long serialVersionUID = -138728352334701174L;

	/** The out trade no. */
	private String outTradeNo;
	
	/** The order id. */
	private String orderId;
	
	/** The thirdparty order id. */
	private String thirdpartyOrderId;
	
	/** The thirdparty user id. */
	private String thirdpartyUserId;
	
	/** The pay time. */
	private String payTime;
	
	/** The pay status. */
	private String payStatus;
	
	/** The err msg. */
	private String errMsg;
	
	/** The query trade status. */
	private String asynTradeStatus;
	
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
	 * 获取  outTradeNo.
	 *
	 * @return outTradeNo
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}
	
	/**
	 * 设置 outTradeNo.
	 *
	 * @param outTradeNo the new out trade no
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	/**
	 * 获取  orderId.
	 *
	 * @return orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	
	/**
	 * 设置 orderId.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 获取  thirdpartyOrderId.
	 *
	 * @return thirdpartyOrderId
	 */
	public String getThirdpartyOrderId() {
		return thirdpartyOrderId;
	}
	
	/**
	 * 设置 thirdpartyOrderId.
	 *
	 * @param thirdpartyOrderId the new thirdparty order id
	 */
	public void setThirdpartyOrderId(String thirdpartyOrderId) {
		this.thirdpartyOrderId = thirdpartyOrderId;
	}
	
	/**
	 * 获取  thirdpartyUserId.
	 *
	 * @return thirdpartyUserId
	 */
	public String getThirdpartyUserId() {
		return thirdpartyUserId;
	}
	
	/**
	 * 设置 thirdpartyUserId.
	 *
	 * @param thirdpartyUserId the new thirdparty user id
	 */
	public void setThirdpartyUserId(String thirdpartyUserId) {
		this.thirdpartyUserId = thirdpartyUserId;
	}
	
	/**
	 * 获取  payTime.
	 *
	 * @return payTime
	 */
	public String getPayTime() {
		return payTime;
	}
	
	/**
	 * 设置 payTime.
	 *
	 * @param payTime the new pay time
	 */
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	
	/**
	 * 获取  payStatus.
	 *
	 * @return payStatus
	 */
	public String getPayStatus() {
		return payStatus;
	}
	
	/**
	 * 设置 payStatus.
	 *
	 * @param payStatus the new pay status
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	/**
	 * 获取  errMsg.
	 *
	 * @return errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}
	
	/**
	 * 设置 errMsg.
	 *
	 * @param errMsg the new err msg
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	/**
	 * 获取  updateTime.
	 *
	 * @return updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * 设置 updateTime.
	 *
	 * @param updateTime the new update time
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * 获取  orderStatus.
	 *
	 * @return orderStatus
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}
	
	/**
	 * 设置 orderStatus.
	 *
	 * @param orderStatus the new order status
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
	 * 获取  asynPayStatus.
	 *
	 * @return asynPayStatus
	 */
	public String getAsynPayStatus() {
		return asynPayStatus;
	}
	
	/**
	 * 设置 asynPayStatus.
	 *
	 * @param asynPayStatus the new asyn pay status
	 */
	public void setAsynPayStatus(String asynPayStatus) {
		this.asynPayStatus = asynPayStatus;
	}
	
	/**
	 * 获取  pickupTime.
	 *
	 * @return pickupTime
	 */
	public String getPickupTime() {
		return pickupTime;
	}
	
	/**
	 * 设置 pickupTime.
	 *
	 * @param pickupTime the new pickup time
	 */
	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}
	
	/**
	 * 获取  pickupCode.
	 *
	 * @return pickupCode
	 */
	public String getPickupCode() {
		return pickupCode;
	}
	
	/**
	 * 设置 pickupCode.
	 *
	 * @param pickupCode the new pickup code
	 */
	public void setPickupCode(String pickupCode) {
		this.pickupCode = pickupCode;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
