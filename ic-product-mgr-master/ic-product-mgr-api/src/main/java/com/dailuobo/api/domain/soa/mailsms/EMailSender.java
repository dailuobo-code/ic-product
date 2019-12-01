package com.dailuobo.api.domain.soa.mailsms;

import com.dailuobo.api.domain.soa.StandardResult;

import java.io.Serializable;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * EMailSender
 * 描述:.
 *
 * @since 2015-12-24
 * @author huangwei
 */
public class EMailSender implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;
	
	/** mail相关的配置. */
	private MailConfig mailConfig;
	
	
	/**
	 * Instantiates a new e mail sender.
	 *
	 * @param mailConfig the mail config
	 */
	public EMailSender(MailConfig mailConfig){
		this.mailConfig = mailConfig;
	}
	
	/**
	 * 即时发送邮件.
	 *
	 * @param toMails 	目标收件人
	 * @param mainTitle 	mail头
	 * @param mainContent mail内容
	 * @return the boolean
	 */
	public StandardResult sendHtmlMail(List<String> toMails, String mainTitle, String mainContent){
		return HtmlMailSender.sendHtmlMail(toMails, mainTitle, mainContent);
	}
}
