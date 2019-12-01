package com.dailuobo.api.domain.soa.city;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class CityProductDto.
 */
public class CityProductDto implements Serializable {
	
	/** The Constant serialVersionUID. */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5027119511093315496L;

	/** The city product id. */
	private Integer cityProductId;
	
	/** The city id. */
	private Integer cityId;
	
	/** The hq product id. */
	private Integer hqProductId;
	
	/** The product productNo. */
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
	
	// 配送方式：1，门店自提；2，送货上门
	private Integer deliveryMode;
	
	//是否共享库存 0 否  1 是
	private Integer isShare;
	
	public String newUserPro;

	public Integer goodsType;
	
	
	/**
	 * 积分价格
	 */
	private Integer pointPrice;
	/**
	 * 积分商城状态(0-下架,1-上架)
	 */
	private Integer pointMallStatus;
	
	/**
	 * 一级分类和二级分类名称
	 */
	private String l1L2Names;
	
	private Integer storeId;
	
	/**
	 * 一分购
	 */
	private String pennySignValue;

	private Integer merchantId;

	private Integer isStandard;

	/**
	 * 主视频地址
	 */
	private String videoUrl;
	
	public String getPennySignValue() {
		return pennySignValue;
	}
	
	public void setPennySignValue(String pennySignValue) {
		this.pennySignValue = pennySignValue;
	}
	
	public Integer getStoreId() {
		return storeId;
	}
	
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	
	public Integer getPointPrice() {
		return pointPrice;
	}
	
	public void setPointPrice(Integer pointPrice) {
		this.pointPrice = pointPrice;
	}
	
	public Integer getPointMallStatus() {
		return pointMallStatus;
	}
	
	public void setPointMallStatus(Integer pointMallStatus) {
		this.pointMallStatus = pointMallStatus;
	}
	
	public String getL1L2Names() {
		return l1L2Names;
	}
	
	public void setL1L2Names(String l1L2Names) {
		this.l1L2Names = l1L2Names;
	}
	
	
	/*public Integer cityThreshold;
		
	public Integer getCityThreshold() {
		return cityThreshold;
	}

	public void setCityThreshold(Integer cityThreshold) {
		this.cityThreshold = cityThreshold;
	}*/

	public Integer getIsShare() {
		return isShare;
	}

	public void setIsShare(Integer isShare) {
		this.isShare = isShare;
	}


	public String getNewUserPro() {
		return newUserPro;
	}

	public void setNewUserPro(String newUserPro) {
		this.newUserPro = newUserPro;
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
	 * @param productNo the new product productNo
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

	public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getIsStandard() {
		return isStandard;
	}

	public void setIsStandard(Integer isStandard) {
		this.isStandard = isStandard;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
}
