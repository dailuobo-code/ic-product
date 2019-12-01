package com.dailuobo.api.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by Cowboy on 2016/3/30.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TACityProduct implements Serializable {
    private static final long serialVersionUID = 1;
    private Integer cityProductId;
    private String showName;
    private String productNo;
    private String iconTip;
    private String keyword;
    private Double avgPrice;
    private Double realPrice;
    private Byte changeFlag;
    private Integer packageMaxWeight;
    private Integer packageQuantity;
    private String weightUnit;
    private String numUnit;
    private String avgUnit;
    private Byte deliveryMode;
    private String unitType;
    private String projectCode;
    private String keepType;
    private String qualityTime;
    private Double disablePrice;
    private String saleName;

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getQualityTime() {
		return qualityTime;
	}

	public void setQualityTime(String qualityTime) {
		this.qualityTime = qualityTime;
	}

	public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getIconTip() {
        return iconTip;
    }

    public void setIconTip(String iconTip) {
        this.iconTip = iconTip;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public Byte getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(Byte changeFlag) {
        this.changeFlag = changeFlag;
    }

    public Integer getPackageMaxWeight() {
        return packageMaxWeight;
    }

    public void setPackageMaxWeight(Integer packageMaxWeight) {
        this.packageMaxWeight = packageMaxWeight;
    }

    public Integer getPackageQuantity() {
        return packageQuantity;
    }

    public void setPackageQuantity(Integer packageQuantity) {
        this.packageQuantity = packageQuantity;
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

    public String getAvgUnit() {
        return avgUnit;
    }

    public void setAvgUnit(String avgUnit) {
        this.avgUnit = avgUnit;
    }

    public Byte getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Byte deliveryMode) {
        this.deliveryMode = deliveryMode;
    }
    
	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getKeepType() {
		return keepType;
	}

	public void setKeepType(String keepType) {
		this.keepType = keepType;
	}

    public Double getDisablePrice() {
        return disablePrice;
    }

    public void setDisablePrice(Double disablePrice) {
        this.disablePrice = disablePrice;
    }
}
