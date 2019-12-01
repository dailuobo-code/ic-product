package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SOAHealthInfo.
 */
public class SOAHealthInfo implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1326210835210487072L;
	
	/** The id. */
	private Integer id;
	
	/** The city id. */
	private Integer cityId;
	
	/** The title. */
	private String title;
	
	/** The subtitle. */
	private String subtitle;
	
	/** The content. */
	private String content;
	
	/** The img. */
	private String img;
	
	/** The publish time. */
	private String publishTime;
	
	/** The create user id. */
	private Integer createUserId;
	
	/** The create time. */
	private String createTime;
	
	/** The update user id. */
	private Integer updateUserId;
	
	/** The update time. */
	private String updateTime;

	/**
	 * 获取  id
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置 id
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * 获取  title
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置 title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取  subtitle
	 * @return subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * 设置 subtitle
	 * @param subtitle
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	/**
	 * 获取  content
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置 content
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取  img
	 * @return img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * 设置 img
	 * @param img
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * 获取  publishTime
	 * @return publishTime
	 */
	public String getPublishTime() {
		return publishTime;
	}

	/**
	 * 设置 publishTime
	 * @param publishTime
	 */
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	/**
	 * 获取  createUserId
	 * @return createUserId
	 */
	public Integer getCreateUserId() {
		return createUserId;
	}

	/**
	 * 设置 createUserId
	 * @param createUserId
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
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
	 * 获取  updateUserId
	 * @return updateUserId
	 */
	public Integer getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 设置 updateUserId
	 * @param updateUserId
	 */
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
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
}
