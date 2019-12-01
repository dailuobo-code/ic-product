package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * StandardResult<br/>
 * 描述:.
 *
 * @author huangwei
 * @since 2016-1-15<br/>
 */
public class StandardResult implements Serializable{

	/**
	 * StandardResult.java
	 */
	private static final long serialVersionUID = 6959597485492716965L;

	/** 是否成功. */
	private Boolean success;
	
	/** 错误编码. */
	private String errorCode;
	
	/** 错误信息. */
	private String errorMsg;
	
	/**
	 * Gets the default success.
	 *
	 * @return the default success
	 */
	public static StandardResult getDefaultSuccess(){
		return new StandardResult(Boolean.TRUE);
	}
	
	
	/**
	 * Gets the default failed.
	 *
	 * @param errorCode the error code
	 * @return the default failed
	 */
	public static StandardResult getDefaultFailed(String errorCode){
		return new StandardResult(Boolean.FALSE, errorCode);
	}
	
	/**
	 * Gets the default failed msg.
	 *
	 * @param errorMsg the error msg
	 * @return the default failed msg
	 */
	public static StandardResult getDefaultFailedMsg(String errorMsg){
		return new StandardResult(Boolean.FALSE, "", errorMsg);
	}
	
	

	/**
	 * Instantiates a new standard result.
	 *
	 * @param success the success
	 * @param errorCode the error code
	 */
	private StandardResult(Boolean success, String errorCode) {
		super();
		this.success = success;
		this.errorCode = errorCode;
	}



	/**
	 * Instantiates a new standard result.
	 *
	 * @param success the success
	 */
	private StandardResult(Boolean success) {
		super();
		this.success = success;
	}



	/**
	 * Instantiates a new standard result.
	 *
	 * @param success the success
	 * @param errorCode the error code
	 * @param errorMsg the error msg
	 */
	public StandardResult(Boolean success, String errorCode, String errorMsg) {
		super();
		this.success = success;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	/**
	 * Gets the success.
	 *
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}

	/**
	 * Sets the success.
	 *
	 * @param success the success to set
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 *
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Gets the error msg.
	 *
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Sets the error msg.
	 *
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
