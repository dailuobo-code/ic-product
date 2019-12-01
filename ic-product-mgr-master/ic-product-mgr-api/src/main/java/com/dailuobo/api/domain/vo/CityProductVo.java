package com.dailuobo.api.domain.vo;

import com.dailuobo.api.domain.entity.CityProduct;

import java.io.Serializable;

public class CityProductVo extends CityProduct implements Serializable {
	private static final long serialVersionUID = 1;

	private Integer updateUserId;

	public Integer getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	

}
