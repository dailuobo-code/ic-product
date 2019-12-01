package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityProductSyncCity extends HqProduct implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer cityProductId;
    private Integer cityId;
    private Integer hqStatus;
    private Integer cityStatus;
    private String showName;
    private Double advisePrice;
    private Double disablePrice;
    private String weightUnit;
    private String numUnit;
    private String cityProductIcon;
    private String cityProductPic;
    private String origin;
    private String detailUrl;
    private String subtitle;
    private String keyword;
    private String iconTip;
    private String l1L2Names;
    private Integer changeFlag;
    private Integer homeFlag;  // 首页显示：0，否；1，是
    private Integer delFlag;
    private Integer purchaseFactor;
    private Integer keepType;
    private Integer qualityTime;

    private Double delta;
    private Integer upperLimit;
    private Integer lowerLimit;
    private int isShare;//是否共享
    private Integer newUserPro; //新人专享  0不是，1是
    private Integer isUnder;
    private String barCode;
    private Double vipCount;
    private Integer vipLimit;//'会员限购 0：限购，1：不限购'
    private Integer threshold;
    private  String length;
    private String high;

    private Integer userId;
    private String productNoNew;

    /**
     * 是否走采销预测
     */
    private Integer isForecast;

    /**
     * 上新类型
     * 0表示常规品  1表示新品
     */
    private Integer newArrivalType;

    /**
     * 售卖季节性
     */
    private Integer seasonal;

    /**
     * 主图视频
     */
    private String videoUrl = "";

    /**
     * 售卖季节性
     */
    private Integer arrivalDay;

    public Integer getHomeFlag() {
        return homeFlag;
    }

    public void setHomeFlag(Integer homeFlag) {
        this.homeFlag = homeFlag;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProductNoNew() {
        return productNoNew;
    }

    public void setProductNoNew(String productNoNew) {
        this.productNoNew = productNoNew;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getVipLimit() {
		return vipLimit;
	}

	public void setVipLimit(Integer vipLimit) {
		this.vipLimit = vipLimit;
	}

	public Double getVipCount() {
		return vipCount;
	}

	public void setVipCount(Double vipCount) {
		this.vipCount = vipCount;
	}

	public Integer getNewUserPro() {
		return newUserPro;
	}

	public void setNewUserPro(Integer newUserPro) {
		this.newUserPro = newUserPro;
	}

	public int getIsShare() {
		return isShare;
	}

	public void setIsShare(int isShare) {
		this.isShare = isShare;
	}

	public CityProductSyncCity() {
    }

    public CityProductSyncCity(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getHqStatus() {
        return hqStatus;
    }

    public void setHqStatus(Integer hqStatus) {
        this.hqStatus = hqStatus;
    }

    public Integer getCityStatus() {
        return cityStatus;
    }

    public void setCityStatus(Integer cityStatus) {
        this.cityStatus = cityStatus;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Double getAdvisePrice() {
        return advisePrice;
    }

    public void setAdvisePrice(Double advisePrice) {
        this.advisePrice = advisePrice;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getNumUnit() {
        return numUnit;
    }

    public void setNumUnit(String numUnit) {
        this.numUnit = numUnit;
    }

    public String getCityProductIcon() {
        return cityProductIcon;
    }

    public void setCityProductIcon(String cityProductIcon) {
        this.cityProductIcon = cityProductIcon;
    }

    public String getCityProductPic() {
        return cityProductPic;
    }

    public void setCityProductPic(String cityProductPic) {
        this.cityProductPic = cityProductPic;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getIconTip() {
        return iconTip;
    }

    public void setIconTip(String iconTip) {
        this.iconTip = iconTip;
    }

    public Double getDisablePrice() {
        return disablePrice;
    }

    public void setDisablePrice(Double disablePrice) {
        this.disablePrice = disablePrice;
    }

    public String getL1L2Names() {
        return l1L2Names;
    }

    public void setL1L2Names(String l1L2Names) {
        this.l1L2Names = l1L2Names;
    }

    public Integer getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(Integer changeFlag) {
        this.changeFlag = changeFlag;
    }

    public Double getDelta() {
        return delta;
    }

    public void setDelta(Double delta) {
        this.delta = delta;
    }

    public Integer getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Integer upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Integer getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Integer lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

	public Integer getIsUnder() {
		return isUnder;
	}

	public void setIsUnder(Integer isUnder) {
		this.isUnder = isUnder;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

    @Override
    public String getLength() {
        return length;
    }

    @Override
    public void setLength(String length) {
        this.length = length;
    }

    @Override
    public String getHigh() {
        return high;
    }

    @Override
    public void setHigh(String high) {
        this.high = high;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getPurchaseFactor() {
        return purchaseFactor;
    }

    public void setPurchaseFactor(Integer purchaseFactor) {
        this.purchaseFactor = purchaseFactor;
    }

    @Override
    public Integer getKeepType() {
        return keepType;
    }

    @Override
    public void setKeepType(Integer keepType) {
        this.keepType = keepType;
    }

    @Override
    public Integer getQualityTime() {
        return qualityTime;
    }

    @Override
    public void setQualityTime(Integer qualityTime) {
        this.qualityTime = qualityTime;
    }

    public Integer getIsForecast() {
        return isForecast;
    }

    public void setIsForecast(Integer isForecast) {
        this.isForecast = isForecast;
    }

    public Integer getNewArrivalType() {
        return newArrivalType;
    }

    public void setNewArrivalType(Integer newArrivalType) {
        this.newArrivalType = newArrivalType;
    }

    public Integer getSeasonal() {
        return seasonal;
    }

    public void setSeasonal(Integer seasonal) {
        this.seasonal = seasonal;
    }

    public Integer getArrivalDay() {
        return arrivalDay;
    }

    public void setArrivalDay(Integer arrivalDay) {
        this.arrivalDay = arrivalDay;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
