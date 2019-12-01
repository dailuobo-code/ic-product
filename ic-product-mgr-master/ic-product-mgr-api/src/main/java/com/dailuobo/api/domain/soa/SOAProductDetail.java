/**
 * SOAProductDetail.java
 * @author huangwei
 * @since 2016-1-27
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * SOAProductDetail.java
 * @author huangwei
 * @since 2016-1-27
 *  描述：商品详细信息，包括规格
 */
public class SOAProductDetail implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 219757924126334785L;

	/** 城市id */
	private Integer cityId;

	/** 城市商品id */
	private Integer cityProductId;

	/** 总部商品id，hq_status */
	private Integer hqProductId;

	/** [0:下架,1:上架]总部状态 */
	private Integer hqStatus;

	/** [0:下架,1:上架 2:待上架]城市状态*/
	private String cityStatus;

	/** 投放名称 */
	private String showName;

	/** 显示价格 */
	private String showPrice;

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
	
	/** 基础价格，真实的价格是基础价格 * 折扣*/
	private Double basePrice;
	
	/** 是否有库存 */
	private Boolean isHave = true;
	
	
	
	
	/**对应的规格*/
	private List<SOASalesSpecVO> specList = new ArrayList<SOASalesSpecVO>();

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
	public String getCityStatus() {
		return cityStatus;
	}

	/**
	 * @param cityStatus the cityStatus to set
	 */
	public void setCityStatus(String cityStatus) {
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
	 * @return the showPrice
	 */
	public String getShowPrice() {
		return showPrice;
	}

	/**
	 * @param showPrice the showPrice to set
	 */
	public void setShowPrice(String showPrice) {
		this.showPrice = showPrice;
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
	 * @return the specList
	 */
	public List<SOASalesSpecVO> getSpecList() {
		return specList;
	}

	/**
	 * @param specList the specList to set
	 */
	public void setSpecList(List<SOASalesSpecVO> specList) {
		this.specList = specList;
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
	 * @return the basePrice
	 */
	public Double getBasePrice() {
		return basePrice;
	}

	/**
	 * @param basePrice the basePrice to set
	 */
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	
	
}
