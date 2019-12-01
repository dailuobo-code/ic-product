package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAStoreMouldDetailDto implements Serializable {
	private static final long serialVersionUID = -7814322955241930094L;
	private Integer id;
	private Integer mouldId;		//首页模板编号
	private Integer orderNum;		//序号
	private String  moduleCode;		//模块code
	private String  mouldDetailJson;//樣式json 
	private String  picUrl;			//图片URL
	private String  linkUrl;		//链接URL
	private Integer status;			//状态：1，正常；2，删除
	private String  createTime;		//创建时间
	private Integer createUserId;	//创建者ID，默认值表示系统
	private String  updateTime;		//更新时间
	private Integer updateUserId;	//更新者ID，默认值表示系统
	
	
	
	public String getMouldDetailJson() {
		return mouldDetailJson;
	}
	public void setMouldDetailJson(String mouldDetailJson) {
		this.mouldDetailJson = mouldDetailJson;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMouldId() {
		return mouldId;
	}
	public void setMouldId(Integer mouldId) {
		this.mouldId = mouldId;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	
}
