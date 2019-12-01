/**
 * SOASalesSpecVO.java
 * @author huangwei
 * @since 2016-1-27
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * SOASalesSpecVO.java
 * @author huangwei
 * @since 2016-1-27
 *  描述：
 */
public class SOASalesSpecVO implements Serializable, Comparable<SOASalesSpecVO>{


	/**
	 * SalesSpec.java
	 */
	private static final long serialVersionUID = 1857770757301186014L;

	/** 规格id */
	private Integer specId;

	/** 城市商品id */
	private Integer cityProductId;
	
	/** 商品名称 */
	private String showName;
	
	/** 数量上限 */
	private Integer maxNum;

	/** 数量下限 */
	private Integer minNum;

	/** 重量上限 */
	private Float maxWeight;

	/** 重量下限 */
	private Float minWeight;

	/** 销售价格 */
	private Float realPrice;

	/** 折合价 */
	private Float avgPrice;

	/** 折合单位 */
	private String avgUnit;

	/** [大|中|小]规格 */
	private String specName;

	/**是否默认 [true|false] */
	private Integer isdefault;

	/** [0:不显示 1：显示] */
	private Integer showFlag;

	/** 重量描述 */
	private String weightDetail;
	
	/** 数量描述 */
	private String numberDetail;
	
	/**售价描述 */
	private String realPriceDetail;
	
	/** 折合价描述 */
	private String avgPriceDetail;

	/**是否标准份 是否标准份(0:否,1:是)*/
	private Integer standardFlag;
	
	/**
	 * 是否有库存
	 */
	private Boolean isHave;
	
	/** 限购份数*/
	private Integer threshold;
	
	/**找零标志：1，找零；2，不找零*/
	private Integer changeFlag;
	
	/** 重量单位 */
	private String weightUnit;

	/** 数量单位 */
	private String numUnit;
	
	//单位类型：1，重量单位；2，数量单位
	private Integer unitType;
	
	// 打包上限
	private Integer packageMaxWeight;
	
	private Double vipCount;
	
	

	public Double getVipCount() {
		return vipCount;
	}

	public void setVipCount(Double vipCount) {
		this.vipCount = vipCount;
	}

	/**
	 * @return the weightDetail
	 */
	public String getWeightDetail() {
		return weightDetail;
	}

	/**
	 * @param weightDetail the weightDetail to set
	 */
	public void setWeightDetail(String weightDetail) {
		this.weightDetail = weightDetail;
	}

	/**
	 * @return the numberDetail
	 */
	public String getNumberDetail() {
		return numberDetail;
	}

	/**
	 * @param numberDetail the numberDetail to set
	 */
	public void setNumberDetail(String numberDetail) {
		this.numberDetail = numberDetail;
	}

	/**
	 * @return the specId
	 */
	public Integer getSpecId() {
		return specId;
	}

	/**
	 * @param specId the specId to set
	 */
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}

	/**
	 * @return the cityProductId
	 */
	public Integer getCityProductId() {
		return cityProductId;
	}

	/**
	 * @param cityProductId the cityProductId to set
	 */
	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}

	/**
	 * @return the minNum
	 */
	public Integer getMinNum() {
		return minNum;
	}

	/**
	 * @param minNum the minNum to set
	 */
	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}

	/**
	 * @return the maxWeight
	 */
	public Float getMaxWeight() {
		return maxWeight;
	}

	/**
	 * @param maxWeight the maxWeight to set
	 */
	public void setMaxWeight(Float maxWeight) {
		this.maxWeight = maxWeight;
	}

	/**
	 * @return the minWeight
	 */
	public Float getMinWeight() {
		return minWeight;
	}

	/**
	 * @param minWeight the minWeight to set
	 */
	public void setMinWeight(Float minWeight) {
		this.minWeight = minWeight;
	}

	/**
	 * @return the realPrice
	 */
	public Float getRealPrice() {
		return realPrice;
	}

	/**
	 * @param realPrice the realPrice to set
	 */
	public void setRealPrice(Float realPrice) {
		this.realPrice = realPrice;
	}

	/**
	 * @return the avgPrice
	 */
	public Float getAvgPrice() {
		return avgPrice;
	}

	/**
	 * @param avgPrice the avgPrice to set
	 */
	public void setAvgPrice(Float avgPrice) {
		this.avgPrice = avgPrice;
	}

	/**
	 * @return the avgUnit
	 */
	public String getAvgUnit() {
		return avgUnit;
	}

	/**
	 * @param avgUnit the avgUnit to set
	 */
	public void setAvgUnit(String avgUnit) {
		this.avgUnit = avgUnit;
	}

	/**
	 * @return the specName
	 */
	public String getSpecName() {
		return specName;
	}

	/**
	 * @param specName the specName to set
	 */
	public void setSpecName(String specName) {
		this.specName = specName;
	}

	/**
	 * @return the isdefault
	 */
	public Integer getIsdefault() {
		return isdefault;
	}

	/**
	 * @param isdefault the isdefault to set
	 */
	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}

	/**
	 * @return the showFlag
	 */
	public Integer getShowFlag() {
		return showFlag;
	}

	/**
	 * @param showFlag the showFlag to set
	 */
	public void setShowFlag(Integer showFlag) {
		this.showFlag = showFlag;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(SOASalesSpecVO o) {
		return this.getIsdefault().compareTo(o.getIsdefault());
	}

	/**
	 * @return the maxNum
	 */
	public Integer getMaxNum() {
		return maxNum;
	}

	/**
	 * @param maxNum the maxNum to set
	 */
	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}

	/**
	 * @return the showName
	 */
	public String getShowName() {
		return showName;
	}

	/**
	 * @param showName the showName to set
	 */
	public void setShowName(String showName) {
		this.showName = showName;
	}

	/**
	 * @return the standardFlag
	 */
	public Integer getStandardFlag() {
		return standardFlag;
	}

	/**
	 * @param standardFlag the standardFlag to set
	 */
	public void setStandardFlag(Integer standardFlag) {
		this.standardFlag = standardFlag;
	}

	/**
	 * @return the isHave
	 */
	public Boolean getIsHave() {
		return isHave;
	}

	/**
	 * @param isHave the isHave to set
	 */
	public void setIsHave(Boolean isHave) {
		this.isHave = isHave;
	}

	/**
	 * @return the realPriceDetail
	 */
	public String getRealPriceDetail() {
		return realPriceDetail;
	}

	/**
	 * @param realPriceDetail the realPriceDetail to set
	 */
	public void setRealPriceDetail(String realPriceDetail) {
		this.realPriceDetail = realPriceDetail;
	}

	/**
	 * @return the avgPriceDetail
	 */
	public String getAvgPriceDetail() {
		return avgPriceDetail;
	}

	/**
	 * @param avgPriceDetail the avgPriceDetail to set
	 */
	public void setAvgPriceDetail(String avgPriceDetail) {
		this.avgPriceDetail = avgPriceDetail;
	}

	/**
	 * @return the threshold
	 */
	public Integer getThreshold() {
		return threshold;
	}

	/**
	 * @param threshold the threshold to set
	 */
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	/**
	 * 获取  changeFlag
	 * @return changeFlag
	 */
	public Integer getChangeFlag() {
		return changeFlag;
	}

	/**
	 * 设置 changeFlag
	 * @param changeFlag
	 */
	public void setChangeFlag(Integer changeFlag) {
		this.changeFlag = changeFlag;
	}

	/**
	 * 获取  weightUnit
	 * @return weightUnit
	 */
	public String getWeightUnit() {
		return weightUnit;
	}

	/**
	 * 设置 weightUnit
	 * @param weightUnit
	 */
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	/**
	 * 获取  numUnit
	 * @return numUnit
	 */
	public String getNumUnit() {
		return numUnit;
	}

	/**
	 * 设置 numUnit
	 * @param numUnit
	 */
	public void setNumUnit(String numUnit) {
		this.numUnit = numUnit;
	}

	/**
	 * 获取  unitType
	 * @return unitType
	 */
	public Integer getUnitType() {
		return unitType;
	}

	/**
	 * 设置 unitType
	 * @param unitType
	 */
	public void setUnitType(Integer unitType) {
		this.unitType = unitType;
	}

	/**
	 * 获取  packageMaxWeight
	 * @return packageMaxWeight
	 */
	public Integer getPackageMaxWeight() {
		return packageMaxWeight;
	}

	/**
	 * 设置 packageMaxWeight
	 * @param packageMaxWeight
	 */
	public void setPackageMaxWeight(Integer packageMaxWeight) {
		this.packageMaxWeight = packageMaxWeight;
	}
}
