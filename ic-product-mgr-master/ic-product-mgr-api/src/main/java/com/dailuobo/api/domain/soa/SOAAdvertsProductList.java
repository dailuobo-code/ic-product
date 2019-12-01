package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * Created by Aministartor on 2016/12/5.
 */
public class SOAAdvertsProductList implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3523304193301667472L;
	private Integer id;
    private Integer adId;
    private Integer cityProductId;
    private Double adProductOrder;

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

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public Double getAdProductOrder() {
        return adProductOrder;
    }

    public void setAdProductOrder(Double adProductOrder) {
        this.adProductOrder = adProductOrder;
    }
}
