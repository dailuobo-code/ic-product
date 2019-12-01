package com.dailuobo.api.domain.soa.mailsms;

import java.io.Serializable;

public class MessagePushMould implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;

	private Integer id;
	private Integer cityId;
	private String  mouldName;
	private String  title;
	private Integer type;
	private Integer msgType;
	private String  scheme;
	private String  content;
	private Integer linkMode;
	private Integer linkType;
	private String  linkContent;
	
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMouldName() {
		return mouldName;
	}
	public void setMouldName(String mouldName) {
		this.mouldName = mouldName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getMsgType() {
		return msgType;
	}
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getLinkType() {
		return linkType;
	}
	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}
	public String getLinkContent() {
		return linkContent;
	}
	public void setLinkContent(String linkContent) {
		this.linkContent = linkContent;
	}
	public Integer getLinkMode() {
		return linkMode;
	}
	public void setLinkMode(Integer linkMode) {
		this.linkMode = linkMode;
	}
	
 
}
