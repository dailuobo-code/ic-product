package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.Date;

public class Group implements Serializable {
    private Integer id;

    private Integer cityId;

    private Integer storeId;

    private String groupTitle;

    private Integer userId;

    private Integer cityProductId;

    private Integer groupProductId;

    private Date groupStartTime;

    private Date groupEndTime;

    private Date pickupTime;

    private Date createTime;

    private Integer status;

    private String storeName;

    private Integer groupUserNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle == null ? null : groupTitle.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public Integer getGroupProductId() {
        return groupProductId;
    }

    public void setGroupProductId(Integer groupProductId) {
        this.groupProductId = groupProductId;
    }

    public Date getGroupStartTime() {
        return groupStartTime;
    }

    public void setGroupStartTime(Date groupStartTime) {
        this.groupStartTime = groupStartTime;
    }

    public Date getGroupEndTime() {
        return groupEndTime;
    }

    public void setGroupEndTime(Date groupEndTime) {
        this.groupEndTime = groupEndTime;
    }

    public Date getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getGroupUserNum() {
        return groupUserNum;
    }

    public void setGroupUserNum(Integer groupUserNum) {
        this.groupUserNum = groupUserNum;
    }
}