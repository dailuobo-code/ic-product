package com.dailuobo.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Spec extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer specId;
    private Integer cityProductId;
    private Double realPrice;
    private Double avgPrice;
    private String avgUnit;
    private Byte unitType;
    private Integer standardFlag;
    private Integer threshold;
    private Integer packageMaxWeight;
    private Byte changeFlag;
    private Integer packageQuantity;
    private Integer storeId;
    private String storeName;
    private Integer cityId;
    private Double delta;
    private Integer upperLimit;
    private Integer lowerLimit;
    private Double vipCount;
    private Double vipProductPrice;
    private Integer warehouseId;
    private String warehouseName;
    private List<Integer> excludeStoreIds;
    private Double avgPriceFloor;
    private Double avgPriceCeiling;
    /**
     * 售罄展示  true 展示  false 不展示
     */
    private Boolean sellOutShow;

    public List<Integer> getExcludeStoreIds() {
        return excludeStoreIds;
    }

    public void setExcludeStoreIds(List<Integer> excludeStoreIds) {
        this.excludeStoreIds = excludeStoreIds;
    }

    public Double getVipProductPrice() {
		return vipProductPrice;
	}

	public void setVipProductPrice(Double vipProductPrice) {
		this.vipProductPrice = vipProductPrice;
	}

	public Double getVipCount() {
		return vipCount;
	}

	public void setVipCount(Double vipCount) {
		this.vipCount = vipCount;
	}

	public Spec() {
    }

    public Spec(Integer specId) {
        this.specId = specId;
    }

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public Integer getCityProductId() {
        return cityProductId;
    }

    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    public Double getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(Double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getAvgUnit() {
        return avgUnit;
    }

    public void setAvgUnit(String avgUnit) {
        this.avgUnit = avgUnit;
    }

    public Byte getUnitType() {
        return unitType;
    }

    public void setUnitType(Byte unitType) {
        this.unitType = unitType;
    }

    public Integer getStandardFlag() {
        return standardFlag;
    }

    public void setStandardFlag(Integer standardFlag) {
        this.standardFlag = standardFlag;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getPackageMaxWeight() {
        return packageMaxWeight;
    }

    public void setPackageMaxWeight(Integer packageMaxWeight) {
        this.packageMaxWeight = packageMaxWeight;
    }

    public Byte getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(Byte changeFlag) {
        this.changeFlag = changeFlag;
    }

    public Integer getPackageQuantity() {
        return packageQuantity;
    }

    public void setPackageQuantity(Integer packageQuantity) {
        this.packageQuantity = packageQuantity;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
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

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public Boolean getSellOutShow() {
        return sellOutShow;
    }

    public void setSellOutShow(Boolean sellOutShow) {
        this.sellOutShow = sellOutShow;
    }

    public Double getAvgPriceFloor() {
        return avgPriceFloor;
    }

    public void setAvgPriceFloor(Double avgPriceFloor) {
        this.avgPriceFloor = avgPriceFloor;
    }

    public Double getAvgPriceCeiling() {
        return avgPriceCeiling;
    }

    public void setAvgPriceCeiling(Double avgPriceCeiling) {
        this.avgPriceCeiling = avgPriceCeiling;
    }
}
