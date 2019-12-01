package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.Date;

public class ChangeUserDto implements Serializable {
    private static final long serialVersionUID = 664199465222068650L;

    private Integer userId;
    /**
     * 收货人
     */
    private String name;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 是否存在变更生日记录;0否，1是
     */
    private Integer isUpdated;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(Integer isUpdated) {
        this.isUpdated = isUpdated;
    }
}
