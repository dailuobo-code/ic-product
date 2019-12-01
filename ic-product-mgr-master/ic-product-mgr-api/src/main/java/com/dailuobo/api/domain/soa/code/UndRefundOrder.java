package com.dailuobo.api.domain.soa.code;

// TODO: Auto-generated Javadoc

import java.io.Serializable;

/**
 * The Class RefundOrder.
 * 退款订单
 * @author Aministartor
 */
public class UndRefundOrder implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;
	
	/** The order id. */
	private String undOrderId;
	
	/** The out trade no. */
	private String outTradeNo;
	
	/** The out refund no. */
	private String outRefundNo;
	
	/** The transaction id. */
	private String transactionId;
	
	/** The total fee. */
	private Integer totalFee;
	
	/** The refund fee. */
	private Integer refundFee;
	
	/** The user id. */
	private Integer userId;
	
	/** The refund id. */
	private String refundId;
	
	/** The refund type. */
	private String refundType;
	
	/** The syn refund status. */
	private String synRefundStatus;
	
	/** The asyn refund status. */
	private String asynRefundStatus;
	
	/** The query refund status. */
	private String queryRefundStatus;
	
	/** The status. */
	private Integer status;
	
	/** The err msg. */
	private String errMsg;
	
	/** The notify id. */
	private String notifyId;
	
	
	/**
	 * 获取  outTradeNo.
	 *
	 * @return outTradeNo
	 */
	public String getOutTradeNo() {
		return outTradeNo;
	}
	
	/**
	 * 设置 outTradeNo.
	 *
	 * @param outTradeNo the new out trade no
	 */
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	/**
	 * 获取  outRefundNo.
	 *
	 * @return outRefundNo
	 */
	public String getOutRefundNo() {
		return outRefundNo;
	}
	
	/**
	 * 设置 outRefundNo.
	 *
	 * @param outRefundNo the new out refund no
	 */
	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}
	
	/**
	 * 获取  transactionId.
	 *
	 * @return transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}
	
	/**
	 * 设置 transactionId.
	 *
	 * @param transactionId the new transaction id
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	/**
	 * 获取  totalFee.
	 *
	 * @return totalFee
	 */
	public Integer getTotalFee() {
		return totalFee;
	}
	
	/**
	 * 设置 totalFee.
	 *
	 * @param totalFee the new total fee
	 */
	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}
	
	/**
	 * 获取  refundFee.
	 *
	 * @return refundFee
	 */
	public Integer getRefundFee() {
		return refundFee;
	}
	
	/**
	 * 设置 refundFee.
	 *
	 * @param refundFee the new refund fee
	 */
	public void setRefundFee(Integer refundFee) {
		this.refundFee = refundFee;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RefundOrder [\n undOrderId=" + undOrderId + "\n outTradeNo=" + outTradeNo + "\n outRefundNo=" + outRefundNo
				+ "\n transactionId=" + transactionId + "\n totalFee=" + totalFee + "\n refundFee=" + refundFee + "\n]";
	}

	
	public String getUndOrderId() {
		return undOrderId;
	}

	public void setUndOrderId(String undOrderId) {
		this.undOrderId = undOrderId;
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
	 * 获取  refundId.
	 *
	 * @return refundId
	 */
	public String getRefundId() {
		return refundId;
	}

	/**
	 * 设置 refundId.
	 *
	 * @param refundId the new refund id
	 */
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	/**
	 * 获取  refundType.
	 *
	 * @return refundType
	 */
	public String getRefundType() {
		return refundType;
	}

	/**
	 * 设置 refundType.
	 *
	 * @param refundType the new refund type
	 */
	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}

	/**
	 * 获取  synRefundStatus.
	 *
	 * @return synRefundStatus
	 */
	public String getSynRefundStatus() {
		return synRefundStatus;
	}

	/**
	 * 设置 synRefundStatus.
	 *
	 * @param synRefundStatus the new syn refund status
	 */
	public void setSynRefundStatus(String synRefundStatus) {
		this.synRefundStatus = synRefundStatus;
	}

	/**
	 * 获取  asynRefundStatus.
	 *
	 * @return asynRefundStatus
	 */
	public String getAsynRefundStatus() {
		return asynRefundStatus;
	}

	/**
	 * 设置 asynRefundStatus.
	 *
	 * @param asynRefundStatus the new asyn refund status
	 */
	public void setAsynRefundStatus(String asynRefundStatus) {
		this.asynRefundStatus = asynRefundStatus;
	}

	/**
	 * 获取  queryRefundStatus.
	 *
	 * @return queryRefundStatus
	 */
	public String getQueryRefundStatus() {
		return queryRefundStatus;
	}

	/**
	 * 设置 queryRefundStatus.
	 *
	 * @param queryRefundStatus the new query refund status
	 */
	public void setQueryRefundStatus(String queryRefundStatus) {
		this.queryRefundStatus = queryRefundStatus;
	}

	/**
	 * 获取  status.
	 *
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置 status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取  errMsg.
	 *
	 * @return errMsg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * 设置 errMsg.
	 *
	 * @param errMsg the new err msg
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	/**
	 * 获取  notifyId.
	 *
	 * @return notifyId
	 */
	public String getNotifyId() {
		return notifyId;
	}

	/**
	 * 设置 notifyId.
	 *
	 * @param notifyId the new notify id
	 */
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}
}
