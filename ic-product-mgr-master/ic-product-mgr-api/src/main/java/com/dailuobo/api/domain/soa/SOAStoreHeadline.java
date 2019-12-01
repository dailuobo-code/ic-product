package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAStoreHeadline implements Serializable {

	private static final long serialVersionUID = -5913987214565083147L;
	
	Integer id;
	Integer headlineId;
	Integer cityId;
	Double headlineOrder;
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
	 * 获取  headlineId
	 * @return headlineId
	 */
	public Integer getHeadlineId() {
		return headlineId;
	}
	/**
	 * 设置 headlineId
	 * @param headlineId
	 */
	public void setHeadlineId(Integer headlineId) {
		this.headlineId = headlineId;
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
	 * 获取  headlineOrder
	 * @return headlineOrder
	 */
	public Double getHeadlineOrder() {
		return headlineOrder;
	}
	/**
	 * 设置 headlineOrder
	 * @param headlineOrder
	 */
	public void setHeadlineOrder(Double headlineOrder) {
		this.headlineOrder = headlineOrder;
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
