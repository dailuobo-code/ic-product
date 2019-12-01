package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAStoreMouldDto implements Serializable {
	
	private static final long serialVersionUID = 893006143976616120L;
	private Integer id;
	private Integer mouldId;		//首页模板编号
	private Integer cityId;			//城市编号
	private Integer storeId;		//门店编号
	private Integer status;			//状态：1，正常；2，删除
	private String  createTime;		//创建时间
	private Integer createUserId;	//创建者ID，默认值表示系统
	private String  updateTime;	//更新时间
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
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	

	
}
