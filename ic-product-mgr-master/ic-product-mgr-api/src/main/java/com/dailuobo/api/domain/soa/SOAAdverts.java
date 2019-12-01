package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * Created by Aministartor on 2016/12/5.
 */
public class SOAAdverts implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7654967874286038246L;
	private Integer adId;
    private Integer cityId;
    private String adName;
    private Integer adType;
    private Integer adProductId;
    private String adUrlName;
    private String adUrl;
    private String adPic;
    private String memo;
    private Integer status;
    private Integer delFlag;
    private Integer productStatus;

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
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

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public Integer getAdType() {
        return adType;
    }

    public void setAdType(Integer adType) {
        this.adType = adType;
    }

    public Integer getAdProductId() {
        return adProductId;
    }

    public void setAdProductId(Integer adProductId) {
        this.adProductId = adProductId;
    }

    public String getAdUrlName() {
        return adUrlName;
    }

    public void setAdUrlName(String adUrlName) {
        this.adUrlName = adUrlName;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getAdPic() {
        return adPic;
    }

    public void setAdPic(String adPic) {
        this.adPic = adPic;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
