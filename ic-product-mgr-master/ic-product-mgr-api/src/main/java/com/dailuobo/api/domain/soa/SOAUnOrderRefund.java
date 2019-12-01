package com.dailuobo.api.domain.soa;

import java.io.Serializable;


public class SOAUnOrderRefund implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6394695867670758016L;
	private Integer id;
	private String undOrderId;
	private String remark;
	private String phone;
	private Integer depositUserId;
	private Integer cityId;
	private Integer storeId;
	private Float amount;
	private Integer undOrderDetailJsId;
	private Integer createUserId;
	private Integer cityProductId;
	private String createTime;
	private Integer actualWeight;
	
	
	public Integer getActualWeight() {
		return actualWeight;
	}
	public void setActualWeight(Integer actualWeight) {
		this.actualWeight = actualWeight;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getCityProductId() {
		return cityProductId;
	}
	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUndOrderId() {
		return undOrderId;
	}
	public void setUndOrderId(String undOrderId) {
		this.undOrderId = undOrderId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getDepositUserId() {
		return depositUserId;
	}
	public void setDepositUserId(Integer depositUserId) {
		this.depositUserId = depositUserId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public Integer getUndOrderDetailJsId() {
		return undOrderDetailJsId;
	}
	public void setUndOrderDetailJsId(Integer undOrderDetailJsId) {
		this.undOrderDetailJsId = undOrderDetailJsId;
	}
	
	
}
