/**
 * StockAlarmTemplate.java
 * @author huangwei
 * @since 2016-4-11
 *  描述：
 */
package com.dailuobo.api.domain.soa.mailsms;

import java.io.Serializable;

/**
 * StockAlarmTemplate.java
 * @author huangwei
 * @since 2016-4-11
 *  描述：
 */
public class StockAlarmTemplate implements Serializable {
	private static final long serialVersionUID = 1;

	
	private String productname;
	
	/**
	 * @param cityProductName
	 * @param cityProductId
	 */
	public StockAlarmTemplate(String productname) {
		super();
		this.productname = productname;
	}

	/**
	 * @return the productname
	 */
	public String getProductname() {
		return productname;
	}

	/**
	 * @param productname the productname to set
	 */
	public void setProductname(String productname) {
		this.productname = productname;
	}


}
