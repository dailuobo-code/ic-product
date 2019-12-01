/**
 * UserCoupon.java
 * @author huangwei
 * @since 2016-4-29
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.Date;

public class SOAUserCouponRelation implements Serializable{


	private Integer id;
	private Integer cityId;
	private Integer storeId;
	private Integer userId;
	private String directCouponId;
	private String usedCouponId;
	private Date createTime;
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getDirectCouponId() {
		return directCouponId;
	}

	public void setDirectCouponId(String directCouponId) {
		this.directCouponId = directCouponId;
	}

	public String getUsedCouponId() {
		return usedCouponId;
	}

	public void setUsedCouponId(String usedCouponId) {
		this.usedCouponId = usedCouponId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
