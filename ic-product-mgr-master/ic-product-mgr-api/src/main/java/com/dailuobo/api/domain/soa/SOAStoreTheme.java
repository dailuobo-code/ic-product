package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAStoreTheme implements Serializable {
	private static final long serialVersionUID = -3364167962217472620L;
	
	Integer id;
	Integer themeId;
	Integer cityId;
	Double themeOrder;
	Integer delFlag;
	String createTime;
	String updateTime;
	Integer storeId;
	
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
	 * 获取  themeId
	 * @return themeId
	 */
	public Integer getThemeId() {
		return themeId;
	}
	/**
	 * 设置 themeId
	 * @param themeId
	 */
	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
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
	 * 获取  themeOrder
	 * @return themeOrder
	 */
	public Double getThemeOrder() {
		return themeOrder;
	}
	/**
	 * 设置 themeOrder
	 * @param themeOrder
	 */
	public void setThemeOrder(Double themeOrder) {
		this.themeOrder = themeOrder;
	}
	/**
	 * 获取  delFlag
	 * @return delFlag
	 */
	public Integer getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置 delFlag
	 * @param delFlag
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取  createTime
	 * @return createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	/**
	 * 设置 createTime
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取  updateTime
	 * @return updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置 updateTime
	 * @param updateTime
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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
}
