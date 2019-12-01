package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOATopPool implements Serializable {
    private static final long serialVersionUID = -6002148555113951510L;

    private Integer topId;
    private Integer cityId;
    private Integer type;
    private String topValue;
    private String topName;
    private String bgColor;
    private Integer isLogin;
    private Integer status;
    private Integer createUser;
    private Integer topOrder;

    public Integer getTopOrder() {
        return topOrder;
    }

    public void setTopOrder(Integer topOrder) {
        this.topOrder = topOrder;
    }

    public Integer getTopId() {
        return topId;
    }

    public void setTopId(Integer topId) {
        this.topId = topId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTopValue() {
        return topValue;
    }

    public void setTopValue(String topValue) {
        this.topValue = topValue;
    }

    public String getTopName() {
        return topName;
    }

    public void setTopName(String topName) {
        this.topName = topName;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public Integer getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(Integer isLogin) {
        this.isLogin = isLogin;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }
}
