package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.Date;

public class SOACoupon implements Serializable{


	private static final long serialVersionUID = 3574798950465885275L;

	private Integer id;
	private String couponName;
	private Integer cityId;
	private Float minUsedAmount;
	private Float deductions;
	private Integer putIntoCount;
	private Integer surplusCount;
	private String putIntoStartTime;
	private String putIntoEndTime;
	private String usedStartTime;
	private String usedEndTime;
	private Integer createUserId;
	private Date createTime;
	private Integer updateUserId;
	private Date updateTime;
	private Integer couponType;
	private Integer status;
	private Integer putType;
	private Integer canDole;
	private Integer dole;
	private String  couponDescribe;
	private Integer vipCoupon;
	private Integer businessType;
	private Integer termType;
	private Integer termDay;
	private Integer needReceive; //是否需要领取(0-不需要,1-需要)
	private Integer maximumOnePerson; //单人领取最大量



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
	public Integer getPutType() {
		return putType;
	}
	public void setPutType(Integer putType) {
		this.putType = putType;
	}
	public Integer getCanDole() {
		return canDole;
	}
	public void setCanDole(Integer canDole) {
		this.canDole = canDole;
	}
	public Integer getDole() {
		return dole;
	}
	public void setDole(Integer dole) {
		this.dole = dole;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	public Float getMinUsedAmount() {
		return minUsedAmount;
	}
	public void setMinUsedAmount(Float minUsedAmount) {
		this.minUsedAmount = minUsedAmount;
	}
	public Float getDeductions() {
		return deductions;
	}
	public void setDeductions(Float deductions) {
		this.deductions = deductions;
	}
	public Integer getPutIntoCount() {
		return putIntoCount;
	}
	public void setPutIntoCount(Integer putIntoCount) {
		this.putIntoCount = putIntoCount;
	}
	public Integer getSurplusCount() {
		return surplusCount;
	}
	public void setSurplusCount(Integer surplusCount) {
		this.surplusCount = surplusCount;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getCouponType() {
		return couponType;
	}
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPutIntoStartTime() {
		return putIntoStartTime;
	}
	public void setPutIntoStartTime(String putIntoStartTime) {
		this.putIntoStartTime = putIntoStartTime;
	}
	public String getPutIntoEndTime() {
		return putIntoEndTime;
	}
	public void setPutIntoEndTime(String putIntoEndTime) {
		this.putIntoEndTime = putIntoEndTime;
	}
	public String getUsedStartTime() {
		return usedStartTime;
	}
	public void setUsedStartTime(String usedStartTime) {
		this.usedStartTime = usedStartTime;
	}
	public String getUsedEndTime() {
		return usedEndTime;
	}
	public void setUsedEndTime(String usedEndTime) {
		this.usedEndTime = usedEndTime;
	}
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public Integer getTermType() {
		return termType;
	}
	public void setTermType(Integer termType) {
		this.termType = termType;
	}
	public Integer getTermDay() {
		return termDay;
	}
	public void setTermDay(Integer termDay) {
		this.termDay = termDay;
	}

	public Integer getNeedReceive() {
		return needReceive;
	}

	public void setNeedReceive(Integer needReceive) {
		this.needReceive = needReceive;
	}

	public Integer getMaximumOnePerson() {
		return maximumOnePerson;
	}

	public void setMaximumOnePerson(Integer maximumOnePerson) {
		this.maximumOnePerson = maximumOnePerson;
	}
}
