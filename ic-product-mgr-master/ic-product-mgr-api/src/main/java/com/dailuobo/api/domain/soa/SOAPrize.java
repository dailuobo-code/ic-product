package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAPrize implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3779611044190933567L;

	private Integer id;

	private String prizeName;

	private String prizeIcon;

	private Integer prizeNum;

	private Integer prizeType;

	private Integer ordnum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName == null ? null : prizeName.trim();
	}

	public String getPrizeIcon() {
		return prizeIcon;
	}

	public void setPrizeIcon(String prizeIcon) {
		this.prizeIcon = prizeIcon == null ? null : prizeIcon.trim();
	}

	public Integer getPrizeNum() {
		return prizeNum;
	}

	public void setPrizeNum(Integer prizeNum) {
		this.prizeNum = prizeNum;
	}

	public Integer getPrizeType() {
		return prizeType;
	}

	public void setPrizeType(Integer prizeType) {
		this.prizeType = prizeType;
	}

	public Integer getOrdnum() {
		return ordnum;
	}

	public void setOrdnum(Integer ordnum) {
		this.ordnum = ordnum;
	}
}