package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOACollections implements Serializable{
	/**
	 * 用户收藏
	 */
	private static final long serialVersionUID = -2234957263800601305L;
	private Integer userId;
	private Integer cityId;
	private Integer cityProductId;
	private String createTime;
	private Integer status;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getCityProductId() {
		return cityProductId;
	}
	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
