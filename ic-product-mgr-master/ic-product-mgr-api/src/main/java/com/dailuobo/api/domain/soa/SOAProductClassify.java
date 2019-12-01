package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAProductClassify implements Serializable {
	private static final long serialVersionUID = 7550675235614693054L;
	
	private Integer classifyId;
	private String classifyNo;
	private String classifyName;
	private String iconUrl;
	private Integer showOrder;
	private String createTime;
	private String updateTime;
	private Integer fatherId;
	
	/**
	 * 获取  classifyId
	 * @return classifyId
	 */
	public Integer getClassifyId() {
		return classifyId;
	}
	/**
	 * 设置 classifyId
	 * @param classifyId
	 */
	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}
	/**
	 * 获取  classifyNo
	 * @return classifyNo
	 */
	public String getClassifyNo() {
		return classifyNo;
	}
	/**
	 * 设置 classifyNo
	 * @param classifyNo
	 */
	public void setClassifyNo(String classifyNo) {
		this.classifyNo = classifyNo;
	}
	/**
	 * 获取  classifyName
	 * @return classifyName
	 */
	public String getClassifyName() {
		return classifyName;
	}
	/**
	 * 设置 classifyName
	 * @param classifyName
	 */
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	/**
	 * 获取  iconUrl
	 * @return iconUrl
	 */
	public String getIconUrl() {
		return iconUrl;
	}
	/**
	 * 设置 iconUrl
	 * @param iconUrl
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	/**
	 * 获取  showOrder
	 * @return showOrder
	 */
	public Integer getShowOrder() {
		return showOrder;
	}
	/**
	 * 设置 showOrder
	 * @param showOrder
	 */
	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
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
	 * 获取  fatherId
	 * @return fatherId
	 */
	public Integer getFatherId() {
		return fatherId;
	}
	/**
	 * 设置 fatherId
	 * @param fatherId
	 */
	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}
}
