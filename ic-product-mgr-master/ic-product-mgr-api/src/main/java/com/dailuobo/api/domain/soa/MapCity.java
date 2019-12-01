/**
 * SOAMapCity.java
 * @author huangwei
 * @since 2016-1-27
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * SOAMapCity.java
 * @author huangwei
 * @since 2016-1-27
 *  描述：
 */
public class MapCity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -929230580078177881L;

	/** 城市code */
	private Integer cityCode;
	
	/** 城市名称 */
	private String cityName;

	/**
	 * @return the cityCode
	 */
	public Integer getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
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
}
