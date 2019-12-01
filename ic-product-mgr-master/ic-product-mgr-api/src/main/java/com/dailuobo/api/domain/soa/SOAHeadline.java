package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAHeadline implements Serializable {
	private static final long serialVersionUID = 2721886989868060769L;
	Integer headlineId;
	Integer cityId;
	String headlineName;
	Integer headlineType;
	Integer headlineProductId;
	String headlineUrl;
	String headlinePic;
	String memo;
	Integer status;
	Integer delFlag;
	String createTime;
	String updateTime;
	Integer productStatus;
	Double headlineOrder;
	
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
	 * 获取  headlineName
	 * @return headlineName
	 */
	public String getHeadlineName() {
		return headlineName;
	}
	/**
	 * 设置 headlineName
	 * @param headlineName
	 */
	public void setHeadlineName(String headlineName) {
		this.headlineName = headlineName;
	}
	/**
	 * 获取  headlineType
	 * @return headlineType
	 */
	public Integer getHeadlineType() {
		return headlineType;
	}
	/**
	 * 设置 headlineType
	 * @param headlineType
	 */
	public void setHeadlineType(Integer headlineType) {
		this.headlineType = headlineType;
	}
	/**
	 * 获取  headlineProductId
	 * @return headlineProductId
	 */
	public Integer getHeadlineProductId() {
		return headlineProductId;
	}
	/**
	 * 设置 headlineProductId
	 * @param headlineProductId
	 */
	public void setHeadlineProductId(Integer headlineProductId) {
		this.headlineProductId = headlineProductId;
	}
	/**
	 * 获取  headlineUrl
	 * @return headlineUrl
	 */
	public String getHeadlineUrl() {
		return headlineUrl;
	}
	/**
	 * 设置 headlineUrl
	 * @param headlineUrl
	 */
	public void setHeadlineUrl(String headlineUrl) {
		this.headlineUrl = headlineUrl;
	}
	/**
	 * 获取  headlinePic
	 * @return headlinePic
	 */
	public String getHeadlinePic() {
		return headlinePic;
	}
	/**
	 * 设置 headlinePic
	 * @param headlinePic
	 */
	public void setHeadlinePic(String headlinePic) {
		this.headlinePic = headlinePic;
	}
	/**
	 * 获取  memo
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * 设置 memo
	 * @param memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 获取  status
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置 status
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	 * 获取  productStatus
	 * @return productStatus
	 */
	public Integer getProductStatus() {
		return productStatus;
	}
	/**
	 * 设置 productStatus
	 * @param productStatus
	 */
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
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
}
