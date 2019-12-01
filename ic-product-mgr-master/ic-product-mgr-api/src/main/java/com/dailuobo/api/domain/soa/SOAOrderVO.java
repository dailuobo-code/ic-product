package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SOAOrderVO.
 */
public class SOAOrderVO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2823478639222002603L;
	
	/**  订单ID. */
	private String orderId;
	
	/**  门店ID. */
	private Integer storeId;
	
	/**  用户ID. */
	private Integer userId;
	
	/**  订单名称. */
	private String orderName;
	
	/**  订单状态. */
	private Integer status;
	
	/**  第三方支付异步通知状态  1 异常(未收到第三方异步通知或异步通知结果与同步结果不一致)   2 正常(第三方支付异步结果已返回且异步通知结果与同步结果一致). */
	private String asynPayStatus;
	
	/** The pickup code. */
	private String pickupCode;
	
	/**  订单图片. */
	private String orderPic;
	
	/**  付款时间. */
	private String payTime;
	
	/**  生成时间. */
	private String generateTime;
	
	/**  关闭时间. */
	private String closeTime;
	
	/**  取货日期. */
	private String pickupTime;
	
	/**  订单实付金额. */
	private Integer orderPrice;
	
	/**   订单总价. */
	private Integer totalPrice;
	
	/**  创建记录用户id. */
	private Integer createUserId;
	
	/**  创建时间. */
	private String createTime;
	
	/**  更新记录用户id. */
	private Integer updateUserId;
	
	/**  更新记录时间. */
	private String updateTime;
	
	/**  取货开始时间. */
	private String pickupStartTime;
	
	/**  取货结束时间. */
	private String pickupEndTime;
	
	/**  备注. */
	private String memo;

	/**  城市id. */
	private Integer cityId;
	
	/**  逻辑删除标记  0 正常  1为删除状态. */
	private String isDel;
	
	/**  取菜方式 0 自提  1代提. */
	private Integer pickupTypeId;
	
	/**  是否愿意帮人代提(0 不愿意,1愿意). */
	private Integer isWilling;
	
	/**  订单重量. */
	private Float weight;
	
	/**  余额扣除数. */
	private Integer balance;
	
	/**  失效时间的毫秒数. */
	private Long overTimeMills;
	
	/**  扩展字段. */
	private String extras;
	/** The evaluate content. */
	private String evaluateContent;
	
	/** The coupon id. */
	private Integer couponId;
	
	/** The coupon desc. */
	private String couponDesc;
	
	/** The refund status. */
	private Integer refundStatus;
	
	/** The pickup time id. */
	private Integer pickupTimeId;
	
	/** The change. */
	private Integer change;// 称重找零
	
	/** The version. */
	private Integer version;//订单版本号
	
	/** The pay type. */
	private Integer payType;
	
	/** The complete user id. */
	private Integer completeUserId;
	
	/** The complete time. */
	private String completeTime;
	
	/** The child status. */
	private Integer childStatus; 
	
	/** The coupon value. */
	private Float couponValue;
	
	private String payCompleteTime;
	
	private Integer deliveryMode;
	private String recipients;
	private String recipientsPhone;
	private String recipientsAddr;
	private Integer freight;
	
	private Integer warehouseId;
	
	private Integer residenceId;
	private String residenceName;
	private Integer limitFee;
	private String limitFeeDesc;
	
	private Integer replyStatus;
	
	private Integer orderType;  //订单类型 1-正常  2-预售   朱雨晨添加于 2018-02-27
	
	private Integer presellId;

	private Integer groupId; //团购订单的id

	public Integer getPresellId() {
		return presellId;
	}

	public void setPresellId(Integer presellId) {
		this.presellId = presellId;
	}

	/**
	 * Instantiates a new SOA order vo.
	 */
	public SOAOrderVO() {
		this.payTime = "";
		this.closeTime = "";
		this.pickupCode = "";
		this.memo = "";
		this.pickupTime = "";
		this.pickupStartTime = "";
		this.pickupEndTime = "";
		this.extras = "";
		this.evaluateContent = "";
	}
	
	/**
	 * 获取  orderType.
	 *
	 * @return orderId
	 */
	public Integer getOrderType() {
		return orderType;
	}
	/**
	 * 设置 orderType.
	 *
	 * @param orderId the new order id
	 */
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
	 * 获取  storeId.
	 *
	 * @return storeId
	 */
	public Integer getStoreId() {
		return storeId;
	}

	/**
	 * 设置 storeId.
	 *
	 * @param storeId the new store id
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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
	 * 获取  orderName.
	 *
	 * @return orderName
	 */
	public String getOrderName() {
		return orderName;
	}

	/**
	 * 设置 orderName.
	 *
	 * @param orderName the new order name
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
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
	 * 获取  orderPic.
	 *
	 * @return orderPic
	 */
	public String getOrderPic() {
		return orderPic;
	}

	/**
	 * 设置 orderPic.
	 *
	 * @param orderPic the new order pic
	 */
	public void setOrderPic(String orderPic) {
		this.orderPic = orderPic;
	}

	
	/**
	 * 获取  payTime.
	 *
	 * @return payTime
	 */
	public String getPayTime() {
		return payTime;
	}

	/**
	 * 设置 payTime.
	 *
	 * @param payTime the new pay time
	 */
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	
	/**
	 * 获取  generateTime.
	 *
	 * @return generateTime
	 */
	public String getGenerateTime() {
		return generateTime;
	}

	/**
	 * 设置 generateTime.
	 *
	 * @param generateTime the new generate time
	 */
	public void setGenerateTime(String generateTime) {
		this.generateTime = generateTime;
	}

	
	/**
	 * 获取  closeTime.
	 *
	 * @return closeTime
	 */
	public String getCloseTime() {
		return closeTime;
	}

	/**
	 * 设置 closeTime.
	 *
	 * @param closeTime the new close time
	 */
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	
	/**
	 * 获取  pickupTime.
	 *
	 * @return pickupTime
	 */
	public String getPickupTime() {
		return pickupTime;
	}

	/**
	 * 设置 pickupTime.
	 *
	 * @param pickupTime the new pickup time
	 */
	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}

	
	/**
	 * 获取  orderPrice.
	 *
	 * @return orderPrice
	 */
	public Integer getOrderPrice() {
		return orderPrice;
	}

	/**
	 * 设置 orderPrice.
	 *
	 * @param orderPrice the new order price
	 */
	public void setOrderPrice(Integer orderPrice) {
		this.orderPrice = orderPrice;
	}

	
	/**
	 * 获取  createUserId.
	 *
	 * @return createUserId
	 */
	public Integer getCreateUserId() {
		return createUserId;
	}

	/**
	 * 设置 createUserId.
	 *
	 * @param createUserId the new creates the user id
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
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
	 * 获取  updateUserId.
	 *
	 * @return updateUserId
	 */
	public Integer getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 设置 updateUserId.
	 *
	 * @param updateUserId the new update user id
	 */
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
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

	
	/**
	 * 获取  pickupStartTime.
	 *
	 * @return pickupStartTime
	 */
	public String getPickupStartTime() {
		return pickupStartTime;
	}

	/**
	 * 设置 pickupStartTime.
	 *
	 * @param pickupStartTime the new pickup start time
	 */
	public void setPickupStartTime(String pickupStartTime) {
		this.pickupStartTime = pickupStartTime;
	}

	
	/**
	 * 获取  pickupEndTime.
	 *
	 * @return pickupEndTime
	 */
	public String getPickupEndTime() {
		return pickupEndTime;
	}

	/**
	 * 设置 pickupEndTime.
	 *
	 * @param pickupEndTime the new pickup end time
	 */
	public void setPickupEndTime(String pickupEndTime) {
		this.pickupEndTime = pickupEndTime;
	}

	
	/**
	 * 获取  memo.
	 *
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置 memo.
	 *
	 * @param memo the new memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	
	/**
	 * 获取  totalPrice.
	 *
	 * @return totalPrice
	 */
	public Integer getTotalPrice() {
		return totalPrice;
	}

	/**
	 * 设置 totalPrice.
	 *
	 * @param totalPrice the new total price
	 */
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
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
	 * 获取  isDel.
	 *
	 * @return isDel
	 */
	public String getIsDel() {
		return isDel;
	}

	/**
	 * 设置 isDel.
	 *
	 * @param isDel the new checks if is del
	 */
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	/**
	 * 获取  asynPayStatus.
	 *
	 * @return asynPayStatus
	 */
	public String getAsynPayStatus() {
		return asynPayStatus;
	}

	/**
	 * 设置 asynPayStatus.
	 *
	 * @param asynPayStatus the new asyn pay status
	 */
	public void setAsynPayStatus(String asynPayStatus) {
		this.asynPayStatus = asynPayStatus;
	}

	/**
	 * 获取  pickupCode.
	 *
	 * @return pickupCode
	 */
	public String getPickupCode() {
		return pickupCode;
	}

	/**
	 * 设置 pickupCode.
	 *
	 * @param pickupCode the new pickup code
	 */
	public void setPickupCode(String pickupCode) {
		this.pickupCode = pickupCode;
	}

	/**
	 * 获取  pickupTypeId.
	 *
	 * @return pickupTypeId
	 */
	public Integer getPickupTypeId() {
		return pickupTypeId;
	}

	/**
	 * 设置 pickupTypeId.
	 *
	 * @param pickupTypeId the new pickup type id
	 */
	public void setPickupTypeId(Integer pickupTypeId) {
		this.pickupTypeId = pickupTypeId;
	}

	/**
	 * 获取  isWilling.
	 *
	 * @return isWilling
	 */
	public Integer getIsWilling() {
		return isWilling;
	}

	/**
	 * 设置 isWilling.
	 *
	 * @param isWilling the new checks if is willing
	 */
	public void setIsWilling(Integer isWilling) {
		this.isWilling = isWilling;
	}

	/**
	 * 获取  weight.
	 *
	 * @return weight
	 */
	public Float getWeight() {
		return weight;
	}

	/**
	 * 设置 weight.
	 *
	 * @param weight the new weight
	 */
	public void setWeight(Float weight) {
		this.weight = weight;
	}

	/**
	 * 获取  balance.
	 *
	 * @return balance
	 */
	public Integer getBalance() {
		return balance;
	}

	/**
	 * 设置 balance.
	 *
	 * @param balance the new balance
	 */
	public void setBalance(Integer balance) {
		this.balance = balance;
	}


	/**
	 * 获取  overTimeMills.
	 *
	 * @return overTimeMills
	 */
	public Long getOverTimeMills() {
		return overTimeMills;
	}

	/**
	 * 设置 overTimeMills.
	 *
	 * @param overTimeMills the new over time mills
	 */
	public void setOverTimeMills(Long overTimeMills) {
		this.overTimeMills = overTimeMills;
	}

	/**
	 * 获取  extras.
	 *
	 * @return extras
	 */
	public String getExtras() {
		return extras;
	}

	/**
	 * 设置 extras.
	 *
	 * @param extras the new extras
	 */
	public void setExtras(String extras) {
		this.extras = extras;
	}

	/**
	 * 获取  evaluateContent.
	 *
	 * @return evaluateContent
	 */
	public String getEvaluateContent() {
		return evaluateContent;
	}

	/**
	 * 设置 evaluateContent.
	 *
	 * @param evaluateContent the new evaluate content
	 */
	public void setEvaluateContent(String evaluateContent) {
		this.evaluateContent = evaluateContent;
	}

	/**
	 * 获取  couponId.
	 *
	 * @return couponId
	 */
	public Integer getCouponId() {
		return couponId;
	}

	/**
	 * 设置 couponId.
	 *
	 * @param couponId the new coupon id
	 */
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	/**
	 * 获取  couponDesc.
	 *
	 * @return couponDesc
	 */
	public String getCouponDesc() {
		return couponDesc;
	}

	/**
	 * 设置 couponDesc.
	 *
	 * @param couponDesc the new coupon desc
	 */
	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}

	/**
	 * 获取  refundStatus.
	 *
	 * @return refundStatus
	 */
	public Integer getRefundStatus() {
		return refundStatus;
	}

	/**
	 * 设置 refundStatus.
	 *
	 * @param refundStatus the new refund status
	 */
	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	/**
	 * 获取  pickupTimeId.
	 *
	 * @return pickupTimeId
	 */
	public Integer getPickupTimeId() {
		return pickupTimeId;
	}

	/**
	 * 设置 pickupTimeId.
	 *
	 * @param pickupTimeId the new pickup time id
	 */
	public void setPickupTimeId(Integer pickupTimeId) {
		this.pickupTimeId = pickupTimeId;
	}

	/**
	 * 获取  change.
	 *
	 * @return change
	 */
	public Integer getChange() {
		return change;
	}

	/**
	 * 设置 change.
	 *
	 * @param change the new change
	 */
	public void setChange(Integer change) {
		this.change = change;
	}

	/**
	 * 获取  version.
	 *
	 * @return version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * 设置 version.
	 *
	 * @param version the new version
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * 获取  payType.
	 *
	 * @return payType
	 */
	public Integer getPayType() {
		return payType;
	}

	/**
	 * 设置 payType.
	 *
	 * @param payType the new pay type
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	/**
	 * 获取  completeUserId.
	 *
	 * @return completeUserId
	 */
	public Integer getCompleteUserId() {
		return completeUserId;
	}

	/**
	 * 设置 completeUserId.
	 *
	 * @param completeUserId the new complete user id
	 */
	public void setCompleteUserId(Integer completeUserId) {
		this.completeUserId = completeUserId;
	}

	/**
	 * 获取  completeTime.
	 *
	 * @return completeTime
	 */
	public String getCompleteTime() {
		return completeTime;
	}

	/**
	 * 设置 completeTime.
	 *
	 * @param completeTime the new complete time
	 */
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	/**
	 * 获取  childStatus
	 * @return childStatus
	 */
	public Integer getChildStatus() {
		return childStatus;
	}

	/**
	 * 设置 childStatus
	 * @param childStatus
	 */
	public void setChildStatus(Integer childStatus) {
		this.childStatus = childStatus;
	}

	/**
	 * 获取  couponValue
	 * @return couponValue
	 */
	public Float getCouponValue() {
		return couponValue;
	}

	/**
	 * 设置 couponValue
	 * @param couponValue
	 */
	public void setCouponValue(Float couponValue) {
		this.couponValue = couponValue;
	}

	/**
	 * 获取  payCompleteTime
	 * @return payCompleteTime
	 */
	public String getPayCompleteTime() {
		return payCompleteTime;
	}

	/**
	 * 设置 payCompleteTime
	 * @param payCompleteTime
	 */
	public void setPayCompleteTime(String payCompleteTime) {
		this.payCompleteTime = payCompleteTime;
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

	/**
	 * 获取  recipients
	 * @return recipients
	 */
	public String getRecipients() {
		return recipients;
	}

	/**
	 * 设置 recipients
	 * @param recipients
	 */
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	/**
	 * 获取  recipientsPhone
	 * @return recipientsPhone
	 */
	public String getRecipientsPhone() {
		return recipientsPhone;
	}

	/**
	 * 设置 recipientsPhone
	 * @param recipientsPhone
	 */
	public void setRecipientsPhone(String recipientsPhone) {
		this.recipientsPhone = recipientsPhone;
	}

	/**
	 * 获取  recipientsAddr
	 * @return recipientsAddr
	 */
	public String getRecipientsAddr() {
		return recipientsAddr;
	}

	/**
	 * 设置 recipientsAddr
	 * @param recipientsAddr
	 */
	public void setRecipientsAddr(String recipientsAddr) {
		this.recipientsAddr = recipientsAddr;
	}

	/**
	 * 获取  freight
	 * @return freight
	 */
	public Integer getFreight() {
		return freight;
	}

	/**
	 * 设置 freight
	 * @param freight
	 */
	public void setFreight(Integer freight) {
		this.freight = freight;
	}

	/**
	 * 获取  warehouseId
	 * @return warehouseId
	 */
	public Integer getWarehouseId() {
		return warehouseId;
	}

	/**
	 * 设置 warehouseId
	 * @param warehouseId
	 */
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**
	 * 获取  residenceId
	 * @return residenceId
	 */
	public Integer getResidenceId() {
		return residenceId;
	}

	/**
	 * 设置 residenceId
	 * @param residenceId
	 */
	public void setResidenceId(Integer residenceId) {
		this.residenceId = residenceId;
	}

	/**
	 * 获取  residenceName
	 * @return residenceName
	 */
	public String getResidenceName() {
		return residenceName;
	}

	/**
	 * 设置 residenceName
	 * @param residenceName
	 */
	public void setResidenceName(String residenceName) {
		this.residenceName = residenceName;
	}

	/**
	 * 获取  limitFee
	 * @return limitFee
	 */
	public Integer getLimitFee() {
		return limitFee;
	}

	/**
	 * 设置 limitFee
	 * @param limitFee
	 */
	public void setLimitFee(Integer limitFee) {
		this.limitFee = limitFee;
	}

	/**
	 * 获取  limitFeeDesc
	 * @return limitFeeDesc
	 */
	public String getLimitFeeDesc() {
		return limitFeeDesc;
	}

	/**
	 * 设置 limitFeeDesc
	 * @param limitFeeDesc
	 */
	public void setLimitFeeDesc(String limitFeeDesc) {
		this.limitFeeDesc = limitFeeDesc;
	}

	/**
	 * 获取  replyStatus
	 * @return replyStatus
	 */
	public Integer getReplyStatus() {
		return replyStatus;
	}

	/**
	 * 设置 replyStatus
	 * @param replyStatus
	 */
	public void setReplyStatus(Integer replyStatus) {
		this.replyStatus = replyStatus;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
}
