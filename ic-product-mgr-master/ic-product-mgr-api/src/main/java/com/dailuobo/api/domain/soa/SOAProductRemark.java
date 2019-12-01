/**
 * SOAProductRemark.java
 * @author huangwei
 * @since 2016-1-26
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * SOAProductRemark.java
 * @author huangwei
 * @since 2016-1-26
 *  描述：
 */
public class SOAProductRemark implements Serializable, Comparable<SOAProductRemark>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6252703451380667545L;

	/** 城市id */
	private Integer cityId;

	/** 城市商品id */
	private Integer cityProductId;
	
	/** 投放名称 */
	private String showName;

	/** 显示价格 */
	private String showPrice;

	/** 失效价格 */
	private String disablePrice;
	
	/** 在列表中的显示图片 */
	private String iconPic;
	
	/** 排序 */
	private Integer productOrder;
	
	/**
	 * 商品状态
	 */
	private Integer productStatus;
	
	/**
	 * 限购份数
	 */
	private Integer threshold = 0;
	
	private String updateTime;
	
	private String createTime;
	// 磁贴商品列表中id tbl_tile_product_list
	private Integer titleListId;
	// 首页商品列表中id tbl_hp_product_list
	private Integer hpListId;

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
	 * @return the productOrder
	 */
	public Integer getProductOrder() {
		return productOrder;
	}

	/**
	 * @param productOrder the productOrder to set
	 */
	public void setProductOrder(Integer productOrder) {
		this.productOrder = productOrder;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(SOAProductRemark o) {
		//return this.getProductOrder().compareTo(o.getProductOrder());
		if(this.getProductOrder().compareTo(o.getProductOrder()) > 0)
			return 1;
		else if(this.getProductOrder().compareTo(o.getProductOrder()) < 0)
			return -1;
		else {
			if(this.getUpdateTime() == null || this.getHpListId() == null || o.getHpListId() == null || o.getUpdateTime() == null)
				return 0;
			if(this.getUpdateTime().compareTo(o.getUpdateTime()) == 0)
				return o.getHpListId().compareTo(this.getHpListId());
			else
				return o.getUpdateTime().compareTo(this.getUpdateTime());
		}
	}

	/**
	 * @return the productStatus
	 */
	public Integer getProductStatus() {
		return productStatus;
	}

	/**
	 * @param productStatus the productStatus to set
	 */
	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
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
	 * 获取  titleListId
	 * @return titleListId
	 */
	public Integer getTitleListId() {
		return titleListId;
	}

	/**
	 * 设置 titleListId
	 * @param titleListId
	 */
	public void setTitleListId(Integer titleListId) {
		this.titleListId = titleListId;
	}

	/**
	 * 获取  hpListId
	 * @return hpListId
	 */
	public Integer getHpListId() {
		return hpListId;
	}

	/**
	 * 设置 hpListId
	 * @param hpListId
	 */
	public void setHpListId(Integer hpListId) {
		this.hpListId = hpListId;
	}

}
