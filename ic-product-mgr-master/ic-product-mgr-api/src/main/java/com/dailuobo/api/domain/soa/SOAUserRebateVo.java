package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAUserRebateVo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4993337692532660775L;
	private Integer id;
	private Integer userId;
	private Integer profitUserId;
	private Integer type;
	private Integer points;
	private String createTime;
	private String updateTime;
	private String phone;
	private String nickName;
	private String appUserImgUrl;
	
	public String getAppUserImgUrl() {
		return appUserImgUrl;
	}
	public void setAppUserImgUrl(String appUserImgUrl) {
		this.appUserImgUrl = appUserImgUrl;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProfitUserId() {
		return profitUserId;
	}
	public void setProfitUserId(Integer profitUserId) {
		this.profitUserId = profitUserId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
}
