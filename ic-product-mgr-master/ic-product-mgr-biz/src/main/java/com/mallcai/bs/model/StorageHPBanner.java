package com.mallcai.bs.model;

import java.io.Serializable;

/**
 * MongoHomePageBanner<br/>
 * 描述:
 * @author huangwei
 * @since 2016-1-20<br/>
 */
public class StorageHPBanner implements Serializable{

	/**
	 * MongoHomePageBanner.java
	 */
	private static final long serialVersionUID = -9015601747919985178L;

	/** 城市id */
	private Integer cityId;

	/** bannerid */
	private Integer bannerId;

	/** banner序号 */
	private Integer bannerOrder;
	
	private Integer storeId;

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
	 * @return the bannerId
	 */
	public Integer getBannerId() {
		return bannerId;
	}

	/**
	 * @param bannerId the bannerId to set
	 */
	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	/**
	 * @return the bannerOrder
	 */
	public Integer getBannerOrder() {
		return bannerOrder;
	}

	/**
	 * @param bannerOrder the bannerOrder to set
	 */
	public void setBannerOrder(Integer bannerOrder) {
		this.bannerOrder = bannerOrder;
	}

	/**
	 * 获取  storeId
	 * @return storeId
	 */
	public Integer getStoreId() {
		return storeId;
	}

	/**
	 * 设置 storeId
	 * @param storeId
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	
}
