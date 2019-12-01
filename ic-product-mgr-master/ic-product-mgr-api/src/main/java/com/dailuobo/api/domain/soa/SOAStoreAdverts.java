package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * Created by Aministartor on 2016/12/5.
 */
public class SOAStoreAdverts implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4451935063067737518L;
	private Integer id;
    private Integer adId;
    private Integer cityId;
    private Integer storeId;
    private Integer adOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
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

    public Integer getAdOrder() {
        return adOrder;
    }

    public void setAdOrder(Integer adOrder) {
        this.adOrder = adOrder;
    }
}
