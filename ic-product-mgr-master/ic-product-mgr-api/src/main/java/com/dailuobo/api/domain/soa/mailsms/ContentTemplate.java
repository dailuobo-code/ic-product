package com.dailuobo.api.domain.soa.mailsms;

import java.io.Serializable;

/**
 * MonitorTemplate<br/>
 * 描述: 监控日志
 * @author huangwei
 * @since 2016-1-15<br/>
 */
public class ContentTemplate implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;

	/** 详细信息 */
	private String content;


	/**
	 * @param content
	 */
	public ContentTemplate(String content) {
		super();
		this.content = content;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
