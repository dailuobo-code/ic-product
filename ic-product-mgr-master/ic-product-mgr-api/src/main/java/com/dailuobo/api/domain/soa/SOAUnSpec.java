package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.List;

public class SOAUnSpec implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1519009241236478247L;
	private Integer undSpecId;
	private Integer cityId;
	private Integer storeId;
	private Integer cityProductId;
	private Double maxWeight;
	private Double minWeight;
    private Double realPrice;
    private Float avgPrice;
    private String avgUnit;
    private Integer unitType;
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
    private Integer changeFlag;
    private Integer packageQuantity;
    private String storeName;
   
    private Double delta;
    private Integer upperLimit;
    private Integer lowerLimit;
    
    private String cityProductIcon;
    private String hqProductName;
    private String showName;
    private String productNo;
    private String weightUnit;
    private String numUnit;
    private Double advisePrice;
    private Double disablePrice;
    private Integer actualWeight;
	
	private Float actualPrice;

    private List<Integer> storeIds;
	
	public Integer getActualWeight() {
		return actualWeight;
	}

	public void setActualWeight(Integer actualWeight) {
		this.actualWeight = actualWeight;
	}

	public Float getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Float actualPrice) {
		this.actualPrice = actualPrice;
	}
    
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
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
	public Double getAdvisePrice() {
		return advisePrice;
	}
	public void setAdvisePrice(Double advisePrice) {
		this.advisePrice = advisePrice;
	}
	public Double getDisablePrice() {
		return disablePrice;
	}
	public void setDisablePrice(Double disablePrice) {
		this.disablePrice = disablePrice;
	}
	public String getCityProductIcon() {
		return cityProductIcon;
	}
	public void setCityProductIcon(String cityProductIcon) {
		this.cityProductIcon = cityProductIcon;
	}
	public String getHqProductName() {
		return hqProductName;
	}
	public void setHqProductName(String hqProductName) {
		this.hqProductName = hqProductName;
	}
	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
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
	public Float getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(Float avgPrice) {
		this.avgPrice = avgPrice;
	}
	public String getAvgUnit() {
		return avgUnit;
	}
	public void setAvgUnit(String avgUnit) {
		this.avgUnit = avgUnit;
	}
	public Integer getUnitType() {
		return unitType;
	}
	public void setUnitType(Integer unitType) {
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
	public Integer getChangeFlag() {
		return changeFlag;
	}
	public void setChangeFlag(Integer changeFlag) {
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

    public List<Integer> getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(List<Integer> storeIds) {
        this.storeIds = storeIds;
    }

    public SOAUnSpec() {
	}

	public SOAUnSpec(Integer cityId, Integer cityProductId, Double realPrice, Float avgPrice, Integer createUserId, Integer threshold) {
		this.cityId = cityId;
		this.cityProductId = cityProductId;
		this.realPrice = realPrice;
		this.avgPrice = avgPrice;
		this.createUserId = createUserId;
		this.threshold = threshold;
	}
}
