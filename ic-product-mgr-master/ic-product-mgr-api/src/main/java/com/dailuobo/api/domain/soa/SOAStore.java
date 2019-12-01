/**
 * SOAStore.java
 * @author huangwei
 * @since 2016-2-1
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * SOAStore.java
 * @author huangwei
 * @since 2016-2-1
 *  描述：
 */
public class SOAStore implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5616351740803012639L;

	/** 城市id */
	private Integer cityId;
	
	/** 门店id */
	private Integer storeId;
	
	/** 地址 */
	private String address;
	
	/** 电话 */
	private String telephone;
	
	/** 门店名称 */
	private String storeName;
	
	/** 门店的温馨提示 */
	private String remark;
	
	/** 区域id */
	private Integer areaId;
	
	/** 区域名称 */
	private String areaName;
	
	/** 经度 */
	private String lon;

	/** 纬度 */
	private String lat;
	
	/**
	 * 图片
	 */
	private String storePic;
	
	private Integer warehouseId;

	/**
	 * 首页展示图片
	 */
	private String showPic;

	/**
	 * 开店序号
	 */
	private Integer storeNo;

	public Integer getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(Integer storeNo) {
		this.storeNo = storeNo;
	}

	public String getShowPic() {
		return showPic;
	}

	public void setShowPic(String showPic) {
		this.showPic = showPic;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the areaId
	 */
	public Integer getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * @return the cityId
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * @return the storeId
	 */
	public Integer getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * 获取  lon
	 * @return lon
	 */
	public String getLon() {
		return lon;
	}

	/**
	 * 设置 lon
	 * @param lon
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}

	/**
	 * 获取  lat
	 * @return lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 * 设置 lat
	 * @param lat
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 * @return the storePic
	 */
	public String getStorePic() {
		return storePic;
	}

	/**
	 * @param storePic the storePic to set
	 */
	public void setStorePic(String storePic) {
		this.storePic = storePic;
	}

	/**
	 * 获取  warehouseId
	 * @return warehouseId
	 */
	public Integer getWarehouseId() {
		return warehouseId;
	}

	/**
	 * 设置 warehouseId
	 * @param warehouseId
	 */
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	
}
