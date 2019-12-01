package com.dailuobo.api.domain.entity;


import java.io.Serializable;

public class UndProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	  	private String productNo;
	    private Integer cityProductId;
	   
	    private String barcodes;
		public String getProductNo() {
			return productNo;
		}
		public void setProductNo(String productNo) {
			this.productNo = productNo;
		}
		public Integer getCityProductId() {
			return cityProductId;
		}
		public void setCityProductId(Integer cityProductId) {
			this.cityProductId = cityProductId;
		}

		public String getBarcodes() {
			return barcodes;
		}
		public void setBarcodes(String barcodes) {
			this.barcodes = barcodes;
		}
	    
	    
}
