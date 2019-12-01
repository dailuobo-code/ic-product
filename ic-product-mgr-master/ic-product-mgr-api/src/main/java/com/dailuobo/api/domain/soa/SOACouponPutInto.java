/**
 * CouponPutInto.java
 * @author huangwei
 * @since 2016-4-26
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.Date;

/**
 * CouponPutInto.java
 * @author huangwei
 * @since 2016-4-26
 *  描述：优惠券投放
 */
public class SOACouponPutInto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6604340985576870429L;

	private Integer couponPutIntoId;
	
	/**
	 * 优惠券类别id
	 */
	private Integer classifyId;
	
	/**
	 * 投放数量
	 */
	private Integer putIntoCount;
	
	/**
	 * 优惠券名称
	 */
	private String couponName;
	
	/**
	 * 投放开始时间
	 */
	private Date putIntoStartTime;
	
	/**
	 * 投放结束时间
	 */
	private Date putIntoEndTime;
	
	/**
	 * 使用开始时间
	 */
	private Date usedStartTime;
	
	/**
	 * 使用结束时间
	 */
	private Date usedEndTime;
	
	/**
	 * 最低使用额度
	 */
	private Integer minUsedAmount;
	
	/**
	 * 城市id
	 */
	private Integer cityId;
	
	/**
	 * 抵扣金额
	 */
	private Integer deductions;
	
	/**
	 * 是否己领取
	 */
	private Integer dole;
	
	/**
	 * 是否可领取
	 */
	private Integer canDole;


	/**
	 * @return the couponPutIntoId
	 */
	public Integer getCouponPutIntoId() {
		return couponPutIntoId;
	}

	/**
	 * @param couponPutIntoId the couponPutIntoId to set
	 */
	public void setCouponPutIntoId(Integer couponPutIntoId) {
		this.couponPutIntoId = couponPutIntoId;
	}

	/**
	 * @return the classifyId
	 */
	public Integer getClassifyId() {
		return classifyId;
	}

	/**
	 * @param classifyId the classifyId to set
	 */
	public void setClassifyId(Integer classifyId) {
		this.classifyId = classifyId;
	}

	/**
	 * @return the putIntoCount
	 */
	public Integer getPutIntoCount() {
		return putIntoCount;
	}

	/**
	 * @param putIntoCount the putIntoCount to set
	 */
	public void setPutIntoCount(Integer putIntoCount) {
		this.putIntoCount = putIntoCount;
	}

	/**
	 * @return the putIntoStartTime
	 */
	public Date getPutIntoStartTime() {
		return putIntoStartTime;
	}

	/**
	 * @param putIntoStartTime the putIntoStartTime to set
	 */
	public void setPutIntoStartTime(Date putIntoStartTime) {
		this.putIntoStartTime = putIntoStartTime;
	}

	/**
	 * @return the putIntoEndTime
	 */
	public Date getPutIntoEndTime() {
		return putIntoEndTime;
	}

	/**
	 * @param putIntoEndTime the putIntoEndTime to set
	 */
	public void setPutIntoEndTime(Date putIntoEndTime) {
		this.putIntoEndTime = putIntoEndTime;
	}

	/**
	 * @return the usedStartTime
	 */
	public Date getUsedStartTime() {
		return usedStartTime;
	}

	/**
	 * @param usedStartTime the usedStartTime to set
	 */
	public void setUsedStartTime(Date usedStartTime) {
		this.usedStartTime = usedStartTime;
	}

	/**
	 * @return the usedEndTime
	 */
	public Date getUsedEndTime() {
		return usedEndTime;
	}

	/**
	 * @param usedEndTime the usedEndTime to set
	 */
	public void setUsedEndTime(Date usedEndTime) {
		this.usedEndTime = usedEndTime;
	}

	/**
	 * @return the minUsedAmount
	 */
	public Integer getMinUsedAmount() {
		return minUsedAmount;
	}

	/**
	 * @param minUsedAmount the minUsedAmount to set
	 */
	public void setMinUsedAmount(Integer minUsedAmount) {
		this.minUsedAmount = minUsedAmount;
	}

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
	 * @return the deductions
	 */
	public Integer getDeductions() {
		return deductions;
	}

	/**
	 * @param deductions the deductions to set
	 */
	public void setDeductions(Integer deductions) {
		this.deductions = deductions;
	}

	/**
	 * @return the couponName
	 */
	public String getCouponName() {
		return couponName;
	}

	/**
	 * @param couponName the couponName to set
	 */
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	/**
	 * @return the dole
	 */
	public Integer getDole() {
		return dole;
	}

	/**
	 * @param dole the dole to set
	 */
	public void setDole(Integer dole) {
		this.dole = dole;
	}

	/**
	 * @return the canDole
	 */
	public Integer getCanDole() {
		return canDole;
	}

	/**
	 * @param canDole the canDole to set
	 */
	public void setCanDole(Integer canDole) {
		this.canDole = canDole;
	}
	
	
	
	
}
