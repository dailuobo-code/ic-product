package com.dailuobo.api.domain.soa.mailsms;

import java.io.Serializable;

/**
 * ValidateTemplate<br/>
 * 描述: 验证码模板
 * @author huangwei
 * @since 2016-1-15<br/>
 */
public class ValidateTemplate implements Serializable {
	private static final long serialVersionUID = 1;

	/** 值 */
	private String code;
	
	/** 产品名称 */
	private String product;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * @param code
	 */
	public ValidateTemplate(String code) {
		super();
		this.code = code;
	}

	/**
	 * @param code
	 * @param product
	 */
	public ValidateTemplate(String code, String product) {
		super();
		this.code = code;
		this.product = product;
	}
	
	
}
