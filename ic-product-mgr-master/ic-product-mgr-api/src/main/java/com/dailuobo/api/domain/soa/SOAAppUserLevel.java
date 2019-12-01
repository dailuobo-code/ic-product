package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class AppUserLevel.
 */
public class SOAAppUserLevel implements Serializable {
	
	private static final long serialVersionUID = 3009722696607710662L;

	/** The id. */
	private Integer id;
	
	/** The level_name. */
	private String level_name;
	
	/** The lower_growth. */
	private Integer lower_growth;

	/**
	 * 获取  level_name
	 * @return level_name
	 */
	public String getLevel_name() {
		return level_name;
	}

	/**
	 * 设置 level_name
	 * @param level_name
	 */
	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}

	/**
	 * 获取  lower_growth
	 * @return lower_growth
	 */
	public Integer getLower_growth() {
		return lower_growth;
	}

	/**
	 * 设置 lower_growth
	 * @param lower_growth
	 */
	public void setLower_growth(Integer lower_growth) {
		this.lower_growth = lower_growth;
	}

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
}
