package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.Date;

public class SOASignUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3265186391809635649L;

	private Integer userNum;
	private String time;
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public SOASignUser() {
		super();
	}

	public SOASignUser(Integer userNum) {
		super();
		this.userNum = userNum;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}
	
	
}
