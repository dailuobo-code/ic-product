package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOASysParam implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5629841630682595258L;
	private String paramType;
	private String paramVal;
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	public String getParamVal() {
		return paramVal;
	}
	public void setParamVal(String paramVal) {
		this.paramVal = paramVal;
	}


	
}
