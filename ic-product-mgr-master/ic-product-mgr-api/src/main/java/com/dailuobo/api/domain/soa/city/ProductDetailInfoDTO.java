/**
 * ProductDetailInfo.java
 * @author huangwei
 * @since 2016-4-8
 *  描述：
 */
package com.dailuobo.api.domain.soa.city;

import java.io.Serializable;

/**
 * ProductDetailInfo.java
 * @author huangwei
 * @since 2016-4-8
 *  描述：从mysql多表联合查询 出的结果;
 *  
 */
public class ProductDetailInfoDTO implements Serializable{


	/**
	 * 
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 5847290040758921705L;

	/** 城市id */
	private Integer cityId;

	/** 城市商品id */
	private Integer cityProductId;

	/** 总部商品id，hq_status */
	private Integer hqProductId;

	/** [0:下架,1:上架]总部状态 */
	private Integer hqStatus;

	/** [0:下架,1:上架 2:待上架]城市状态*/
	private Integer cityStatus;

	/** 投放名称 */
	private String showName;

	/** 失效价格 */
	private String disablePrice;

	/** 重量单位 */
	private String weightUnit;

	/** 数量单位 */
	private String numUnit;

	/** 指导价 */
	private String advisePrice;

	/** 描述 */
	private String desc;

	/** [城市商品图片1,城市商品图片2,城市商品图片3] */
	private String cityProductPic;

	/** 在列表中的显示图片 */
	private String iconPic;

	/** [详情里的长图] */
	private String detailUrl;
	
	/** 规格id */
	private Integer specId;
	
	
	/** 销售价格 */
	private Float realPrice;

	/** 折合价 */
	private Float avgPrice;

	/** 折合单位 */
	private String avgUnit;

	/** [大|中|小]规格 */
	private String specName;
	
	/** 数量上限 */
	private Integer maxNum;

	/** 数量下限 */
	private Integer minNum;

	/** 重量上限 */
	private Float maxWeight;

	/** 重量下限 */
	private Float minWeight;
	
	/** 折合价描述 */
	private String avgPriceDetail;
	
	/** 销售价格描述 */
	private String realPriceDetail;
	
	/** 重量描述 */
	private String weightDetail;
	
	/** 数量描述 */
	private String numberDetail;

	/**是否标准份 是否标准份(0:否,1:是)*/
	private Integer standardFlag;
	
	/**距离定价类型*/
	private Integer type;
	
	/** 每人限购份数 */
	private Integer threshold;
	
	/**城市商品更新时间*/
	private String updateTime;
	
	private String createTime;
	/**找零标志：1，找零；2，不找零*/
	private Integer changeFlag;
	//单位类型：1，重量单位；2，数量单位
	private Integer unitType;
	// 打包上限
	private Integer packageMaxWeight;
	// 配送方式：1，门店自提；2，送货上门
	private Integer deliveryMode;

	/**
	 * @return the cityId
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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
	 * @return the hqProductId
	 */
	public Integer getHqProductId() {
		return hqProductId;
	}

	/**
	 * @param hqProductId the hqProductId to set
	 */
	public void setHqProductId(Integer hqProductId) {
		this.hqProductId = hqProductId;
	}

	/**
	 * @return the hqStatus
	 */
	public Integer getHqStatus() {
		return hqStatus;
	}

	/**
	 * @param hqStatus the hqStatus to set
	 */
	public void setHqStatus(Integer hqStatus) {
		this.hqStatus = hqStatus;
	}

	/**
	 * @return the cityStatus
	 */
	public Integer getCityStatus() {
		return cityStatus;
	}

	/**
	 * @param cityStatus the cityStatus to set
	 */
	public void setCityStatus(Integer cityStatus) {
		this.cityStatus = cityStatus;
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
	 * @return the disablePrice
	 */
	public String getDisablePrice() {
		return disablePrice;
	}

	/**
	 * @param disablePrice the disablePrice to set
	 */
	public void setDisablePrice(String disablePrice) {
		this.disablePrice = disablePrice;
	}

	/**
	 * @return the weightUnit
	 */
	public String getWeightUnit() {
		return weightUnit;
	}

	/**
	 * @param weightUnit the weightUnit to set
	 */
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	/**
	 * @return the numUnit
	 */
	public String getNumUnit() {
		return numUnit;
	}

	/**
	 * @param numUnit the numUnit to set
	 */
	public void setNumUnit(String numUnit) {
		this.numUnit = numUnit;
	}

	/**
	 * @return the advisePrice
	 */
	public String getAdvisePrice() {
		return advisePrice;
	}

	/**
	 * @param advisePrice the advisePrice to set
	 */
	public void setAdvisePrice(String advisePrice) {
		this.advisePrice = advisePrice;
	}


	/**
	 * @return the cityProductPic
	 */
	public String getCityProductPic() {
		return cityProductPic;
	}

	/**
	 * @param cityProductPic the cityProductPic to set
	 */
	public void setCityProductPic(String cityProductPic) {
		this.cityProductPic = cityProductPic;
	}


	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the iconPic
	 */
	public String getIconPic() {
		return iconPic;
	}

	/**
	 * @param iconPic the iconPic to set
	 */
	public void setIconPic(String iconPic) {
		this.iconPic = iconPic;
	}

	/**
	 * @return the detailUrl
	 */
	public String getDetailUrl() {
		return detailUrl;
	}

	/**
	 * @param detailUrl the detailUrl to set
	 */
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
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
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
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

	/**
	 * 获取  deliveryMode
	 * @return deliveryMode
	 */
	public Integer getDeliveryMode() {
		return deliveryMode;
	}

	/**
	 * 设置 deliveryMode
	 * @param deliveryMode
	 */
	public void setDeliveryMode(Integer deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	
	
}
