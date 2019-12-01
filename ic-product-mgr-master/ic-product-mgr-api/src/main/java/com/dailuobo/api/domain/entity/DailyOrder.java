package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Cowboy on 6/22/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DailyOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer cityId;
    private Date purchaseDate;
    private Integer newUser;
    private Integer newUserOrder;
    private Double newUserAmount;
    private Integer oldUser;
    private Integer oldUserOrder;
    private Double oldUserAmount;
    private Integer totalUser;
    private Integer totalOrder;
    private Double totalAmount;
    private String storeName;
    private Double vipPrice;
    private Double couponPrice;
    private Double undActualPrice;
    private Double undPrice;
    private Integer undOrder;
    private Integer undUser;

    public Integer getUndUser() {
        return undUser;
    }

    public void setUndUser(Integer undUser) {
        this.undUser = undUser;
    }

    public Double getUndActualPrice() {
        return undActualPrice;
    }

    public void setUndActualPrice(Double undActualPrice) {
        this.undActualPrice = undActualPrice;
    }

    public Double getUndPrice() {
        return undPrice;
    }

    public void setUndPrice(Double undPrice) {
        this.undPrice = undPrice;
    }

    public Integer getUndOrder() {
        return undOrder;
    }

    public void setUndOrder(Integer undOrder) {
        this.undOrder = undOrder;
    }

    public Double getVipPrice() {
		return vipPrice;
	}

	public void setVipPrice(Double vipPrice) {
		this.vipPrice = vipPrice;
	}

	public Double getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(Double couponPrice) {
		this.couponPrice = couponPrice;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

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

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Integer getNewUser() {
        return newUser;
    }

    public void setNewUser(Integer newUser) {
        this.newUser = newUser;
    }

    public Integer getNewUserOrder() {
        return newUserOrder;
    }

    public void setNewUserOrder(Integer newUserOrder) {
        this.newUserOrder = newUserOrder;
    }

    public Double getNewUserAmount() {
        return newUserAmount;
    }

    public void setNewUserAmount(Double newUserAmount) {
        this.newUserAmount = newUserAmount;
    }

    public Integer getOldUser() {
        return oldUser;
    }

    public void setOldUser(Integer oldUser) {
        this.oldUser = oldUser;
    }

    public Integer getOldUserOrder() {
        return oldUserOrder;
    }

    public void setOldUserOrder(Integer oldUserOrder) {
        this.oldUserOrder = oldUserOrder;
    }

    public Double getOldUserAmount() {
        return oldUserAmount;
    }

    public void setOldUserAmount(Double oldUserAmount) {
        this.oldUserAmount = oldUserAmount;
    }

    public Integer getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(Integer totalUser) {
        this.totalUser = totalUser;
    }

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Integer totalOrder) {
        this.totalOrder = totalOrder;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
