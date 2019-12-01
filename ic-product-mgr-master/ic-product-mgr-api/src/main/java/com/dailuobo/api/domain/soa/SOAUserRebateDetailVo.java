package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAUserRebateDetailVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4152307937351135505L;

	private Integer id;
	private Integer rebateId;
	private String orderId;
	private String orderTime;
	private String pickupTime;
	private Integer points;
	private String createTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRebateId() {
		return rebateId;
	}
	public void setRebateId(Integer rebateId) {
		this.rebateId = rebateId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
