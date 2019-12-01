package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Class SOAOrderRefund.
 */
public class SOAOrderRefund implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8356543783568416265L;
	
	/** The order id. */
	private String orderId;
	
	/** The city id. */
	private Integer cityId;
	
	/** The store id. */
	private Integer storeId;
	
	/** The phone. */
	private String phone;
	
	/** The status. */
	private Integer status;
	
	/** The amount. */
	private Float amount;
	
	/** The remark. */
	private String remark;
	
//	/** The cityProductIds. */
//	private Integer[] cityProductIds;
//	
//	private Integer[] ids;
	
	private List<IdsModel> list;
	/** The user id. */
//	private Integer userId;
	
	/** The update time. */
	private String updateTime;
	
	private Integer depositUserId;

	private Integer pointAmount;
	
	//private Integer userId;
	
	
//	public Integer getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}

	private BigDecimal refundAmt;
	public Integer getDepositUserId() {
		return depositUserId;
	}

	public void setDepositUserId(Integer depositUserId) {
		this.depositUserId = depositUserId;
	}

	public List<IdsModel> getList() {
		return list;
	}

	public void setList(List<IdsModel> list) {
		this.list = list;
	}

//	public Integer[] getCityProductIds() {
//		return cityProductIds;
//	}
//
//	public void setCityProductIds(Integer[] cityProductIds) {
//		this.cityProductIds = cityProductIds;
//	}
//
//	public Integer[] getIds() {
//		return ids;
//	}
//
//	public void setIds(Integer[] ids) {
//		this.ids = ids;
//	}

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
	 * 获取  phone.
	 *
	 * @return phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * 设置 phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * 获取  amount.
	 *
	 * @return amount
	 */
	public Float getAmount() {
		return amount;
	}
	
	/**
	 * 设置 amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	
	/**
	 * 获取  remark.
	 *
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}
	
	/**
	 * 设置 remark.
	 *
	 * @param remark the new remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	 

	/**
	 * 获取  userId.
	 *
	 * @return userId
	 */
//	public Integer getUserId() {
//		return userId;
//	}
	
	/**
	 * 设置 userId.
	 *
	 * @param userId the new user id
	 */
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}

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

	public BigDecimal getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(BigDecimal refundAmt) {
		this.refundAmt = refundAmt;
	}

	public Integer getPointAmount() {
		return pointAmount;
	}

	public void setPointAmount(Integer pointAmount) {
		this.pointAmount = pointAmount;
	}
}
