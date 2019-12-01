package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAThemeProduct implements Serializable {
	private static final long serialVersionUID = 2229512781054044744L;
	
	Integer id;
	Integer themeId;
	Integer cityProductId;
	Double themeProductOrder;
	Integer delFlag;
	String createTime;
	String updateTime;
	
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
	 * 获取  cityProductId
	 * @return cityProductId
	 */
	public Integer getCityProductId() {
		return cityProductId;
	}
	/**
	 * 设置 cityProductId
	 * @param cityProductId
	 */
	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}
	/**
	 * 获取  themeProductOrder
	 * @return themeProductOrder
	 */
	public Double getThemeProductOrder() {
		return themeProductOrder;
	}
	/**
	 * 设置 themeProductOrder
	 * @param themeProductOrder
	 */
	public void setThemeProductOrder(Double themeProductOrder) {
		this.themeProductOrder = themeProductOrder;
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
}
