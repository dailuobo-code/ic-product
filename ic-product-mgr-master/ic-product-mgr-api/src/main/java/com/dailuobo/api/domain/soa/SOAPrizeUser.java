package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAPrizeUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3270852852790214835L;

	private String phone;

	private String prizeName;

	private Integer prizeNum;

	private Integer prizeType;

	private String createTime;
	
	private Integer compose;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName == null ? null : prizeName.trim();
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getCompose() {
		return compose;
	}

	public void setCompose(Integer compose) {
		this.compose = compose;
	}
	

}