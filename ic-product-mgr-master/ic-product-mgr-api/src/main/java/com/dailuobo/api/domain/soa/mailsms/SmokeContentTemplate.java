package com.dailuobo.api.domain.soa.mailsms;

import java.io.Serializable;

/**
 * 香烟库存异常 短信模板
 */
public class SmokeContentTemplate implements Serializable {
	private static final long serialVersionUID = 1;

	/** 详细信息 */
	private String storeName;
	private String userName;
	private String time;


	/**
	 * @param storeName
	 * @param userName
	 * @param time
	 */
	public SmokeContentTemplate(String storeName, String userName, String time) {
		super();
		this.storeName = storeName;
		this.userName = userName;
		this.time = time;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
