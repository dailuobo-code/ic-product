package com.dailuobo.api.domain.entity;

import java.io.Serializable;

/**
 * 开通城市
 * Created by Administrator on 2018/12/28.
 */
public class OpenCity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer cityId;
    private String cityName;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "OpenCity{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
