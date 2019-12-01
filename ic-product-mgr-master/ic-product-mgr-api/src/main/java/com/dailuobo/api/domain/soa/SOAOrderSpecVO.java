package com.dailuobo.api.domain.soa;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderDetailVO.
 */
public class SOAOrderSpecVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 82788391092781261L;
	
	/** The order id. */
	private String orderId;
	
	/** The spec id. */
	private Integer specId;
	
	/** The price. */
	private Integer price;

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
	 * 获取  specId
	 * @return specId
	 */
	public Integer getSpecId() {
		return specId;
	}

	/**
	 * 设置 specId
	 * @param specId
	 */
	public void setSpecId(Integer specId) {
		this.specId = specId;
	}

	/**
	 * 获取  price
	 * @return price
	 */
	public Integer getPrice() {
		return price;
	}

	/**
	 * 设置 price
	 * @param price
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
}
