package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单满额（amount）赠单品（rcpId）
 * <p>
 * Created by Cowboy on 2016/11/29.
 */
public class Rule4 implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5451706605175446396L;
	/**
     * 规则ID
     */
    private Integer id;
    /**
     * 规则适用城市ID
     */
    private Integer cityId;
    /**
     * 规则适用门店ID
     */
    private Integer storeId;
    /**
     * 订单满额
     */
    private Integer amount;
    /**
     * 赠品ID
     */
    private Integer rcpId;
    /**
     * 系数
     */
    private Integer factor;
    /**
     * 取货日期
     */
    private Date pickupDate;

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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getRcpId() {
        return rcpId;
    }

    public void setRcpId(Integer rcpId) {
        this.rcpId = rcpId;
    }

    public Integer getFactor() {
        return factor;
    }

    public void setFactor(Integer factor) {
        this.factor = factor;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    @Override
    public String toString() {
        return "Rule4{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", storeId=" + storeId +
                ", amount=" + amount +
                ", rcpId=" + rcpId +
                ", factor=" + factor +
                ", pickupDate=" + pickupDate +
                '}';
    }
}
