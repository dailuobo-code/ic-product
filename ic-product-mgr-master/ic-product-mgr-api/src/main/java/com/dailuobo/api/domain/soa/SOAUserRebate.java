package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAUserRebate implements Serializable{
   /**
	 * 
	 */
	private static final long serialVersionUID = -501562043275020084L;

	private Integer userId;
	
	private String type;
	
	public Integer getUserId() {
	return userId;
	}
	
	public void setUserId(Integer userId) {
	this.userId = userId;
	}
	
	public String getType() {
	return type;
	}
	
	public void setType(String type) {
	this.type = type;
	}
   
}
