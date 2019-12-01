/**
 * SOACartDetail.java
 * @author huangwei
 * @since 2016-1-28
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * SOACartDetail.java
 * @author huangwei
 * @since 2016-1-28
 *  描述：
 *  返回的购物车明细信息
 */
public class SOACartDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9100141993741838530L;

	/** 城市id */
	private Integer cityId;

	/** 城市商品id */
	private Integer cityProductId;
	
	/** 商品名称 */
	private String showName;
	
	/** 在列表中的显示图片 */
	private String iconPic;
	
	/** 销售价格 */
	private Float realPrice;
	
	/** [大|中|小]规格 */
	private String specName;
	
	/** 折合价 */
	private Float avgPrice;
	
	/** 份数 */
	private Integer num;
	
	/** 规格id */
	private String specId;

	/** [0:下架,1:上架]总部状态 */
	/*private Integer hqStatus;*/

	/** [0:下架,1:上架 2:待上架]城市状态*/
	/*private String cityStatus;*/

	/** 折合价描述 */
	private String avgPriceDetail;
	
	/** 销售价格描述 */
	private String realPriceDetail;
	
	/** 重量描述 */
	private String weightDetail;
	
	/** 数量描述 */
	private String numberDetail;
	
	/** 是否有库存 */
	private Integer productStatus;
	
	/** 每人单店限购数量 */
	private Integer threshold;
	
	private String iconTip;
	
	private Float disablePrice;
	
	private String subtitle;
	
	private String avgUnit; //折合单位
	
	private Float vipCount;  //会员折扣
	
	/** 每人账户限购数量 */
	/*private Integer cityThreshold;

	public Integer getCityThreshold() {
		return cityThreshold;
	}

	public void setCityThreshold(Integer cityThreshold) {
		this.cityThreshold = cityThreshold;
	}*/

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
	 * @return the hqStatus
	 */
	/*public Integer getHqStatus() {
		return hqStatus;
	}

	*//**
	 * @param hqStatus the hqStatus to set
	 *//*
	public void setHqStatus(Integer hqStatus) {
		this.hqStatus = hqStatus;
	}*/

	/**
	 * @return the cityStatus
	 */
	/*public String getCityStatus() {
		return cityStatus;
	}

	*//**
	 * @param cityStatus the cityStatus to set
	 *//*
	public void setCityStatus(String cityStatus) {
		this.cityStatus = cityStatus;
	}
*/
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
	 * @return the num
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
	}

	/**
	 * @return the specId
	 */
	public String getSpecId() {
		return specId;
	}

	/**
	 * @param specId the specId to set
	 */
	public void setSpecId(String specId) {
		this.specId = specId;
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
	 * 获取  subtitle
	 * @return subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * 设置 subtitle
	 * @param subtitle
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * 获取  disablePrice
	 * @return disablePrice
	 */
	public Float getDisablePrice() {
		return disablePrice;
	}

	/**
	 * 设置 disablePrice
	 * @param disablePrice
	 */
	public void setDisablePrice(Float disablePrice) {
		this.disablePrice = disablePrice;
	}

	public Float getVipCount() {
		return vipCount;
	}

	public void setVipCount(Float vipCount) {
		this.vipCount = vipCount;
	}

	public String getAvgUnit() {
		return avgUnit;
	}

	public void setAvgUnit(String avgUnit) {
		this.avgUnit = avgUnit;
	}

	
}
