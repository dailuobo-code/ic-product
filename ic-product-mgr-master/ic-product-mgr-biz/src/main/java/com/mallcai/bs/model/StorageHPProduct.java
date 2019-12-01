package com.mallcai.bs.model;

import java.io.Serializable;

/**
 * MongoHomePageProductList<br/>
 * 描述:
 * @author huangwei
 * @since 2016-1-20<br/>
 */
public class StorageHPProduct implements Serializable{

	/**
	 * MongoHomePageProductList.java
	 */
	private static final long serialVersionUID = -4073942530813073983L;

	/** 城市id */
	private Integer cityId;

	/** 城市商品id */
	private Integer cityProductId;

	/** 磁贴商品序号 */
	private Integer productOrder;
	
	private String createTime;
	private String updateTime;
	private Integer id;
	
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
	 * @return the cityProductId
	 */
	public Integer getCityProductId() {
		return cityProductId;
	}

	/**
	 * @param cityProductId the cityProductId to set
	 */
	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}

	/**
	 * @return the productOrder
	 */
	public Integer getProductOrder() {
		return productOrder;
	}

	/**
	 * @param productOrder the productOrder to set
	 */
	public void setProductOrder(Integer productOrder) {
		this.productOrder = productOrder;
	}

	/**
	 * 获取  id
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置 id
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取  createTime
	 * @return createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取  updateTime
	 * @return updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置 updateTime
	 * @param updateTime
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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
