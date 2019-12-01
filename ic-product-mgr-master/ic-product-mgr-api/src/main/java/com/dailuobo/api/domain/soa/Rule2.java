package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.Date;

/**
 * 购买单品（scpId）赠单品（rcpId）
 * <p>
 * Created by Cowboy on 2016/11/29.
 */
public class Rule2 implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2443067865827732177L;
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
     * 单品ID
     */
    private Integer scpId;
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

    public Integer getScpId() {
        return scpId;
    }

    public void setScpId(Integer scpId) {
        this.scpId = scpId;
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
        return "Rule2{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", storeId=" + storeId +
                ", scpId=" + scpId +
                ", rcpId=" + rcpId +
                ", factor=" + factor +
                ", pickupDate=" + pickupDate +
                '}';
    }
}
