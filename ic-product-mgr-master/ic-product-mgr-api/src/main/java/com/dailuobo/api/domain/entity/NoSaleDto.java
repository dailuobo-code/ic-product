package com.dailuobo.api.domain.entity;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SalesSpecDto.
 */
public class NoSaleDto implements Serializable {
	
	private static final long serialVersionUID = 3634852490732645648L;

	private Integer id; 
	private Integer cityId; 
	private Integer storeId; 
	private Integer cityProductId; 
	private String createUser; 
	private String createTime; 
	private String updateUser; 
	private String updateTime; 
	private Integer status;
	private Integer specId;
	
	public Integer getSpecId() {
		return specId;
	}
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getCityProductId() {
		return cityProductId;
	}
	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	} 
	
	
	
}
