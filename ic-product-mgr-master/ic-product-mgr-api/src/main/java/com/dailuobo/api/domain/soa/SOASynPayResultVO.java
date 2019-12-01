package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SOASynPayResultVO.
 */
public class SOASynPayResultVO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5072921329251097271L;
	
	/** The pay order no. */
	private String payOrderNo;
	
	/** The order no. */
	private String orderNo;
	
	/** The pay status. */
	private String payStatus;
	
	/** The order status. */
	private String orderStatus;
	
	/** The syn trade status. */
	private String synTradeStatus;
	
	/** The update time. */
	private String updateTime;
	
	/** The trade no. */
	private String tradeNo;
	
	/** The pickup date. */
	private String pickupDate;
	
	/** The pay time. */
	private String payTime;
	
	private Integer payPrice; //zyc 添加于2018-03-20 用于新版本支付宝同步回调时保存订单金额
	
	
	public Integer getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(Integer payPrice) {
		this.payPrice = payPrice;
	}

	/**
	 * 获取  payOrderNo.
	 *
	 * @return payOrderNo
	 */
	public String getPayOrderNo() {
		return payOrderNo;
	}
	
	/**
	 * 设置 payOrderNo.
	 *
	 * @param payOrderNo the new pay order no
	 */
	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}
	
	/**
	 * 获取  orderNo.
	 *
	 * @return orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}
	
	/**
	 * 设置 orderNo.
	 *
	 * @param orderNo the new order no
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	 * 获取  orderStatus.
	 *
	 * @return orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	
	/**
	 * 设置 orderStatus.
	 *
	 * @param orderStatus the new order status
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	/**
	 * 获取  synTradeStatus.
	 *
	 * @return synTradeStatus
	 */
	public String getSynTradeStatus() {
		return synTradeStatus;
	}
	
	/**
	 * 设置 synTradeStatus.
	 *
	 * @param synTradeStatus the new syn trade status
	 */
	public void setSynTradeStatus(String synTradeStatus) {
		this.synTradeStatus = synTradeStatus;
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
	 * 获取  tradeNo.
	 *
	 * @return tradeNo
	 */
	public String getTradeNo() {
		return tradeNo;
	}
	
	/**
	 * 设置 tradeNo.
	 *
	 * @param tradeNo the new trade no
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	
	/**
	 * 获取  pickupDate.
	 *
	 * @return pickupDate
	 */
	public String getPickupDate() {
		return pickupDate;
	}
	
	/**
	 * 设置 pickupDate.
	 *
	 * @param pickupDate the new pickup date
	 */
	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
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
}
