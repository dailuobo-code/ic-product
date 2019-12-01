package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SOAAfterSales.
 */
public class SOAAfterSales implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -75232531680740345L;
	
	private Integer id;
	/** The order id. */
	private String orderId;
	
	/** The pics. */
	private String pics;
	
	/** The content. */
	private String content;
	
	/** The status. */
	private Integer status;
	
	/** The create time. */
	private String createTime;
	
	/** The reply. */
	private String reply;
	
	/** The result. */
	private String result;
	
	/** The oper user id. */
	private Integer operUserId;
	
	/** The update time. */
	private String updateTime;
	
	/** The service no. */
	private String serviceNo;
	
	/** The user id. */
	private Integer userId;
	
	/** The is del. */
	private Integer isDel;
	
	/** The phone. */
	private String phone;
	
	/** The city id. */
	private Integer cityId;
	
	private String replyPic;
	
	private Integer deliveryMode;
	
	//zyc 添加2018-03-14 用于区分订单类型  1-正常订单 2-预售订单
	private Integer orderType;
	
	private Integer createUser;
	
	private Integer type;
	
	private String saleType;

	private Integer applyType;

	
	
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public String getSaleType() {
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getOrderType() {
		return orderType;
	}
	
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
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
	 * 获取  pics.
	 *
	 * @return pics
	 */
	public String getPics() {
		return pics;
	}
	
	/**
	 * 设置 pics.
	 *
	 * @param pics the new pics
	 */
	public void setPics(String pics) {
		this.pics = pics;
	}
	
	/**
	 * 获取  content.
	 *
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * 设置 content.
	 *
	 * @param content the new content
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * 获取  createTime.
	 *
	 * @return createTime
	 */
	public String getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置 createTime.
	 *
	 * @param createTime the new creates the time
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取  reply.
	 *
	 * @return reply
	 */
	public String getReply() {
		return reply;
	}
	
	/**
	 * 设置 reply.
	 *
	 * @param reply the new reply
	 */
	public void setReply(String reply) {
		this.reply = reply;
	}
	
	/**
	 * 获取  result.
	 *
	 * @return result
	 */
	public String getResult() {
		return result;
	}
	
	/**
	 * 设置 result.
	 *
	 * @param result the new result
	 */
	public void setResult(String result) {
		this.result = result;
	}
	
	/**
	 * 获取  operUserId.
	 *
	 * @return operUserId
	 */
	public Integer getOperUserId() {
		return operUserId;
	}
	
	/**
	 * 设置 operUserId.
	 *
	 * @param operUserId the new oper user id
	 */
	public void setOperUserId(Integer operUserId) {
		this.operUserId = operUserId;
	}
	
	/**
	 * 获取  updateTime.
	 *
	 * @return updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * 设置 updateTime.
	 *
	 * @param updateTime the new update time
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SOAAfterSales [\n orderId=" + orderId + "\n pics=" + pics + "\n content=" + content + "\n status="
				+ status + "\n createTime=" + createTime + "\n reply=" + reply + "\n result=" + result
				+ "\n operUserId=" + operUserId + "\n updateTime=" + updateTime + "\n]";
	}

	/**
	 * 获取  serviceNo.
	 *
	 * @return serviceNo
	 */
	public String getServiceNo() {
		return serviceNo;
	}

	/**
	 * 设置 serviceNo.
	 *
	 * @param serviceNo the new service no
	 */
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
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
	 * 获取  isDel
	 * @return isDel
	 */
	public Integer getIsDel() {
		return isDel;
	}

	/**
	 * 设置 isDel
	 * @param isDel
	 */
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	/**
	 * 获取  phone
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置 phone
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取  cityId
	 * @return cityId
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * 设置 cityId
	 * @param cityId
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * 获取  replyPic
	 * @return replyPic
	 */
	public String getReplyPic() {
		return replyPic;
	}

	/**
	 * 设置 replyPic
	 * @param replyPic
	 */
	public void setReplyPic(String replyPic) {
		this.replyPic = replyPic;
	}

	/**
	 * 获取  deliveryMode
	 * @return deliveryMode
	 */
	public Integer getDeliveryMode() {
		return deliveryMode;
	}

	/**
	 * 设置 deliveryMode
	 * @param deliveryMode
	 */
	public void setDeliveryMode(Integer deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApplyType() {
		return applyType;
	}

	public void setApplyType(Integer applyType) {
		this.applyType = applyType;
	}
}
