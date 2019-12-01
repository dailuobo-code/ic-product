package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.Date;

public class SOAUnDepositLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -9073143343645753779L;
	private Integer id;
    private Integer cityId;
    private Integer appUserId;
    private String phone;
    private Integer amount;
    private String remark;
    private String remark2;
    private Integer depositUserId;
    private String depositUserName;
    private Date depositTime;
    private Integer auditUserId;
    private String auditUserName;
    private Date auditTime;
    private Byte status;
    private String undOrderId;
    
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
	public Integer getDepositUserId() {
		return depositUserId;
	}
	public void setDepositUserId(Integer depositUserId) {
		this.depositUserId = depositUserId;
	}
	public String getDepositUserName() {
		return depositUserName;
	}
	public void setDepositUserName(String depositUserName) {
		this.depositUserName = depositUserName;
	}
	public Date getDepositTime() {
		return depositTime;
	}
	public void setDepositTime(Date depositTime) {
		this.depositTime = depositTime;
	}
	public Integer getAuditUserId() {
		return auditUserId;
	}
	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}
	public String getAuditUserName() {
		return auditUserName;
	}
	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	public String getUndOrderId() {
		return undOrderId;
	}
	public void setUndOrderId(String undOrderId) {
		this.undOrderId = undOrderId;
	}
    
    
}
