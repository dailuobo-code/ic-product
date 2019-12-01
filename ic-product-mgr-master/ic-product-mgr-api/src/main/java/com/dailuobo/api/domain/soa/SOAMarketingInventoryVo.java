package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAMarketingInventoryVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1219971355652123281L;
	/**
	 * 
	 */
	private Integer cityProductId;
	private Integer storeId;
	private Integer delta;
	private Integer threshold;
	private Integer createUserId;
	private Integer updateUserId;
	private Integer source;
	
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Integer updateUserId) {
		this.updateUserId = updateUserId;
	}
	public Integer getCityProductId() {
		return cityProductId;
	}
	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getDelta() {
		return delta;
	}
	public void setDelta(Integer delta) {
		this.delta = delta;
	}
	public Integer getThreshold() {
		return threshold;
	}
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	
	

}
