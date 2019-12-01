package com.dailuobo.api.domain.soa.city;

import java.io.Serializable;

public class StoreMouldDetailDto implements Serializable {
	private static final long serialVersionUID = -3607869694939117085L;
	private Integer id;
	private Integer mouldId;		//首页模板编号
	private Integer orderNum;		//序号
	private Integer moduleCode;		//模块code
	private String  style_code_one;	//试样code1
	private String  style_code_two;	//试样code2
	private String  picUrl;			//图片URL
	private String  linkUrl;		//链接URL
	private Integer productsNum;	//首页显示商品个数
	private Integer status;			//状态：1，正常；2，删除
	private String  createTime;		//创建时间
	private Integer createUserId;	//创建者ID，默认值表示系统
	private String  update_time;	//更新时间
	private Integer updateUserId;	//更新者ID，默认值表示系统
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
	public Integer getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(Integer moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getStyle_code_one() {
		return style_code_one;
	}
	public void setStyle_code_one(String style_code_one) {
		this.style_code_one = style_code_one;
	}
	public String getStyle_code_two() {
		return style_code_two;
	}
	public void setStyle_code_two(String style_code_two) {
		this.style_code_two = style_code_two;
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
	public Integer getProductsNum() {
		return productsNum;
	}
	public void setProductsNum(Integer productsNum) {
		this.productsNum = productsNum;
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
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}

	
}
