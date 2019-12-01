package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * Created by Aministartor on 2016/12/13.
 */
public class SOAFullOfGifts implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1524856164922045010L;
	private Double poorPrice;
    private Double activityPrice;
    private Double highestPrice;
    private Integer flag;

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Double getPoorPrice() {
        return poorPrice;
    }

    public void setPoorPrice(Double poorPrice) {
        this.poorPrice = poorPrice;
    }

    public Double getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(Double activityPrice) {
        this.activityPrice = activityPrice;
    }

    public Double getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(Double highestPrice) {
        this.highestPrice = highestPrice;
    }
}
