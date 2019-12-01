/**
 * SOAPickupTime.java
 * @author huangwei
 * @since 2016-2-3
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * SOAPickupTime.java
 * @author huangwei
 * @since 2016-2-3
 *  描述：
 */
public class SOAPickupTime implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7230091374397646829L;

	/** 城市id */
	private Integer cityId;

	/** 取货时间id */
	private Integer pickupId;

	/** 开始时间 */
	private String beginTime;

	/** 结束时间 */
	private String endTime;
	
	/** 限制流量 */
	private Integer threshold;
	
	/** 当前流量 */
	private Integer surplusFlow;
	
	private Integer storeId;
	
	private Integer type;

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
	 * @return the pickupId
	 */
	public Integer getPickupId() {
		return pickupId;
	}

	/**
	 * @param pickupId the pickupId to set
	 */
	public void setPickupId(Integer pickupId) {
		this.pickupId = pickupId;
	}

	/**
	 * @return the beginTime
	 */
	public String getBeginTime() {
		return beginTime;
	}

	/**
	 * @param beginTime the beginTime to set
	 */
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the threshold
	 */
	public Integer getThreshold() {
		return threshold;
	}

	/**
	 * @param threshold the threshold to set
	 */
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	/**
	 * @return the surplusFlow
	 */
	public Integer getSurplusFlow() {
		return surplusFlow;
	}

	/**
	 * @param surplusFlow the surplusFlow to set
	 */
	public void setSurplusFlow(Integer surplusFlow) {
		this.surplusFlow = surplusFlow;
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
	 * 获取  type
	 * @return type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置 type
	 * @param type
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	
	
}
