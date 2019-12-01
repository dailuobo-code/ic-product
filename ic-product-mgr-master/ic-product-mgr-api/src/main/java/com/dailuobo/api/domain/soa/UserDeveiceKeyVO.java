package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.Date;


/**
 * UserDeveiceVO<br/>
 * 描述:获取UserDevice的有效性及userId<br/>
 * 如果isValidate为false，则用户虽然登录，但可能己超时，需要重新登录<br/>
 * @author huangwei
 * @since 2016-1-5<br/>
 */
public class UserDeveiceKeyVO implements Serializable{
	
	/**
	 * UserDeveiceKeyVO.java
	 */
	private static final long serialVersionUID = -259412952707513574L;

	/** 无登录失效时间限制 */
	public final static Long NO_LIMIT_TIME = -1L;

	/** 用户id	 */
	private Integer userId;
	
	/** 登录时间 */
	private Date loginTime;
	
	/** 登录有效时长（单位为：秒) */
	private Long validateDuration;


	/**
	 * @return the loginTime
	 */
	public Date getLoginTime() {
		return loginTime;
	}

	/**
	 * @param loginTime the loginTime to set
	 */
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @return the validateDuration
	 */
	public Long getValidateDuration() {
		return validateDuration;
	}

	/**
	 * @param validateDuration the validateDuration to set
	 */
	public void setValidateDuration(Long validateDuration) {
		this.validateDuration = validateDuration;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
