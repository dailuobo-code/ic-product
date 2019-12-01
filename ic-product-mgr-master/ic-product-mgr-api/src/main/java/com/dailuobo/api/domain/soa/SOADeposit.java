package com.dailuobo.api.domain.soa;

import java.io.Serializable;


public class SOADeposit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5090824708871098666L;
	private Integer id;
	private Integer cityId;
    private Integer appUserId;
    private String phone;
    private Integer amount;
    private String remark;
    private String remark2;
    private Integer status;
    private Integer depositUserId;
    private String depositTime;
    private Integer auditUserId;
    private String auditTime;
    private String orderId;
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getAppUserId() {
		return appUserId;
	}
	public void setAppUserId(Integer appUserId) {
		this.appUserId = appUserId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getDepositUserId() {
		return depositUserId;
	}
	public void setDepositUserId(Integer depositUserId) {
		this.depositUserId = depositUserId;
	}

	public String getDepositTime() {
		return depositTime;
	}
	public void setDepositTime(String depositTime) {
		this.depositTime = depositTime;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public Integer getAuditUserId() {
		return auditUserId;
	}
	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
    
    
    
}
