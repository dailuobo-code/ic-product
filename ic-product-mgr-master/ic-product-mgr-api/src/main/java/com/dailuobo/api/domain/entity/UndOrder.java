package com.dailuobo.api.domain.entity;


import java.io.Serializable;

public class UndOrder implements Serializable {
	private static final long serialVersionUID = 1L;
    private String undOrderId;
    private UndProduct[] products;
	public String getUndOrderId() {
		return undOrderId;
	}
	public void setUndOrderId(String undOrderId) {
		this.undOrderId = undOrderId;
	}
	public UndProduct[] getProducts() {
		return products;
	}
	public void setProducts(UndProduct[] products) {
		this.products = products;
	}
	
    
    
    
}
