package com.dailuobo.api.domain.soa.code;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SalesSpecDto.
 */
public class SOASalesSpecDto implements Serializable {
	
	/** The Constant serialVersionUID. */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3634852490732645648L;

	/** The spec id. */
	private Integer undSpecId; 
	
	/** The city product id. */
	private Integer cityProductId;
	
	/** The real price. */
	// 实际销售价格
	private Float realPrice; 
	
	/** The avg price. */
	// 折合价
	private Float avgPrice; 
	
	/** The avg unit. */
	private String avgUnit; 
	
	/** The unit type. */
	private Integer unitType; 
	
	/** The del flag. */
	private Integer delFlag; 
	
	/** The create time. */
	private String createTime; 
	
	/** The update time. */
	private String updateTime; 
	
	/** The threshold. */
	private Integer threshold; 
	
	/** The package max weight. */
	private Integer packageMaxWeight; 
	
	/** The change flag. */
	private Integer changeFlag; 
	
	/** The package quantity. */
	private Integer packageQuantity; 
	
	/** The store id. */
	private Integer storeId;
	
	/** 折合价描述 */
	private String avgPriceDetail;
	
	/** 销售价格描述 */
	private String realPriceDetail;
	
	/** 重量描述 */
	private String weightDetail;
	
	/** 数量描述 */
	private String numberDetail;
	
	private Integer standardFlag;
	
	private Integer cityId;
	
	private String iconTip;
	
	private String keyword;
	
	private String showName;
	
	private Integer actualWeight;
	
	private Float actualPrice;
	
	private String cityProductIcon;
	
	private String productNo;
	
	private String l1L2Names;

	public String getL1L2Names() {
		return l1L2Names;
	}

	public void setL1L2Names(String l1L2Names) {
		this.l1L2Names = l1L2Names;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getCityProductIcon() {
		return cityProductIcon;
	}

	public void setCityProductIcon(String cityProductIcon) {
		this.cityProductIcon = cityProductIcon;
	}

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

	public Integer getUndSpecId() {
		return undSpecId;
	}

	public void setUndSpecId(Integer undSpecId) {
		this.undSpecId = undSpecId;
	}

	/**
	 * 获取  cityProductId.
	 *
	 * @return cityProductId
	 */
	public Integer getCityProductId() {
		return cityProductId;
	}
	
	/**
	 * 设置 cityProductId.
	 *
	 * @param cityProductId the new city product id
	 */
	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}
	
	/**
	 * 获取  unitType.
	 *
	 * @return unitType
	 */
	public Integer getUnitType() {
		return unitType;
	}
	
	/**
	 * 设置 unitType.
	 *
	 * @param unitType the new unit type
	 */
	public void setUnitType(Integer unitType) {
		this.unitType = unitType;
	}
	
	/**
	 * 获取  delFlag.
	 *
	 * @return delFlag
	 */
	public Integer getDelFlag() {
		return delFlag;
	}
	
	/**
	 * 设置 delFlag.
	 *
	 * @param delFlag the new del flag
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	/**
	 * 获取  threshold.
	 *
	 * @return threshold
	 */
	public Integer getThreshold() {
		return threshold;
	}
	
	/**
	 * 设置 threshold.
	 *
	 * @param threshold the new threshold
	 */
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}
	
	/**
	 * 获取  packageMaxWeight.
	 *
	 * @return packageMaxWeight
	 */
	public Integer getPackageMaxWeight() {
		return packageMaxWeight;
	}
	
	/**
	 * 设置 packageMaxWeight.
	 *
	 * @param packageMaxWeight the new package max weight
	 */
	public void setPackageMaxWeight(Integer packageMaxWeight) {
		this.packageMaxWeight = packageMaxWeight;
	}
	
	/**
	 * 获取  changeFlag.
	 *
	 * @return changeFlag
	 */
	public Integer getChangeFlag() {
		return changeFlag;
	}
	
	/**
	 * 设置 changeFlag.
	 *
	 * @param changeFlag the new change flag
	 */
	public void setChangeFlag(Integer changeFlag) {
		this.changeFlag = changeFlag;
	}
	
	/**
	 * 获取  packageQuantity.
	 *
	 * @return packageQuantity
	 */
	public Integer getPackageQuantity() {
		return packageQuantity;
	}
	
	/**
	 * 设置 packageQuantity.
	 *
	 * @param packageQuantity the new package quantity
	 */
	public void setPackageQuantity(Integer packageQuantity) {
		this.packageQuantity = packageQuantity;
	}
	
	/**
	 * 获取  storeId.
	 *
	 * @return storeId
	 */
	public Integer getStoreId() {
		return storeId;
	}
	
	/**
	 * 设置 storeId.
	 *
	 * @param storeId the new store id
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	/**
	 * 获取  realPrice
	 * @return realPrice
	 */
	public Float getRealPrice() {
		return realPrice;
	}

	/**
	 * 设置 realPrice
	 * @param realPrice
	 */
	public void setRealPrice(Float realPrice) {
		this.realPrice = realPrice;
	}

	/**
	 * 获取  avgPrice
	 * @return avgPrice
	 */
	public Float getAvgPrice() {
		return avgPrice;
	}

	/**
	 * 设置 avgPrice
	 * @param avgPrice
	 */
	public void setAvgPrice(Float avgPrice) {
		this.avgPrice = avgPrice;
	}

	/**
	 * 获取  avgUnit
	 * @return avgUnit
	 */
	public String getAvgUnit() {
		return avgUnit;
	}

	/**
	 * 设置 avgUnit
	 * @param avgUnit
	 */
	public void setAvgUnit(String avgUnit) {
		this.avgUnit = avgUnit;
	}

	/**
	 * 获取  createTime
	 * @return createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取  updateTime
	 * @return updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置 updateTime
	 * @param updateTime
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取  avgPriceDetail
	 * @return avgPriceDetail
	 */
	public String getAvgPriceDetail() {
		return avgPriceDetail;
	}

	/**
	 * 设置 avgPriceDetail
	 * @param avgPriceDetail
	 */
	public void setAvgPriceDetail(String avgPriceDetail) {
		this.avgPriceDetail = avgPriceDetail;
	}

	/**
	 * 获取  realPriceDetail
	 * @return realPriceDetail
	 */
	public String getRealPriceDetail() {
		return realPriceDetail;
	}

	/**
	 * 设置 realPriceDetail
	 * @param realPriceDetail
	 */
	public void setRealPriceDetail(String realPriceDetail) {
		this.realPriceDetail = realPriceDetail;
	}

	/**
	 * 获取  weightDetail
	 * @return weightDetail
	 */
	public String getWeightDetail() {
		return weightDetail;
	}

	/**
	 * 设置 weightDetail
	 * @param weightDetail
	 */
	public void setWeightDetail(String weightDetail) {
		this.weightDetail = weightDetail;
	}

	/**
	 * 获取  numberDetail
	 * @return numberDetail
	 */
	public String getNumberDetail() {
		return numberDetail;
	}

	/**
	 * 设置 numberDetail
	 * @param numberDetail
	 */
	public void setNumberDetail(String numberDetail) {
		this.numberDetail = numberDetail;
	}

	/**
	 * 获取  standardFlag
	 * @return standardFlag
	 */
	public Integer getStandardFlag() {
		return standardFlag;
	}

	/**
	 * 设置 standardFlag
	 * @param standardFlag
	 */
	public void setStandardFlag(Integer standardFlag) {
		this.standardFlag = standardFlag;
	}

	/**
	 * 获取  cityId
	 * @return cityId
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * 设置 cityId
	 * @param cityId
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * 获取  iconTip
	 * @return iconTip
	 */
	public String getIconTip() {
		return iconTip;
	}

	/**
	 * 设置 iconTip
	 * @param iconTip
	 */
	public void setIconTip(String iconTip) {
		this.iconTip = iconTip;
	}

	/**
	 * 获取  keyword
	 * @return keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * 设置 keyword
	 * @param keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	} 
	
}
