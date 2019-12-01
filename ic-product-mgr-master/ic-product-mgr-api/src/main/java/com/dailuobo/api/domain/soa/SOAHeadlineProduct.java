package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAHeadlineProduct implements Serializable {
	private static final long serialVersionUID = 121627993235323L;
	
	Integer id;
	Integer headlineId;
	Integer cityProductId;
	Double headlineProductOrder;
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
	 * 获取  headlineProductOrder
	 * @return headlineProductOrder
	 */
	public Double getHeadlineProductOrder() {
		return headlineProductOrder;
	}
	/**
	 * 设置 headlineProductOrder
	 * @param headlineProductOrder
	 */
	public void setHeadlineProductOrder(Double headlineProductOrder) {
		this.headlineProductOrder = headlineProductOrder;
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
