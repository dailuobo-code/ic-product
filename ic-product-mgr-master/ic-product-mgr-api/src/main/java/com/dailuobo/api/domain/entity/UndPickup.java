package com.dailuobo.api.domain.entity;


import java.io.Serializable;

public class UndPickup implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer operatorId;
    private UndOrder orders;
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public UndOrder getOrders() {
		return orders;
	}
	public void setOrders(UndOrder orders) {
		this.orders = orders;
	}
    
    
}
