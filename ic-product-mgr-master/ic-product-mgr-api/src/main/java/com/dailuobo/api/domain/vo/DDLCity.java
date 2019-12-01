package com.dailuobo.api.domain.vo;

import java.io.Serializable;

public class DDLCity implements Serializable {
	private static final long serialVersionUID = 1;
	private Integer cityId;
	private String cityName;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
