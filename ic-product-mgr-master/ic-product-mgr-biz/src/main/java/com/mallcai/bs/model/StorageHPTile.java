package com.mallcai.bs.model;

import java.io.Serializable;

/**
 * HomePageTile<br/>
 * 描述:
 * @author huangwei
 * @since 2016-1-20<br/>
 */
public class StorageHPTile implements Serializable{

	/**
	 * HomePageTile.java
	 */
	private static final long serialVersionUID = 3798850067526597090L;

	/** 城市id */
	private Integer cityId;

	/** 磁贴id */
	private Integer tileId;

	/** 磁贴序号 */
	private Integer tileOrder;
	
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
	 * @return the tileId
	 */
	public Integer getTileId() {
		return tileId;
	}

	/**
	 * @param tileId the tileId to set
	 */
	public void setTileId(Integer tileId) {
		this.tileId = tileId;
	}

	/**
	 * @return the tileOrder
	 */
	public Integer getTileOrder() {
		return tileOrder;
	}

	/**
	 * @param tileOrder the tileOrder to set
	 */
	public void setTileOrder(Integer tileOrder) {
		this.tileOrder = tileOrder;
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
