package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * ValidateCodeVO<br/>
 * 描述:
 * @author huangwei
 * @since 2016-1-19<br/>
 */
public class ValidateCodeVO implements Serializable{

	/**
	 * ValidateCodeVO.java
	 */
	private static final long serialVersionUID = -853690335573335872L;

	/** 验证码 */
	private String validateCode;
	
	/** 验证码失效时间 */
	private Integer invalidTime;
	
	/** 最后发送短信的时间 */
	private long lastSendSMSTime;

	/**
	 * @return the validateCode
	 */
	public String getValidateCode() {
		return validateCode;
	}

	/**
	 * @param validateCode the validateCode to set
	 */
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	/**
	 * @return the invalidTime
	 */
	public Integer getInvalidTime() {
		return invalidTime;
	}

	/**
	 * @param invalidTime the invalidTime to set
	 */
	public void setInvalidTime(Integer invalidTime) {
		this.invalidTime = invalidTime;
	}

	/**
	 * @return the lastSendSMSTime
	 */
	public long getLastSendSMSTime() {
		return lastSendSMSTime;
	}

	/**
	 * @param lastSendSMSTime the lastSendSMSTime to set
	 */
	public void setLastSendSMSTime(long lastSendSMSTime) {
		this.lastSendSMSTime = lastSendSMSTime;
	}
}
