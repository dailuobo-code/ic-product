package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * SOACityVO<br/>
 * 描述:
 * SOA 传输的城市对象
 * @author huangwei
 * @since 2016-1-19<br/>
 */
public class SOACityVO implements Serializable{

	/**
	 * SOACityVO.java
	 */
	private static final long serialVersionUID = 3395767622977937143L;

	/** 城市id */
	private Integer cityId;
	
	/** 城市名称 */
	private String cityName;
	
	/** 百度code */
	private String baiduCode;
	
	/** 菜菜code */
	private String caiCode;
	
	/** 百度城市名称 */
	private String baiduName;
	
	/** 地址 */
	private String address;
	
	/** 拼音名称 */
	private String phoneticize;

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
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	/**
	 * @return the baiduCode
	 */
	public String getBaiduCode() {
		return baiduCode;
	}

	/**
	 * @param baiduCode the baiduCode to set
	 */
	public void setBaiduCode(String baiduCode) {
		this.baiduCode = baiduCode;
	}

	/**
	 * @return the caiCode
	 */
	public String getCaiCode() {
		return caiCode;
	}

	/**
	 * @param caiCode the caiCode to set
	 */
	public void setCaiCode(String caiCode) {
		this.caiCode = caiCode;
	}

	/**
	 * @return the baiduName
	 */
	public String getBaiduName() {
		return baiduName;
	}

	/**
	 * @param baiduName the baiduName to set
	 */
	public void setBaiduName(String baiduName) {
		this.baiduName = baiduName;
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
	 * @return the phoneticize
	 */
	public String getPhoneticize() {
		return phoneticize;
	}

	/**
	 * @param phoneticize the phoneticize to set
	 */
	public void setPhoneticize(String phoneticize) {
		this.phoneticize = phoneticize;
	}
	
	
}
