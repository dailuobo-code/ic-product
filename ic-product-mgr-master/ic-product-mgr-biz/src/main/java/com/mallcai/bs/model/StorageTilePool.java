package com.mallcai.bs.model;

import java.io.Serializable;

/**
 * TilePool<br/>
 * 描述:
 * @author huangwei
 * @since 2016-1-20<br/>
 */
public class StorageTilePool implements Serializable{
	
	/**
	 * MongoTilePool.java
	 */
	private static final long serialVersionUID = 1300448648732227369L;

	/** 磁贴id */
	private Integer tileId;

	/** 城市id */
	private Integer cityId;

	/** 磁贴名称 */
	private String tileName;

	/**[ 1:商品id,2:商品列表,3:链接url]磁贴类型]*/
	private String tileType;

	/** 商品id */
	private Integer tileProductId;

	/** 链接页面url */
	private String tileUrl;

	/** :[ 0:删除,1:未删除] */
	private String delFlag;

	/** 磁贴icon地址 */
	private String tileIcon;
	
	private String tileImgUrl;

	/**磁帖脚标*/
	private String cornerMark;

	/**
	 * @return the tileId
	 */
	public Integer getTileId() {
		return tileId;
	}

	/**
	 * @param tileId the tileId to set
	 */
	public void setTileId(Integer tileId) {
		this.tileId = tileId;
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
	 * @return the tileName
	 */
	public String getTileName() {
		return tileName;
	}

	/**
	 * @param tileName the tileName to set
	 */
	public void setTileName(String tileName) {
		this.tileName = tileName;
	}

	/**
	 * @return the tileType
	 */
	public String getTileType() {
		return tileType;
	}

	/**
	 * @param tileType the tileType to set
	 */
	public void setTileType(String tileType) {
		this.tileType = tileType;
	}

	/**
	 * @return the tileProductId
	 */
	public Integer getTileProductId() {
		return tileProductId;
	}

	/**
	 * @param tileProductId the tileProductId to set
	 */
	public void setTileProductId(Integer tileProductId) {
		this.tileProductId = tileProductId;
	}

	/**
	 * @return the tileUrl
	 */
	public String getTileUrl() {
		return tileUrl;
	}

	/**
	 * @param tileUrl the tileUrl to set
	 */
	public void setTileUrl(String tileUrl) {
		this.tileUrl = tileUrl;
	}

	/**
	 * @return the delFlag
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * @return the tileIcon
	 */
	public String getTileIcon() {
		return tileIcon;
	}

	/**
	 * @param tileIcon the tileIcon to set
	 */
	public void setTileIcon(String tileIcon) {
		this.tileIcon = tileIcon;
	}

	/**
	 * 获取  tileImgUrl
	 * @return tileImgUrl
	 */
	public String getTileImgUrl() {
		return tileImgUrl;
	}

	/**
	 * 设置 tileImgUrl
	 * @param tileImgUrl
	 */
	public void setTileImgUrl(String tileImgUrl) {
		this.tileImgUrl = tileImgUrl;
	}

	public String getCornerMark() {
		return cornerMark;
	}

	public void setCornerMark(String cornerMark) {
		this.cornerMark = cornerMark;
	}
}
