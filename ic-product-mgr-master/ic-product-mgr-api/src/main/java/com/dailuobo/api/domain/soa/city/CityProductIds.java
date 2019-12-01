package com.dailuobo.api.domain.soa.city;

import java.io.Serializable;

public class CityProductIds implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7722101101690523492L;
	private Integer cityId;
	private String productIds;
	
	/**
	 * 获取  cityId
	 * @return cityId
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * 设置 cityId
	 * @param cityId
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取  productIds
	 * @return productIds
	 */
	public String getProductIds() {
		return productIds;
	}
	/**
	 * 设置 productIds
	 * @param productIds
	 */
	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}
}
