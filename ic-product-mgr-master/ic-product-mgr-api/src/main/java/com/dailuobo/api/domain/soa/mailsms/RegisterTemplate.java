package com.dailuobo.api.domain.soa.mailsms;

import java.io.Serializable;

/**
 * MonitorTemplate<br/>
 * 描述: 监控日志
 * @author huangwei
 * @since 2016-1-15<br/>
 */
public class RegisterTemplate implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;

	/** 详细信息 */
	private String giveBalance;
	
	public RegisterTemplate(String giveBalance) {
		super();
		this.giveBalance = giveBalance;
	}

	public String getGiveBalance() {
		return giveBalance;
	}

	public void setGiveBalance(String giveBalance) {
		this.giveBalance = giveBalance;
	}


	
	
}
