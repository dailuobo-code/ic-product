package com.dailuobo.api.domain.entity;

import java.io.Serializable;

public class UnSpec implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer undSpecId;
	private Integer cityId;
	private Integer storeId;
	private Integer cityProductId;
	private Double maxWeight;
	private Double minWeight;
    private Double realPrice;
    private Double avgPrice;
    private String avgUnit;
    private Byte unitType;
    private Integer specName;
    private Integer status;
    private Integer showFlag;
    private Integer delFlag;
    private Integer createUserId;
    private Integer updateUserId;
    private String createTime;
    private String updateTime;
    private Integer standardFlag;
    private Integer threshold;
    private Integer packageMaxWeight;
    private Byte changeFlag;
    private Integer packageQuantity;
    private String storeName;


	public Integer getUndSpecId() {
		return undSpecId;
	}
	public void setUndSpecId(Integer undSpecId) {
		this.undSpecId = undSpecId;
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

	public Integer getCityProductId() {
		return cityProductId;
	}
	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}
	public Double getMaxWeight() {
		return maxWeight;
	}
	public void setMaxWeight(Double maxWeight) {
		this.maxWeight = maxWeight;
	}
	public Double getMinWeight() {
		return minWeight;
	}
	public void setMinWeight(Double minWeight) {
		this.minWeight = minWeight;
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
	public Integer getSpecName() {
		return specName;
	}
	public void setSpecName(Integer specName) {
		this.specName = specName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(Integer showFlag) {
		this.showFlag = showFlag;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	
}
