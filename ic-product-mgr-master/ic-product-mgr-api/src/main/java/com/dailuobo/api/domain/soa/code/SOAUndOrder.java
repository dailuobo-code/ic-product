package com.dailuobo.api.domain.soa.code;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SOAUndOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 693503849031106490L;
	private Integer id;
    private String undOrderId;
    private Integer cityId;
    private Integer storeId;
    private Integer userId;
    private String  orderName;
    private Integer status;
    private Integer asynPayStatus;
    private String  generateTime;
    private String pickupTime;
    private String pickupStartTime;
    private String pickupEndTime;
    private Integer totalPrice;
    private Integer orderPrice;
    private Integer createUserId;
    private String createTime;
    private Integer updateUserId;
    private String updateTime;
    private String storeName;
    private String phone;
    private String memo;
    private Integer couponId;
    private Integer deductions;
    private Integer balance;
    private Date completeTime;
    private Integer change;
    private Byte version;
    private Byte deliveryMode;
    private String recipients;
    private String recipientsPhone;
    private String recipientsAddr;
    private String orderPic;
    private Integer freight;
    private Integer warehouseId;
    private Integer balanceType;
    private Integer weight;
    private String lockTime;
    private Integer isDel;
	private Long overTimeMills;
    private Integer undOrderDetailJsId;
    private Integer appUserId;
    private List<UndOrderDetailJs> undOrderDetailJsList;
	private String extras;

	public String getExtras() {
		return extras;
	}

	public void setExtras(String extras) {
		this.extras = extras;
	}
    
	public List<UndOrderDetailJs> getUndOrderDetailJsList() {
		return undOrderDetailJsList;
	}
	public void setUndOrderDetailJsList(List<UndOrderDetailJs> undOrderDetailJsList) {
		this.undOrderDetailJsList = undOrderDetailJsList;
	}
	public String getOrderPic() {
		return orderPic;
	}
	public void setOrderPic(String orderPic) {
		this.orderPic = orderPic;
	}
	public Long getOverTimeMills() {
		return overTimeMills;
	}
	public void setOverTimeMills(Long overTimeMills) {
		this.overTimeMills = overTimeMills;
	}
	public Integer getAppUserId() {
		return appUserId;
	}
	public void setAppUserId(Integer appUserId) {
		this.appUserId = appUserId;
	}
	public Integer getUndOrderDetailJsId() {
		return undOrderDetailJsId;
	}
	public void setUndOrderDetailJsId(Integer undOrderDetailJsId) {
		this.undOrderDetailJsId = undOrderDetailJsId;
	}
	public String getLockTime() {
		return lockTime;
	}
	public void setLockTime(String lockTime) {
		this.lockTime = lockTime;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUndOrderId() {
		return undOrderId;
	}
	public void setUndOrderId(String undOrderId) {
		this.undOrderId = undOrderId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Integer getStatus() {
		return status;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAsynPayStatus() {
		return asynPayStatus;
	}
	public void setAsynPayStatus(Integer asynPayStatus) {
		this.asynPayStatus = asynPayStatus;
	}
	public String getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(String generateTime) {
		this.generateTime = generateTime;
	}
	public String getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}
	public String getPickupStartTime() {
		return pickupStartTime;
	}
	public void setPickupStartTime(String pickupStartTime) {
		this.pickupStartTime = pickupStartTime;
	}
	public String getPickupEndTime() {
		return pickupEndTime;
	}
	public void setPickupEndTime(String pickupEndTime) {
		this.pickupEndTime = pickupEndTime;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(Integer orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getCouponId() {
		return couponId;
	}
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	public Integer getDeductions() {
		return deductions;
	}
	public void setDeductions(Integer deductions) {
		this.deductions = deductions;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Date getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}
	public Integer getChange() {
		return change;
	}
	public void setChange(Integer change) {
		this.change = change;
	}
	public Byte getVersion() {
		return version;
	}
	public void setVersion(Byte version) {
		this.version = version;
	}
	public Byte getDeliveryMode() {
		return deliveryMode;
	}
	public void setDeliveryMode(Byte deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	public String getRecipients() {
		return recipients;
	}
	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}
	public String getRecipientsPhone() {
		return recipientsPhone;
	}
	public void setRecipientsPhone(String recipientsPhone) {
		this.recipientsPhone = recipientsPhone;
	}
	public String getRecipientsAddr() {
		return recipientsAddr;
	}
	public void setRecipientsAddr(String recipientsAddr) {
		this.recipientsAddr = recipientsAddr;
	}
	public Integer getFreight() {
		return freight;
	}
	public void setFreight(Integer freight) {
		this.freight = freight;
	}
	public Integer getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Integer getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(Integer balanceType) {
		this.balanceType = balanceType;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
    
}
