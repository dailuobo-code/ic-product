package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SOAResidence.
 */
public class SOAResidence implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7273673347041340692L;
	
	/** The residence id. */
	private Integer residenceId;
	
	/** The residence name. */
	private String residenceName;
	
	/** The area name. */
	private String areaName;
	
	/** The lon. */
	private String lon;
	
	/** The lat. */
	private String lat;
	
	/** The city id. */
	private Integer cityId;
	
	/**
	 * 获取  residenceId.
	 *
	 * @return residenceId
	 */
	public Integer getResidenceId() {
		return residenceId;
	}
	
	/**
	 * 设置 residenceId.
	 *
	 * @param residenceId the new residence id
	 */
	public void setResidenceId(Integer residenceId) {
		this.residenceId = residenceId;
	}
	
	/**
	 * 获取  residenceName.
	 *
	 * @return residenceName
	 */
	public String getResidenceName() {
		return residenceName;
	}
	
	/**
	 * 设置 residenceName.
	 *
	 * @param residenceName the new residence name
	 */
	public void setResidenceName(String residenceName) {
		this.residenceName = residenceName;
	}
	
	/**
	 * 获取  areaName.
	 *
	 * @return areaName
	 */
	public String getAreaName() {
		return areaName;
	}
	
	/**
	 * 设置 areaName.
	 *
	 * @param areaName the new area name
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	/**
	 * 获取  lon.
	 *
	 * @return lon
	 */
	public String getLon() {
		return lon;
	}
	
	/**
	 * 设置 lon.
	 *
	 * @param lon the new lon
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}
	
	/**
	 * 获取  lat.
	 *
	 * @return lat
	 */
	public String getLat() {
		return lat;
	}
	
	/**
	 * 设置 lat.
	 *
	 * @param lat the new lat
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	/**
	 * 获取  cityId.
	 *
	 * @return cityId
	 */
	public Integer getCityId() {
		return cityId;
	}
	
	/**
	 * 设置 cityId.
	 *
	 * @param cityId the new city id
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
}
