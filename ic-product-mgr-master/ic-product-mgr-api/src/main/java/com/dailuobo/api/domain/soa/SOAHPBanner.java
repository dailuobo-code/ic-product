/**
 * SOABanner.java
 * @author huangwei
 * @since 2016-1-26
 *  描述：
 */
package com.dailuobo.api.domain.soa;

import java.io.Serializable;

/**
 * SOABanner.java
 * @author huangwei
 * @since 2016-1-26
 *  描述：
 *  首页banner信息
 *  
 */
public class SOAHPBanner implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3384227469999696448L;

	/** bannerid */
	private Integer bannerId;

	/** 城市id */
	private Integer cityId;

	/** banner名称 */
	private String bannerName;

	/** banner类型[ 1:商品id,2:商品列表,3:链接url] */
	private Integer bannerType;

	/** 商品id */
	private Integer cityProductId;

	/** 链接页面url */
	private String bannerUrl;
	
	/** 图片地址 */
	private String bannerPic;
	
	/**
	 * 对应的序号
	 */
	private Integer order;

    /**
     * 3.0.0首页图片地址
     */
    private String bannerBigPic;

    public String getBannerBigPic() {
        return bannerBigPic;
    }

    public void setBannerBigPic(String bannerBigPic) {
        this.bannerBigPic = bannerBigPic;
    }

	/**
	 * @return the bannerId
	 */
	public Integer getBannerId() {
		return bannerId;
	}

	/**
	 * @param bannerId the bannerId to set
	 */
	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
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
	 * @return the bannerName
	 */
	public String getBannerName() {
		return bannerName;
	}

	/**
	 * @param bannerName the bannerName to set
	 */
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	/**
	 * @return the bannerType
	 */
	public Integer getBannerType() {
		return bannerType;
	}

	/**
	 * @param bannerType the bannerType to set
	 */
	public void setBannerType(Integer bannerType) {
		this.bannerType = bannerType;
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
	 * @return the bannerUrl
	 */
	public String getBannerUrl() {
		return bannerUrl;
	}

	/**
	 * @param bannerUrl the bannerUrl to set
	 */
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	/**
	 * @return the bannerPic
	 */
	public String getBannerPic() {
		return bannerPic;
	}

	/**
	 * @param bannerPic the bannerPic to set
	 */
	public void setBannerPic(String bannerPic) {
		this.bannerPic = bannerPic;
	}

	/**
	 * @return the order
	 */
	public Integer getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}
}
