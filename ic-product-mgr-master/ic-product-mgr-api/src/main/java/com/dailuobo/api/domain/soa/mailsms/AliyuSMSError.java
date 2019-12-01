package com.dailuobo.api.domain.soa.mailsms;

import java.io.Serializable;

/**
 * ErrorCode<br/>
 * 描述: 阿里大鱼的错误码返回
 * @author huangwei
 * @since 2016-1-15<br/>
 */
public class AliyuSMSError implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;

	/** code代码 */
	private String code;
	
	/** 错误信息描述 */
	private String errorMsg;
	
	/** 解决方案 */
	private String solution;
	
	

	/**
	 * @param code
	 * @param errorMsg
	 * @param solution
	 */
	public AliyuSMSError(String code, String errorMsg, String solution) {
		super();
		this.code = code;
		this.errorMsg = errorMsg;
		this.solution = solution;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the solution
	 */
	public String getSolution() {
		return solution;
	}

	/**
	 * @param solution the solution to set
	 */
	public void setSolution(String solution) {
		this.solution = solution;
	}
	
	
}
