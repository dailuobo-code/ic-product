/**
 * UserCoupon.java
 * @author huangwei
 * @since 2016-4-29
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * UserCoupon.java
 * @author huangwei
 * @since 2016-4-29
 *  描述：
 */
public class SOAUserCoupon implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7843252021959657762L;

	/** The user coupon id. */
	private Integer id;
	
	/** The classify id. */
	private Integer couponId;
	
	/** The city id. */
	private Integer cityId;
	
	/** The user id. */
	private Integer userId;
	
	/** The obtain type.1:自抢； 2:赠送 */
	private Integer obtainType;
	
	/** The status.0：未使用； 1：己锁定；2:己使用 */
	private Integer status;
	
	/** The coupon name. */
	private String couponName;
	
	/** The deductions. */
	private Float deductions;
	
	/** The min used amount. */
	private Float minUsedAmount;
	
	/** The used start time. */
	private Date usedStartTime;
	
	/** The Used end time. */
	private Date UsedEndTime;
	
	private Integer couponType;
	
	private String couponDescribe;
	
	private Integer vipCoupon;
	
	private Integer isShow;//默认0：未显示，1：已显示
	
	
	
	
	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getVipCoupon() {
		return vipCoupon;
	}

	public void setVipCoupon(Integer vipCoupon) {
		this.vipCoupon = vipCoupon;
	}

	public String getCouponDescribe() {
		return couponDescribe;
	}

	public void setCouponDescribe(String couponDescribe) {
		this.couponDescribe = couponDescribe;
	}

	/**
	 * 使用时间
	 */
	private Date usedTime;

	private String phone;

	public Integer getCouponType() {
		return couponType;
	}

	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	/**
	 * Gets the city id.
	 *
	 * @return the cityId
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * Sets the city id.
	 *
	 * @param cityId the cityId to set
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * Gets the obtain type.
	 *
	 * @return the obtainType
	 */
	public Integer getObtainType() {
		return obtainType;
	}

	/**
	 * Sets the obtain type.
	 *
	 * @param obtainType the obtainType to set
	 */
	public void setObtainType(Integer obtainType) {
		this.obtainType = obtainType;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * Gets the coupon name.
	 *
	 * @return the couponName
	 */
	public String getCouponName() {
		return couponName;
	}

	/**
	 * Sets the coupon name.
	 *
	 * @param couponName the couponName to set
	 */
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	


	public Float getDeductions() {
		return deductions;
	}

	public void setDeductions(Float deductions) {
		this.deductions = deductions;
	}

	public Float getMinUsedAmount() {
		return minUsedAmount;
	}

	public void setMinUsedAmount(Float minUsedAmount) {
		this.minUsedAmount = minUsedAmount;
	}

	public Date getUsedStartTime() {
		return usedStartTime;
	}

	public void setUsedStartTime(Date usedStartTime) {
		this.usedStartTime = usedStartTime;
	}

	public Date getUsedEndTime() {
		return UsedEndTime;
	}

	public void setUsedEndTime(Date usedEndTime) {
		UsedEndTime = usedEndTime;
	}

	/**
	 * @return the usedTime
	 */
	public Date getUsedTime() {
		return usedTime;
	}

	/**
	 * @param usedTime the usedTime to set
	 */
	public void setUsedTime(Date usedTime) {
		this.usedTime = usedTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

}
