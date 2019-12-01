package com.dailuobo.api.domain.soa;

import java.io.Serializable;

public class SOAAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1345507665091441396L;
	
	/**
	 * 联系人
	 */
	private String contacter;
	
	/**
	 * 联系电话
	 */
	private String contactphone;
	
	/**
	 * 联系地址
	 */
	private String address;
	
	public String getContacter() {
		return contacter;
	}
	public void setContacter(String contacter) {
		this.contacter = contacter;
	}
	public String getContactphone() {
		return contactphone;
	}
	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

}
