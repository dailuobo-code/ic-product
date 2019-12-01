package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SOAPrepayOrder.
 */
public class SOAUndPrepayOrder implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7765059753935196310L;
	
	/** The user id. */
	private Integer userId;
	
	/** The out trade no. */
	private String outTradeNo;
	
	/** The order id. */
	private String undOrderId;
	
	/** The prepay id. */
	private String prepayId;
	
	/** The thirdparty order id. */
	private String thirdpartyOrderId;
	
	/** The thirdparty user id. */
	private String thirdpartyUserId;
	
	/** The notify id. */
	private String notifyId;
	
	/** The pay price. */
	private Integer payPrice;
	
	/** The pay time. */
	private String payTime;
	
	/** The pay type. */
	private String payType;
	
	/** The pay status. */
	private String payStatus;
	
	/** The err msg. */
	private String errMsg;
	
	/** The syn trade status. */
	private String synTradeStatus;
	
	/** The asyn trade status. */
	private String asynTradeStatus;
	
	/** The query trade status. */
	private String queryTradeStatus;
	
	/** The create time. */
	private String createTime;
	
	/** The update time. */
	private String updateTime;
	
	/** 主要是为了保证mongodb第一次写入有数据 */
	public SOAUndPrepayOrder() {
		prepayId = "";
		thirdpartyOrderId = "";
		thirdpartyUserId = "";
		notifyId = "";
		payTime = "";
		payStatus = "";
		errMsg = "";
		synTradeStatus = "";
		asynTradeStatus = "";
		queryTradeStatus = "";
	}

	/**
	 * 获取  userId
	 * @return userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置 userId
	 * @param userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获取  outTradeNo
	 * @return outTradeNo
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}

	/**
	 * 设置 outTradeNo
	 * @param outTradeNo
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}


	public String getUndOrderId() {
		return undOrderId;
	}

	public void setUndOrderId(String undOrderId) {
		this.undOrderId = undOrderId;
	}

	/**
	 * 获取  prepayId
	 * @return prepayId
	 */
	public String getPrepayId() {
		return prepayId;
	}

	/**
	 * 设置 prepayId
	 * @param prepayId
	 */
	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	/**
	 * 获取  thirdpartyOrderId
	 * @return thirdpartyOrderId
	 */
	public String getThirdpartyOrderId() {
		return thirdpartyOrderId;
	}

	/**
	 * 设置 thirdpartyOrderId
	 * @param thirdpartyOrderId
	 */
	public void setThirdpartyOrderId(String thirdpartyOrderId) {
		this.thirdpartyOrderId = thirdpartyOrderId;
	}

	/**
	 * 获取  thirdpartyUserId
	 * @return thirdpartyUserId
	 */
	public String getThirdpartyUserId() {
		return thirdpartyUserId;
	}

	/**
	 * 设置 thirdpartyUserId
	 * @param thirdpartyUserId
	 */
	public void setThirdpartyUserId(String thirdpartyUserId) {
		this.thirdpartyUserId = thirdpartyUserId;
	}

	/**
	 * 获取  notifyId
	 * @return notifyId
	 */
	public String getNotifyId() {
		return notifyId;
	}

	/**
	 * 设置 notifyId
	 * @param notifyId
	 */
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	/**
	 * 获取  payPrice
	 * @return payPrice
	 */
	public Integer getPayPrice() {
		return payPrice;
	}

	/**
	 * 设置 payPrice
	 * @param payPrice
	 */
	public void setPayPrice(Integer payPrice) {
		this.payPrice = payPrice;
	}

	/**
	 * 获取  payTime
	 * @return payTime
	 */
	public String getPayTime() {
		return payTime;
	}

	/**
	 * 设置 payTime
	 * @param payTime
	 */
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	/**
	 * 获取  payType
	 * @return payType
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * 设置 payType
	 * @param payType
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * 获取  payStatus
	 * @return payStatus
	 */
	public String getPayStatus() {
		return payStatus;
	}

	/**
	 * 设置 payStatus
	 * @param payStatus
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
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
	 * 获取  synTradeStatus
	 * @return synTradeStatus
	 */
	public String getSynTradeStatus() {
		return synTradeStatus;
	}

	/**
	 * 设置 synTradeStatus
	 * @param synTradeStatus
	 */
	public void setSynTradeStatus(String synTradeStatus) {
		this.synTradeStatus = synTradeStatus;
	}

	/**
	 * 获取  asynTradeStatus
	 * @return asynTradeStatus
	 */
	public String getAsynTradeStatus() {
		return asynTradeStatus;
	}

	/**
	 * 设置 asynTradeStatus
	 * @param asynTradeStatus
	 */
	public void setAsynTradeStatus(String asynTradeStatus) {
		this.asynTradeStatus = asynTradeStatus;
	}

	/**
	 * 获取  queryTradeStatus
	 * @return queryTradeStatus
	 */
	public String getQueryTradeStatus() {
		return queryTradeStatus;
	}

	/**
	 * 设置 queryTradeStatus
	 * @param queryTradeStatus
	 */
	public void setQueryTradeStatus(String queryTradeStatus) {
		this.queryTradeStatus = queryTradeStatus;
	}

	/**
	 * 获取  createTime
	 * @return createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取  updateTime
	 * @return updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置 updateTime
	 * @param updateTime
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
