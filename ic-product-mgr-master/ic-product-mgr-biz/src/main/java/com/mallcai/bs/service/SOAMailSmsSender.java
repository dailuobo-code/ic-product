package com.mallcai.bs.service;

import com.dailuobo.api.domain.soa.StandardResult;
import com.dailuobo.api.domain.soa.mailsms.*;

import java.util.List;
/**
 * 2018-12-13
 * depends-on="com.mallcai.utils.BaseConfig"
 * @author rhythm
 *
 */

public class SOAMailSmsSender {
	/** 邮件发送者 */
	private EMailSender emailSender =  new EMailSender(MailConfig.getDefaultConfig());
	
	/** 短信发送者 */
	private SMSSender smsSender = new SMSSender(SMSConfig.getDefaultConfig());
	
	private WWSender wwSender = new WWSender(WWConfig.getDefaultConfig());


	public StandardResult sendHtmlMail(List<String> toMails, String mainTitle,
			String mainContent) {
		return emailSender.sendHtmlMail(toMails, mainTitle, mainContent);
	}


	public StandardResult sendStockAlarmSMS(List<String> targetMobileList, String cityProductName,
			String cityProductCode) {
		/*return smsSender.sendStockAlarmSMS(targetMobileList, cityProductName, cityProductCode);*/ 
		StandardResult wwResult = wwSender.sendStockAlarmSMS(targetMobileList, cityProductName, cityProductCode);
		if(!wwResult.getSuccess()){
			return smsSender.sendStockAlarmSMS(targetMobileList, cityProductName, cityProductCode);
		}
		return wwResult;
		//2018-11-09 yr 短信方式
	}
	
	public StandardResult sendInventorySMS(String phone, String storeName,String checkUserName) {
		/*return smsSender.sendInventorySMS(phone,storeName,checkUserName);*/
		StandardResult wwResult =  wwSender.sendInventorySMS(phone,storeName,checkUserName);
		if(!wwResult.getSuccess()){
			return smsSender.sendInventorySMS(phone,storeName,checkUserName);
		}
		return wwResult;
	}
}
