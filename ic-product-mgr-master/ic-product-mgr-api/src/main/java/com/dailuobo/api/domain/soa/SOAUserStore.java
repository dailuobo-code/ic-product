/**
 * SOAUserStore.java
 * @author huangwei
 * @since 2016-2-1
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * SOAUserStore.java
 * @author huangwei
 * @since 2016-2-1
 *  描述：
 */
public class SOAUserStore implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3716458393967550899L;

	/** 城市id */
	private Integer cityId;
	
	/** 用户id */
	private Integer userId;
	
	/** 取货点id */
	private Integer storeId;
	
	/** 自定义名称 */
	private String customName;
	
	/** 是否默认 0:非默认,1:默认*/
	private Integer isDefault;
	
	/** 地址 */
	private String address;
	
	/** 电话 */
	private String telephone;
	
	/** 门店名称 */
	private String storeName;

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
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	 * @return the customName
	 */
	public String getCustomName() {
		return customName;
	}

	/**
	 * @param customName the customName to set
	 */
	public void setCustomName(String customName) {
		this.customName = customName;
	}

	/**
	 * @return the isDefault
	 */
	public Integer getIsDefault() {
		return isDefault;
	}

	/**
	 * @param isDefault the isDefault to set
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
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

	
}
