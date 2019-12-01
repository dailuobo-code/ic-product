package com.dailuobo.api.domain.soa;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class SOAStoreProduct.
 */
public class SOAStorePresellProduct implements Serializable, Comparable<SOAStorePresellProduct> {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7856917086772190456L;

	/** The city product id. */
	private Integer cityProductId;
	
	/** The city id. */
	private Integer cityId;
	
	/** The hq product id. */
	private Integer hqProductId;
	
	/** The product no. */
	private String productNo;
	
	/** The hq status. */
	// 总部状态：0，下架；1，上架
	private Integer hqStatus;
	
	/** The city status. */
	// 城市状态：0，待上架；1，上架；2，下架
	private Integer cityStatus;
	
	/** The show name. */
	private String showName;
	
	/** The disable price. */
	private Float disablePrice;
	
	/** The weight unit. */
	private String weightUnit;
	
	/** The num unit. */
	private String numUnit;
	
	/** The origin. */
	private String origin;
	
	/** The advise price. */
	private Float advisePrice;
	
	/** The remark. */
	private String remark;
	
	/** The city product icon. */
	private String cityProductIcon;
	
	/** The city product pic. */
	private String cityProductPic;
	
	/** The detail url. */
	private String detailUrl;
	
	/** The home flag. */
	private Integer homeFlag;
	
	/** The del flag. */
	private Integer delFlag;
	
	/** The create time. */
	private String createTime;
	
	/** The update time. */
	private String updateTime;
	
	/** The subtitle. */
	private String subtitle;
	
	/** The keyword. */
	private String keyword;
	
	/** The icon tip. */
	private String iconTip;
	
	/** The spec id. */
	private Integer specId; 
	
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
	
	/** The product status. */
	private Integer productStatus;
	
	/** 折合价描述 */
	private String avgPriceDetail;
	
	/** 销售价格描述 */
	private String realPriceDetail;
	
	/** 重量描述 */
	private String weightDetail;
	
	/** 数量描述 */
	private String numberDetail;
	
	private Integer standardFlag;
	
	private boolean isHave;
	
	private Integer productOrder;
	
	// 配送方式：1，门店自提；2，送货上门
	private Integer deliveryMode;
	
	//是否共享库存 0 否  1 是
	private Integer isShare;
	
	private String pickupTime;
	
	private String presellStartTime;
	
	private String presellEndTime;
	
	private Integer presellId;

	private String status; //预售状态
	
	private String imgUrl;
	
	private String presellTitle;
	
	private String presellIcon;
	
	private String presellPic;
	
	private Float vipCount;
	
	
	public Float getVipCount() {
		return vipCount;
	}

	public void setVipCount(Float vipCount) {
		this.vipCount = vipCount;
	}

	private Integer stockNum;		//zyc 添加于2018-03-13 ：新增属性，用于预售判断是否有库存，该状态不区分上下架情况
	
	
	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public String getPresellTitle() {
		return presellTitle;
	}

	public void setPresellTitle(String presellTitle) {
		this.presellTitle = presellTitle;
	}

	public String getPresellIcon() {
		return presellIcon;
	}

	public void setPresellIcon(String presellIcon) {
		this.presellIcon = presellIcon;
	}

	public String getPresellPic() {
		return presellPic;
	}

	public void setPresellPic(String presellPic) {
		this.presellPic = presellPic;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getPresellId() {
		return presellId;
	}

	public void setPresellId(Integer presellId) {
		this.presellId = presellId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}

	public String getPresellStartTime() {
		return presellStartTime;
	}

	public void setPresellStartTime(String presellStartTime) {
		this.presellStartTime = presellStartTime;
	}

	public String getPresellEndTime() {
		return presellEndTime;
	}

	public void setPresellEndTime(String presellEndTime) {
		this.presellEndTime = presellEndTime;
	}

	public Integer getIsShare() {
		return isShare;
	}

	public void setIsShare(Integer isShare) {
		this.isShare = isShare;
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
	 * 获取  cityId.
	 *
	 * @return cityId
	 */
	public Integer getCityId() {
		return cityId;
	}

	/**
	 * 设置 cityId.
	 *
	 * @param cityId the new city id
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	/**
	 * 获取  hqProductId.
	 *
	 * @return hqProductId
	 */
	public Integer getHqProductId() {
		return hqProductId;
	}

	/**
	 * 设置 hqProductId.
	 *
	 * @param hqProductId the new hq product id
	 */
	public void setHqProductId(Integer hqProductId) {
		this.hqProductId = hqProductId;
	}

	/**
	 * 获取  productNo.
	 *
	 * @return productNo
	 */
	public String getProductNo() {
		return productNo;
	}

	/**
	 * 设置 productNo.
	 *
	 * @param productNo the new product no
	 */
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	/**
	 * 获取  hqStatus.
	 *
	 * @return hqStatus
	 */
	public Integer getHqStatus() {
		return hqStatus;
	}

	/**
	 * 设置 hqStatus.
	 *
	 * @param hqStatus the new hq status
	 */
	public void setHqStatus(Integer hqStatus) {
		this.hqStatus = hqStatus;
	}

	/**
	 * 获取  showName.
	 *
	 * @return showName
	 */
	public String getShowName() {
		return showName;
	}

	/**
	 * 设置 showName.
	 *
	 * @param showName the new show name
	 */
	public void setShowName(String showName) {
		this.showName = showName;
	}

	/**
	 * 获取  disablePrice.
	 *
	 * @return disablePrice
	 */
	public Float getDisablePrice() {
		return disablePrice;
	}

	/**
	 * 设置 disablePrice.
	 *
	 * @param disablePrice the new disable price
	 */
	public void setDisablePrice(Float disablePrice) {
		this.disablePrice = disablePrice;
	}

	/**
	 * 获取  weightUnit.
	 *
	 * @return weightUnit
	 */
	public String getWeightUnit() {
		return weightUnit;
	}

	/**
	 * 设置 weightUnit.
	 *
	 * @param weightUnit the new weight unit
	 */
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	/**
	 * 获取  numUnit.
	 *
	 * @return numUnit
	 */
	public String getNumUnit() {
		return numUnit;
	}

	/**
	 * 设置 numUnit.
	 *
	 * @param numUnit the new num unit
	 */
	public void setNumUnit(String numUnit) {
		this.numUnit = numUnit;
	}

	/**
	 * 获取  origin.
	 *
	 * @return origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * 设置 origin.
	 *
	 * @param origin the new origin
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * 获取  advisePrice.
	 *
	 * @return advisePrice
	 */
	public Float getAdvisePrice() {
		return advisePrice;
	}

	/**
	 * 设置 advisePrice.
	 *
	 * @param advisePrice the new advise price
	 */
	public void setAdvisePrice(Float advisePrice) {
		this.advisePrice = advisePrice;
	}

	/**
	 * 获取  remark.
	 *
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置 remark.
	 *
	 * @param remark the new remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获取  cityProductIcon.
	 *
	 * @return cityProductIcon
	 */
	public String getCityProductIcon() {
		return cityProductIcon;
	}

	/**
	 * 设置 cityProductIcon.
	 *
	 * @param cityProductIcon the new city product icon
	 */
	public void setCityProductIcon(String cityProductIcon) {
		this.cityProductIcon = cityProductIcon;
	}

	/**
	 * 获取  cityProductPic.
	 *
	 * @return cityProductPic
	 */
	public String getCityProductPic() {
		return cityProductPic;
	}

	/**
	 * 设置 cityProductPic.
	 *
	 * @param cityProductPic the new city product pic
	 */
	public void setCityProductPic(String cityProductPic) {
		this.cityProductPic = cityProductPic;
	}

	/**
	 * 获取  detailUrl.
	 *
	 * @return detailUrl
	 */
	public String getDetailUrl() {
		return detailUrl;
	}

	/**
	 * 设置 detailUrl.
	 *
	 * @param detailUrl the new detail url
	 */
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}

	/**
	 * 获取  homeFlag.
	 *
	 * @return homeFlag
	 */
	public Integer getHomeFlag() {
		return homeFlag;
	}

	/**
	 * 设置 homeFlag.
	 *
	 * @param homeFlag the new home flag
	 */
	public void setHomeFlag(Integer homeFlag) {
		this.homeFlag = homeFlag;
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
	 * 获取  createTime.
	 *
	 * @return createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置 createTime.
	 *
	 * @param createTime the new creates the time
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取  updateTime.
	 *
	 * @return updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 设置 updateTime.
	 *
	 * @param updateTime the new update time
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 获取  subtitle.
	 *
	 * @return subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * 设置 subtitle.
	 *
	 * @param subtitle the new subtitle
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * 获取  keyword.
	 *
	 * @return keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * 设置 keyword.
	 *
	 * @param keyword the new keyword
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 获取  iconTip.
	 *
	 * @return iconTip
	 */
	public String getIconTip() {
		return iconTip;
	}

	/**
	 * 设置 iconTip.
	 *
	 * @param iconTip the new icon tip
	 */
	public void setIconTip(String iconTip) {
		this.iconTip = iconTip;
	}

	/**
	 * 获取  specId.
	 *
	 * @return specId
	 */
	public Integer getSpecId() {
		return specId;
	}

	/**
	 * 设置 specId.
	 *
	 * @param specId the new spec id
	 */
	public void setSpecId(Integer specId) {
		this.specId = specId;
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
	 * 获取  cityStatus
	 * @return cityStatus
	 */
	public Integer getCityStatus() {
		return cityStatus;
	}

	/**
	 * 设置 cityStatus
	 * @param cityStatus
	 */
	public void setCityStatus(Integer cityStatus) {
		this.cityStatus = cityStatus;
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
	 * 获取  productStatus
	 * @return productStatus
	 */
	public Integer getProductStatus() {
		return productStatus;
	}

	/**
	 * 设置 productStatus
	 * @param productStatus
	 */
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
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
	 * 获取  isHave
	 * @return isHave
	 */
	public boolean isHave() {
		return isHave;
	}

	/**
	 * 设置 isHave
	 * @param isHave
	 */
	public void setHave(boolean isHave) {
		this.isHave = isHave;
	}

	/**
	 * 获取  productOrder
	 * @return productOrder
	 */
	public Integer getProductOrder() {
		return productOrder;
	}

	/**
	 * 设置 productOrder
	 * @param productOrder
	 */
	public void setProductOrder(Integer productOrder) {
		this.productOrder = productOrder;
	}

	@Override
	public int compareTo(SOAStorePresellProduct o) {
		if(this.productOrder == null) this.setProductOrder(999);
		if(o.productOrder == null) o.setProductOrder(999);
		
		if(this.productOrder.compareTo(o.getProductOrder()) == 0) {
			if(StringUtils.isBlank(this.updateTime)) this.setUpdateTime("");
			if(StringUtils.isBlank(o.getUpdateTime())) o.setUpdateTime("");
			
			if(this.updateTime.compareTo(o.getUpdateTime()) == 0) {
				if(StringUtils.isBlank(this.createTime)) this.setCreateTime("");
				if(StringUtils.isBlank(o.getCreateTime())) o.setCreateTime("");
				
				return o.getCreateTime().compareTo(this.createTime);
			}
			
			return o.getUpdateTime().compareTo(this.updateTime);
		}
		
		return this.productOrder.compareTo(o.getProductOrder());
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
