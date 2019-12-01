package com.dailuobo.api.domain.soa.city;

import java.io.Serializable;

public class SystemParamDto implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;
	
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
