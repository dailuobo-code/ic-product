package com.dailuobo.api.domain.soa.city;

import java.io.Serializable;

public class StoreMouldDto implements Serializable {
	private static final long serialVersionUID = -8421399774720130674L;
	private Integer hpmId; 	//首页模板编号
	private Integer cityId;	//城市编号
	private Integer storeId;	//门店编号
	private Integer status;	//状态：1，正常；2，删除
	private String create_time;				//创建时间
	private Integer create_user_id;			//创建者ID，默认值表示系统
	private String update_time;				//更新时间
	private Integer update_user_id;			//更新者ID，默认值表示系统
	public Integer getHpmId() {
		return hpmId;
	}
	public void setHpmId(Integer hpmId) {
		this.hpmId = hpmId;
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
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public Integer getCreate_user_id() {
		return create_user_id;
	}
	public void setCreate_user_id(Integer create_user_id) {
		this.create_user_id = create_user_id;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public Integer getUpdate_user_id() {
		return update_user_id;
	}
	public void setUpdate_user_id(Integer update_user_id) {
		this.update_user_id = update_user_id;
	}
	

	

	
}
