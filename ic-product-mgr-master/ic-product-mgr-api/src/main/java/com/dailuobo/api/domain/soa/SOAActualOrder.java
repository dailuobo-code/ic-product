package com.dailuobo.api.domain.soa;

import java.io.Serializable;
import java.util.List;

public class SOAActualOrder implements Serializable {

	private static final long serialVersionUID = 3842930064756074620L;
	
	private String orderId;
	private Integer cityProductId;
	private List<Integer> actualWeight;
	private List<Integer> isOutStock;
	private List<String> sortingStr;
	private Integer operUserId;
	/**
	 * 获取  orderId
	 * @return orderId
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * 设置 orderId
	 * @param orderId
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取  cityProductId
	 * @return cityProductId
	 */
	public Integer getCityProductId() {
		return cityProductId;
	}
	/**
	 * 设置 cityProductId
	 * @param cityProductId
	 */
	public void setCityProductId(Integer cityProductId) {
		this.cityProductId = cityProductId;
	}
	/**
	 * 获取  actualWeight
	 * @return actualWeight
	 */
	public List<Integer> getActualWeight() {
		return actualWeight;
	}
	/**
	 * 设置 actualWeight
	 * @param actualWeight
	 */
	public void setActualWeight(List<Integer> actualWeight) {
		this.actualWeight = actualWeight;
	}
	/**
	 * 获取  isOutStock
	 * @return isOutStock
	 */
	public List<Integer> getIsOutStock() {
		return isOutStock;
	}
	/**
	 * 设置 isOutStock
	 * @param isOutStock
	 */
	public void setIsOutStock(List<Integer> isOutStock) {
		this.isOutStock = isOutStock;
	}
	/**
	 * 获取  operUserId
	 * @return operUserId
	 */
	public Integer getOperUserId() {
		return operUserId;
	}
	/**
	 * 设置 operUserId
	 * @param operUserId
	 */
	public void setOperUserId(Integer operUserId) {
		this.operUserId = operUserId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SOAActualOrder [\n orderId=" + orderId + "\n cityProductId=" + cityProductId + "\n actualWeight="
				+ actualWeight + "\n isOutStock=" + isOutStock + "\n operUserId=" + operUserId + "\n]";
	}
	public List<String> getSortingStr() {
		return sortingStr;
	}
	public void setSortingStr(List<String> sortingStr) {
		this.sortingStr = sortingStr;
	}
	
	
}
