/**
 * SOACityDistance.java
 * @author huangwei
 * @since 2016-3-28
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * SOACityDistance.java
 * @author huangwei
 * @since 2016-3-28
 *  描述：分区段价格信息
 */
public class CityDistance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7621804654945956406L;

	/** 城市id */
	private Integer cityId;
	
	/** 折扣类型 */
	private Integer type;
	
	/** 最低距离 */
	private Integer lowerLimit;
	
	/** 最高距离 */
	private Integer upperLimit;

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
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the lowerLimit
	 */
	public Integer getLowerLimit() {
		return lowerLimit;
	}

	/**
	 * @param lowerLimit the lowerLimit to set
	 */
	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * @return the uppLimit
	 */
	public Integer getUpperLimit() {
		return upperLimit;
	}

	/**
	 * @param upperLimit the uppLimit to set
	 */
	public void setUpperLimit(Integer upperLimit) {
		this.upperLimit = upperLimit;
	}
	
	@Override
	public String toString(){
		return JSON.toJSONString(this);
	}
}
