package com.dailuobo.api.domain.vo;

import java.io.Serializable;

/**
 * Created by Cowboy on 2016/3/30.
 */
public class DDLCityProduct implements Serializable {
    private static final long serialVersionUID = 1;
    private Integer cityProductId;
    private String showName;

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}
