package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAPrint<T> implements Serializable {
	private static final long serialVersionUID = 4074934916015948562L;
	private String phone;
    private String undOrderId;
	private String totalPrice;
    private T detail;
    
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUndOrderId() {
		return undOrderId;
	}
	public void setUndOrderId(String undOrderId) {
		this.undOrderId = undOrderId;
	}
	public T getDetail() {
		return detail;
	}
	public void setDetail(T detail) {
		this.detail = detail;
	}
	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

  
}
