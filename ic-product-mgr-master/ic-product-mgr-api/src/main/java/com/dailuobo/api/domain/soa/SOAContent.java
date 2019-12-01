package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2667759521166895062L;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 副标题
	 */
	private String subTitle;

	/**
	 * 关键字，多个以半角逗号分隔
	 */
	private String keyWord;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 访问量，默认为0
	 */
	private Integer visitCount;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 修改时间
	 */
	private String updateTime;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle == null ? null : subTitle.trim();
	}

	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
