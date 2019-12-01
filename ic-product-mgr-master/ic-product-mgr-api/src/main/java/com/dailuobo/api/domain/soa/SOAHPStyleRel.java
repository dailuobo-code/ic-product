package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAHPStyleRel implements Serializable {
	private static final long serialVersionUID = 1320514910203471736L;
	
	private Integer id;
	private Integer cityId;
	private Integer storeId;
	private Integer schemaId;
	
	/**
	 * 获取  id
	 * @return id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置 id
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
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
	 * 获取  storeId
	 * @return storeId
	 */
	public Integer getStoreId() {
		return storeId;
	}
	/**
	 * 设置 storeId
	 * @param storeId
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	/**
	 * 获取  schemaId
	 * @return schemaId
	 */
	public Integer getSchemaId() {
		return schemaId;
	}
	/**
	 * 设置 schemaId
	 * @param schemaId
	 */
	public void setSchemaId(Integer schemaId) {
		this.schemaId = schemaId;
	}
	
}
