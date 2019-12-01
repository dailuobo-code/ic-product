package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * Created by Aministartor on 2018/5/24.
 */
public class SOABalanceLog implements Serializable {

	private static final long serialVersionUID = -4828314993841619373L;
	private Integer appUserId;
	private Integer operationType;
	private Integer balance;
	private Integer amount;
    private String remark;
    private String operationUserId;
    private String operationTs;
    private String orderId;
	public Integer getAppUserId() {
		return appUserId;
	}
	public void setAppUserId(Integer appUserId) {
		this.appUserId = appUserId;
	}
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
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
	public String getOperationUserId() {
		return operationUserId;
	}
	public void setOperationUserId(String operationUserId) {
		this.operationUserId = operationUserId;
	}
	public String getOperationTs() {
		return operationTs;
	}
	public void setOperationTs(String operationTs) {
		this.operationTs = operationTs;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
    
    
}
