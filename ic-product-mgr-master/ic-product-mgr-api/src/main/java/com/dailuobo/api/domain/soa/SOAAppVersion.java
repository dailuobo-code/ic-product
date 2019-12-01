package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAAppVersion implements Serializable {

	private static final long serialVersionUID = 1856382452242555575L;
	
	/** The version code. */
	private String versionCode;
	
	/** The version name. */
	private String versionName;
	
	/** The download url. */
	private String downloadUrl;
	
	/** The update text. */
	private String updateText;
	
	/** The is must. */
	private String isMust;
	
	/** The device type. */
	private String deviceType;
	
	/** The is hint. */
	private String isHint;

	private String patchCode;
	
	
	public String getPatchCode() {
		return patchCode;
	}

	public void setPatchCode(String patchCode) {
		this.patchCode = patchCode;
	}

	/**
	 * 获取  versionCode
	 * @return versionCode
	 */
	public String getVersionCode() {
		return versionCode;
	}

	/**
	 * 设置 versionCode
	 * @param versionCode
	 */
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	/**
	 * 获取  versionName
	 * @return versionName
	 */
	public String getVersionName() {
		return versionName;
	}

	/**
	 * 设置 versionName
	 * @param versionName
	 */
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	/**
	 * 获取  downloadUrl
	 * @return downloadUrl
	 */
	public String getDownloadUrl() {
		return downloadUrl;
	}

	/**
	 * 设置 downloadUrl
	 * @param downloadUrl
	 */
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	/**
	 * 获取  updateText
	 * @return updateText
	 */
	public String getUpdateText() {
		return updateText;
	}

	/**
	 * 设置 updateText
	 * @param updateText
	 */
	public void setUpdateText(String updateText) {
		this.updateText = updateText;
	}

	/**
	 * 获取  isMust
	 * @return isMust
	 */
	public String getIsMust() {
		return isMust;
	}

	/**
	 * 设置 isMust
	 * @param isMust
	 */
	public void setIsMust(String isMust) {
		this.isMust = isMust;
	}

	/**
	 * 获取  deviceType
	 * @return deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}

	/**
	 * 设置 deviceType
	 * @param deviceType
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * 获取  isHint
	 * @return isHint
	 */
	public String getIsHint() {
		return isHint;
	}

	/**
	 * 设置 isHint
	 * @param isHint
	 */
	public void setIsHint(String isHint) {
		this.isHint = isHint;
	}
}
