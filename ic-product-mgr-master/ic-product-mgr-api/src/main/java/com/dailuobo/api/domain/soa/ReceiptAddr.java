package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class ReceiptAddr implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4500942560412104733L;
	private Integer id;
	private Integer userId;
	private String recipients;
	private String phone;
	private Integer cityId;
	private String cityName;
	private Integer storeId;
	private String storeName;
	private Integer residenceId;
	private String residenceName;
	private String address;
	private Integer isDefault;
	
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
	 * 获取  userId
	 * @return userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置 userId
	 * @param userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取  recipients
	 * @return recipients
	 */
	public String getRecipients() {
		return recipients;
	}
	/**
	 * 设置 recipients
	 * @param recipients
	 */
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}
	/**
	 * 获取  phone
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置 phone
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取  cityId
	 * @return cityId
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * 设置 cityId
	 * @param cityId
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取  cityName
	 * @return cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * 设置 cityName
	 * @param cityName
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
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
	/**
	 * 获取  storeName
	 * @return storeName
	 */
	public String getStoreName() {
		return storeName;
	}
	/**
	 * 设置 storeName
	 * @param storeName
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	/**
	 * 获取  residenceId
	 * @return residenceId
	 */
	public Integer getResidenceId() {
		return residenceId;
	}
	/**
	 * 设置 residenceId
	 * @param residenceId
	 */
	public void setResidenceId(Integer residenceId) {
		this.residenceId = residenceId;
	}
	/**
	 * 获取  residenceName
	 * @return residenceName
	 */
	public String getResidenceName() {
		return residenceName;
	}
	/**
	 * 设置 residenceName
	 * @param residenceName
	 */
	public void setResidenceName(String residenceName) {
		this.residenceName = residenceName;
	}
	/**
	 * 获取  address
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置 address
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取  isDefault
	 * @return isDefault
	 */
	public Integer getIsDefault() {
		return isDefault;
	}
	/**
	 * 设置 isDefault
	 * @param isDefault
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
}
