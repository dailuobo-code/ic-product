package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAPrintDetail implements Serializable {
	
	private static final long serialVersionUID = 3208374192481439285L;
	private String prodShowName;
    private String productNum;
    private String actualWeight;
    private String actualPrice;



    
	public String getProdShowName() {
		return prodShowName;
	}
	public void setProdShowName(String prodShowName) {
		this.prodShowName = prodShowName;
	}
	public String getProductNum() {
		return productNum;
	}
	public void setProductNum(String productNum) {
		this.productNum = productNum;
	}

	public String getActualWeight() {
		return actualWeight;
	}

	public void setActualWeight(String actualWeight) {
		this.actualWeight = actualWeight;
	}

	public String getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(String actualPrice) {
		this.actualPrice = actualPrice;
	}


}
