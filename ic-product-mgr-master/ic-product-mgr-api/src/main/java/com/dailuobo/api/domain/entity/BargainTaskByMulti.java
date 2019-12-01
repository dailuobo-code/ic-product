package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by Cowboy on 2016/10/21.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BargainTaskByMulti implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer cityProductId;
    private Integer cityId;
    private Integer storeId;
    private String iconTip;
    private String keyword;
    private Double avgPrice;
    private Double realPrice;
    private Integer thresholdForPurchase;
    private Integer createUserId;

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
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

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }
}
