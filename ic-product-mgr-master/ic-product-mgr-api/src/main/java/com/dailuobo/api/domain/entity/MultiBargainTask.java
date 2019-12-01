package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Cowboy on 2016/10/21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MultiBargainTask implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Date executeTime;
    private Integer cityProductId;
    private String productNo;
    private String showName;
    private Integer cityId;
    private String storeIds;
    private String storeNames;
    private String iconTip;
    private String keyword;
    private Double avgPrice;
    private Double realPrice;
    private Integer thresholdForPurchase;
    private Date createTime;
    private Integer createUserId;
    private Integer status;
    private String exception;
    private Date updateTime;
    private String signVal;
    /**
     * 城市限购
     * 0:城市限购
     * 1:非城市限购
     */
    private Integer cityThreshold;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(String storeIds) {
        this.storeIds = storeIds;
    }

    public String getStoreNames() {
        return storeNames;
    }

    public void setStoreNames(String storeNames) {
        this.storeNames = storeNames;
    }

    public String getIconTip() {
        return iconTip;
    }

    public void setIconTip(String iconTip) {
        this.iconTip = iconTip;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public Integer getThresholdForPurchase() {
        return thresholdForPurchase;
    }

    public void setThresholdForPurchase(Integer thresholdForPurchase) {
        this.thresholdForPurchase = thresholdForPurchase;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSignVal() {
        return signVal;
    }

    public void setSignVal(String signVal) {
        this.signVal = signVal;
    }

    public Integer getCityThreshold() {
        return cityThreshold;
    }

    public void setCityThreshold(Integer cityThreshold) {
        this.cityThreshold = cityThreshold;
    }
}
