package com.dailuobo.api.domain.vo;

import java.io.Serializable;
import java.util.List;

public class DefaultSpecVo implements Serializable {
	private static final long serialVersionUID = 1;
	private Integer cityId;
    private Integer cityProductId;
    private Double realPrice;
    private Double avgPrice;
    private Integer threshold;
    private Integer userId;
    private List<Integer> storeIds;
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getCityProductId() {
		return cityProductId;
	}
	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}
	public Double getRealPrice() {
		return realPrice;
	}
	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}
	public Double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
	}
	public Integer getThreshold() {
		return threshold;
	}
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<Integer> getStoreIds() {
		return storeIds;
	}
	public void setStoreIds(List<Integer> storeIds) {
		this.storeIds = storeIds;
	}
	public DefaultSpecVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DefaultSpecVo(Integer cityId, Integer cityProductId,
			Double realPrice, Double avgPrice, Integer threshold, Integer userId) {
		super();
		this.cityId = cityId;
		this.cityProductId = cityProductId;
		this.realPrice = realPrice;
		this.avgPrice = avgPrice;
		this.threshold = threshold;
		this.userId = userId;
	}
    
    
}
