package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class IdsModel implements Serializable{
	private static final long serialVersionUID = 5145069259060262395L;
	
	private Integer cityProductId;
	private Integer orderDetailJsId;
	private String remark;
	private Float amount;
	private Integer pointAmount;
	
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getCityProductId() {
		return cityProductId;
	}
	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}
	public Integer getOrderDetailJsId() {
		return orderDetailJsId;
	}
	public void setOrderDetailJsId(Integer orderDetailJsId) {
		this.orderDetailJsId = orderDetailJsId;
	}

	public Integer getPointAmount() {
		return pointAmount;
	}

	public void setPointAmount(Integer pointAmount) {
		this.pointAmount = pointAmount;
	}
}
