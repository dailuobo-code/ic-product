package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * Created by Aministartor on 2017/1/4.
 */
public class SOARecharge implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3149427691430445062L;
	private Integer userId;
    private String outTradeNo;
    private String orderId;
    private String prepayId;
    private String thirdpartyOrderId;
    private String thirdpartyUserId;
    private String notifyId;
    private Integer payPrice;
    private String payTime;
    private String syncCompleteTime;
    private String asyncCompleteTime;
    private String payType;
    private String payStatus;
    private String errMsg;
    private String synTradeStatus;
    private String asynTradeStatus;
    private String queryTradeStatus;
    private String createTime;
    private String updateTime;
    private Integer buyType;

    private Float preBalance;

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
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

    private Integer chargeType;

    private Integer cityId;
    private Integer storeId;

    public Float getPreBalance() {
        return preBalance;
    }

    public void setPreBalance(Float preBalance) {
        this.preBalance = preBalance;
    }

    public Float getPostBalance() {
        return postBalance;
    }

    public void setPostBalance(Float postBalance) {
        this.postBalance = postBalance;
    }

    private Float postBalance;

    public Integer getRechargeStatus() {
        return rechargeStatus;
    }

    public void setRechargeStatus(Integer rechargeStatus) {
        this.rechargeStatus = rechargeStatus;
    }

    private Integer rechargeStatus;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getThirdpartyOrderId() {
        return thirdpartyOrderId;
    }

    public void setThirdpartyOrderId(String thirdpartyOrderId) {
        this.thirdpartyOrderId = thirdpartyOrderId;
    }

    public String getThirdpartyUserId() {
        return thirdpartyUserId;
    }

    public void setThirdpartyUserId(String thirdpartyUserId) {
        this.thirdpartyUserId = thirdpartyUserId;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public Integer getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Integer payPrice) {
        this.payPrice = payPrice;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getSyncCompleteTime() {
        return syncCompleteTime;
    }

    public void setSyncCompleteTime(String syncCompleteTime) {
        this.syncCompleteTime = syncCompleteTime;
    }

    public String getAsyncCompleteTime() {
        return asyncCompleteTime;
    }

    public void setAsyncCompleteTime(String asyncCompleteTime) {
        this.asyncCompleteTime = asyncCompleteTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getSynTradeStatus() {
        return synTradeStatus;
    }

    public void setSynTradeStatus(String synTradeStatus) {
        this.synTradeStatus = synTradeStatus;
    }

    public String getAsynTradeStatus() {
        return asynTradeStatus;
    }

    public void setAsynTradeStatus(String asynTradeStatus) {
        this.asynTradeStatus = asynTradeStatus;
    }

    public String getQueryTradeStatus() {
        return queryTradeStatus;
    }

    public void setQueryTradeStatus(String queryTradeStatus) {
        this.queryTradeStatus = queryTradeStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
