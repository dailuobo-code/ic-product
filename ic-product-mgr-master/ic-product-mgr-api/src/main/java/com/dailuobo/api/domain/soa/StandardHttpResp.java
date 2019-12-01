package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class StandardHttpResp.
 */
public class StandardHttpResp implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7213864541974364012L;

	/**  请求是否成功标记. */
	private Boolean isSuccess;
	
	/**  响应报文. */
	private String responseMsg;
	
	/**  异常信息. */
	private String reasonPhrase;
	
	/**  响应编码. */
	private String responseCode;
	
	/**
	 * Gets the checks if is success.
	 *
	 * @return the checks if is success
	 */
	public Boolean getIsSuccess() {
		return isSuccess;
	}
	
	/**
	 * Sets the checks if is success.
	 *
	 * @param isSuccess the new checks if is success
	 */
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	/**
	 * Gets the response msg.
	 *
	 * @return the response msg
	 */
	public String getResponseMsg() {
		return responseMsg;
	}
	
	/**
	 * Sets the response msg.
	 *
	 * @param responseMsg the new response msg
	 */
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	
	/**
	 * Gets the reason phrase.
	 *
	 * @return the reason phrase
	 */
	public String getReasonPhrase() {
		return reasonPhrase;
	}
	
	/**
	 * Sets the reason phrase.
	 *
	 * @param reasonPhrase the new reason phrase
	 */
	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}
	
	/**
	 * Gets the response code.
	 *
	 * @return the response code
	 */
	public String getResponseCode() {
		return responseCode;
	}
	
	/**
	 * Sets the response code.
	 *
	 * @param responseCode the new response code
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	
	/**
	 * Gets the default success.
	 *
	 * @param code the code
	 * @param msg the msg
	 * @return the default success
	 */
	public static StandardHttpResp getDefaultSuccess(Integer code, String msg) {
		StandardHttpResp resp = new StandardHttpResp();
		resp.setIsSuccess(Boolean.TRUE);
		resp.setResponseMsg(msg);
		resp.setResponseCode(String.valueOf(code));
		return resp;
	}
	
	/**
	 * Gets the default fail.
	 *
	 * @param code the code
	 * @param errMsg the err msg
	 * @return the default fail
	 */
	public static StandardHttpResp getDefaultFail(Integer code, String errMsg) {
		StandardHttpResp resp = new StandardHttpResp();
		resp.setIsSuccess(Boolean.FALSE);
		resp.setResponseMsg(errMsg);
		resp.setResponseCode(String.valueOf(code));
		return resp;
	}
}
