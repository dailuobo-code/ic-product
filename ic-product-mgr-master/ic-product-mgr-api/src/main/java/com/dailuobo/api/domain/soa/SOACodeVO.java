package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class SOAOrderVO.
 */
public class SOACodeVO implements Serializable {

	private static final long serialVersionUID = -8939297922877621325L;
	
	private Integer batchId;
	private String cardNum;
	private String codeNum;
	private Integer facePrice;
	private String validTime;
	private Integer type;
	private Integer status;
	private Integer usedType;
	
	
	public Integer getUsedType() {
		return usedType;
	}
	public void setUsedType(Integer usedType) {
		this.usedType = usedType;
	}
	public Integer getBatchId() {
		return batchId;
	}
	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getCodeNum() {
		return codeNum;
	}
	public void setCodeNum(String codeNum) {
		this.codeNum = codeNum;
	}
	public Integer getFacePrice() {
		return facePrice;
	}
	public void setFacePrice(Integer facePrice) {
		this.facePrice = facePrice;
	}
	public String getValidTime() {
		return validTime;
	}
	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	

}
