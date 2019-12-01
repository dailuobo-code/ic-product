package com.dailuobo.api.domain.soa.mailsms;

import java.io.Serializable;

/**
 * MonitorTemplate<br/>
 * 描述: 监控日志
 * @author huangwei
 * @since 2016-1-15<br/>
 */
public class ActivityTemplate implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;

	/** 详细信息 */
	private String activity;
	private String date;
	private String storeName;

	/**
	 * @param content
	 */
	public ActivityTemplate(String activity,String date,String storeName) {
		super();
		this.activity = activity;
		this.date = date;
		this.storeName = storeName;
	}


	public String getActivity() {
		return activity;
	}


	public void setActivity(String activity) {
		this.activity = activity;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	
	
}
