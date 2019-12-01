package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.List;

public class SOAHPCustom implements Serializable {

	private static final long serialVersionUID = 6885720013157799942L;
	Integer customType;
	Integer status;
	public Integer getCustomType() {
		return customType;
	}
	public void setCustomType(Integer customType) {
		this.customType = customType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
