package com.dailuobo.api.domain.soa.limit;

import java.io.Serializable;

public class OnOrderUserVo implements Serializable {
    private static final long serialVersionUID = -4204100760666154808L;
    private Integer cityId;
    private Integer storeId;
    private Integer pickupTimeId;
    private Integer appUserId;
    private Byte deliveryMode;

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

    public Integer getPickupTimeId() {
        return pickupTimeId;
    }

    public void setPickupTimeId(Integer pickupTimeId) {
        this.pickupTimeId = pickupTimeId;
    }

    public Integer getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Integer appUserId) {
        this.appUserId = appUserId;
    }

    public Byte getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Byte deliveryMode) {
        this.deliveryMode = deliveryMode;
    }
}
