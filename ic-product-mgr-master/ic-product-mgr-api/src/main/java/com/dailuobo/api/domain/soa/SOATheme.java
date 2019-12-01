package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOATheme implements Serializable {

	private static final long serialVersionUID = -6031425141436995303L;
	Integer themeId;
	Integer cityId;
	String themeName;
	Integer themeType;
	Integer themeProductId;
	String themeUrl;
	String themePic;
	String memo;
	Integer status;
	Integer delFlag;
	String createTime;
	String updateTime;
	Double themeOrder;
	String themeIcon;
	Integer showType;
	String showStyle;
	Integer showNum;

	public String getShowStyle() {
		return showStyle;
	}

	public void setShowStyle(String showStyle) {
		this.showStyle = showStyle;
	}

	public Integer getShowNum() {
		return showNum;
	}

	public void setShowNum(Integer showNum) {
		this.showNum = showNum;
	}

	public Integer getShowType() {
		return showType;
	}
	public void setShowType(Integer showType) {
		this.showType = showType;
	}
	/**
	 * 获取  themeId
	 * @return themeId
	 */
	public Integer getThemeId() {
		return themeId;
	}
	/**
	 * 设置 themeId
	 * @param themeId
	 */
	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
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
	 * 获取  themeName
	 * @return themeName
	 */
	public String getThemeName() {
		return themeName;
	}
	/**
	 * 设置 themeName
	 * @param themeName
	 */
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	/**
	 * 获取  themeType
	 * @return themeType
	 */
	public Integer getThemeType() {
		return themeType;
	}
	/**
	 * 设置 themeType
	 * @param themeType
	 */
	public void setThemeType(Integer themeType) {
		this.themeType = themeType;
	}
	/**
	 * 获取  themeProductId
	 * @return themeProductId
	 */
	public Integer getThemeProductId() {
		return themeProductId;
	}
	/**
	 * 设置 themeProductId
	 * @param themeProductId
	 */
	public void setThemeProductId(Integer themeProductId) {
		this.themeProductId = themeProductId;
	}
	/**
	 * 获取  themeUrl
	 * @return themeUrl
	 */
	public String getThemeUrl() {
		return themeUrl;
	}
	/**
	 * 设置 themeUrl
	 * @param themeUrl
	 */
	public void setThemeUrl(String themeUrl) {
		this.themeUrl = themeUrl;
	}
	/**
	 * 获取  themePic
	 * @return themePic
	 */
	public String getThemePic() {
		return themePic;
	}
	/**
	 * 设置 themePic
	 * @param themePic
	 */
	public void setThemePic(String themePic) {
		this.themePic = themePic;
	}
	/**
	 * 获取  memo
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * 设置 memo
	 * @param memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 获取  status
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置 status
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取  delFlag
	 * @return delFlag
	 */
	public Integer getDelFlag() {
		return delFlag;
	}
	/**
	 * 设置 delFlag
	 * @param delFlag
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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
	 * 获取  themeOrder
	 * @return themeOrder
	 */
	public Double getThemeOrder() {
		return themeOrder;
	}
	/**
	 * 设置 themeOrder
	 * @param themeOrder
	 */
	public void setThemeOrder(Double themeOrder) {
		this.themeOrder = themeOrder;
	}
	/**
	 * 获取  themeIcon
	 * @return themeIcon
	 */
	public String getThemeIcon() {
		return themeIcon;
	}
	/**
	 * 设置 themeIcon
	 * @param themeIcon
	 */
	public void setThemeIcon(String themeIcon) {
		this.themeIcon = themeIcon;
	}
}
