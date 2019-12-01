package com.mallcai.bs.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * MongoBannerPool<br/>
 * 描述:
 * mongodb中的banner池记录集 
 * @author huangwei
 * @since 2016-1-20<br/>
 */
@Data
public class StorageBannerPool implements Serializable{

	/**
	 * MongoBannerPool.java
	 */
	private static final long serialVersionUID = 5371336153057581860L;

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

	/** [ 0:删除,1:未删除], */
	private Integer delFlag;

	/** 图片地址 */
	private String bannerPic;

    /**
     * 3.0.0首页图片地址
     */
    private String bannerBigPic;


	/**
	 *  预约开始时间(2019-07-05 添加)
	 */
	private Date futureStartTime;

	/**
	 * 预约结束时间(2019-07-05 添加)
	 */
	private Date futureEndTime;


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
	 * @return the delFlag
	 */
	public Integer getDelFlag() {
		return delFlag;
	}

	/**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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


	
}
