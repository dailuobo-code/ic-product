package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SOAExceptionOrder.
 */
public class SOAExceptionOrder implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2184738156833816703L;
	
	/** The order id. */
	private String orderId; 
	
	/** The user id. */
	private Integer userId; 
	
	/** The city id. */
	private Integer cityId; 
	
	/** The oper type. */
	private Integer operType; 
	
	/** The params. */
	private String params;
	
	/** The err msg. */
	private String errMsg;
	
	/** The store id. */
	private Integer storeId;
	
	/**
	 * 获取  orderId.
	 *
	 * @return orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	
	/**
	 * 设置 orderId.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 获取  userId.
	 *
	 * @return userId
	 */
	public Integer getUserId() {
		return userId;
	}
	
	/**
	 * 设置 userId.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取  cityId.
	 *
	 * @return cityId
	 */
	public Integer getCityId() {
		return cityId;
	}
	
	/**
	 * 设置 cityId.
	 *
	 * @param cityId the new city id
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	/**
	 * 获取  operType.
	 *
	 * @return operType
	 */
	public Integer getOperType() {
		return operType;
	}
	
	/**
	 * 设置 operType.
	 *
	 * @param operType the new oper type
	 */
	public void setOperType(Integer operType) {
		this.operType = operType;
	}
	
	/**
	 * 获取  params.
	 *
	 * @return params
	 */
	public String getParams() {
		return params;
	}
	
	/**
	 * 设置 params.
	 *
	 * @param params the new params
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * 获取  errMsg
	 * @return errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * 设置 errMsg
	 * @param errMsg
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
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
}
