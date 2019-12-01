package com.dailuobo.api.domain.vo;

import java.io.Serializable;

/**
 * Created by Cowboy on 2016/9/28.
 */
public class CityProductIdAndMode implements Serializable {
    private static final long serialVersionUID = 1;
    private Integer cityProductId;
    private Integer deliveryMode;

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public Integer getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Integer deliveryMode) {
        this.deliveryMode = deliveryMode;
    }
}
