package com.dailuobo.api.domain.soa.mailsms;

import java.io.Serializable;
import java.util.List;

public class MessagePush implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;
	private Integer id;
	private Integer cityId;
	private String title;
	private List<Integer> userIds;
	private Integer userId;
	private Integer mouldId;
	private Integer type;
	private Integer msgType;
	private String scheme;
	private String content;
	private Integer linkType;
	private Integer linkMode;
	private String linkContent;
	private String createTime;
	
	
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
	public List<Integer> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getMouldId() {
		return mouldId;
	}
	public void setMouldId(Integer mouldId) {
		this.mouldId = mouldId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getLinkMode() {
		return linkMode;
	}
	public void setLinkMode(Integer linkMode) {
		this.linkMode = linkMode;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
 
}
