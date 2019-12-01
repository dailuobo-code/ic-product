package com.dailuobo.api.domain.soa.mailsms;

import com.alibaba.fastjson.JSON;
import com.dailuobo.api.domain.soa.StandardResult;
import com.taobao.api.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * SMSSender<br/>
 * 描述:
 * @author huangwei
 * @since 2016-1-15<br/>
 */
public class SMSSender implements Serializable {
	private static final long serialVersionUID = 1;
	
	/** 短信配置 */
	private SMSConfig smsConfig;
	
	/** logger */
	private Logger logger = LoggerFactory.getLogger(SMSSender.class);
	
	public SMSSender(SMSConfig smsConfig){
		this.smsConfig = smsConfig;
	}

	/**
	 * 发送验证码短信
	 * @param targetMobile	目标手机
	 * @param validateCode	验证码
	 * @return
	 */
	public StandardResult sendValidateCode(String targetMobile, String validateCode){
		String smsParam = JSON.toJSONString(new ValidateTemplate(validateCode, "呆萝卜"));
		try {
			String result = BaseSMSSender.sendSMS(smsConfig.getUrl(), smsConfig.getAppkey(), smsConfig.getAppsecret(), 
								  smsConfig.getResultformat(), smsConfig.getSmsType(), smsConfig.getSmsFreeSignName(),
								  smsParam, smsConfig.getSmsValidateTemplateCode(), targetMobile);
			return ResultParser.parseJSON(result);
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}
	}
	
	
	/**
	 * 发送验证码短信
	 * @param targetMobile	目标手机
	 * @param host			服务器
	 * @param serviceName	运行的服务名称
	 * @param content		错误信息
	 * @return
	 */
	public StandardResult sendMonitorSMS(String targetMobile, String content){
		String smsParam = JSON.toJSONString(new ContentTemplate(content));
		try {
			String result = BaseSMSSender.sendSMS(smsConfig.getUrl(), smsConfig.getAppkey(), smsConfig.getAppsecret(), 
								  smsConfig.getResultformat(), smsConfig.getSmsType(), smsConfig.getSmsFreeSignName(),
								  smsParam, smsConfig.getSmsMonitorTemplateCode(), targetMobile);
			return ResultParser.parseJSON(result);
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}
	}

	/**
	 * 发送库存报警短信
	 * @param targetMobileList	目标手机
	 * @param cityProductName	城市商品名称
	 * @param cityProductCode	城市商品code
	 * @return
	 */
	public StandardResult sendStockAlarmSMS(List<String> targetMobileList,
			String cityProductName, String cityProductCode) {
		StringBuffer sb = new StringBuffer();
		for(String mobile:targetMobileList){
			sb.append(mobile).append(",");
		}
		String targetMobile = sb.substring(0, sb.length() - 1);
		String smsParam = JSON.toJSONString(new StockAlarmTemplate(cityProductName + "[商品编号:" + cityProductCode + "]"));
		try {
			String result = BaseSMSSender.sendSMS(smsConfig.getUrl(), smsConfig.getAppkey(), smsConfig.getAppsecret(), 
								  smsConfig.getResultformat(), smsConfig.getSmsType(), "库存告警",
								  smsParam, smsConfig.getStockAlarmTemplateCode(), targetMobile);
			return ResultParser.parseJSON(result);
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}
	}
	
	// 发送待获取短信通知
	public StandardResult sendPickupNotice(String phoneNo, Integer noticeType) {
		String templateCode = "";
		if(noticeType == 1){
			templateCode = smsConfig.getSmsPickupTemplateCode1();
		}else if(noticeType == 2){
			templateCode = smsConfig.getSmsPickupTemplateCode2();
		}else if(noticeType == 3){
			templateCode = smsConfig.getSmsPickupTemplateCode3();
		}
		try {
			String result = BaseSMSSender.sendSMS(smsConfig.getUrl(), smsConfig.getAppkey(), smsConfig.getAppsecret(), 
				  smsConfig.getResultformat(), smsConfig.getSmsType(), "呆萝卜", null, templateCode, phoneNo);
			
			StandardResult sr = ResultParser.parseJSON(result);
			if(sr.getSuccess()) {
				logger.info("发送取货提醒短信成功,号码组:" + phoneNo);
			} else {
				logger.error("发送取货提醒短信失败,号码组:" + phoneNo + ",失败原因:" + sr.getErrorMsg());
			}
			return sr;
		} catch (ApiException e) {
			logger.error("发送取货提醒短信异常,号码组:" + phoneNo + ",异常原因:" + e.getMessage(), e);
			return StandardResult.getDefaultFailedMsg(e.getMessage());
		}
	}
	
	public StandardResult sendUndStockAlarmSMS(List<String> targetMobileList,
			String cityProductName) {
		StringBuffer sb = new StringBuffer();
		for(String mobile:targetMobileList){
			sb.append(mobile).append(",");
		}
		String targetMobile = sb.substring(0, sb.length() - 1);
		String smsParam = JSON.toJSONString(new StockAlarmTemplate(cityProductName));
		try {
			String result = BaseSMSSender.sendSMS(smsConfig.getUrl(), smsConfig.getAppkey(), smsConfig.getAppsecret(), 
								  smsConfig.getResultformat(), smsConfig.getSmsType(), "库存告警",
								  smsParam, smsConfig.getStockAlarmTemplateCode(), targetMobile);
			return ResultParser.parseJSON(result);
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}
	}
	
	/** 
	 发送提醒短信
	*/
	public StandardResult sendActivitySMS(String targetMobile, String activity,String date,String storeName){
		String smsParam = JSON.toJSONString(new ActivityTemplate(activity,date,storeName));
		try {
			String result = BaseSMSSender.sendSMS(smsConfig.getUrl(), smsConfig.getAppkey(), smsConfig.getAppsecret(), 
								  smsConfig.getResultformat(), smsConfig.getSmsType(), "呆萝卜",
								  smsParam, smsConfig.getActivityTemplateCode(), targetMobile);
			return ResultParser.parseJSON(result);
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}
	}

	public StandardResult sendInventorySMS(String targetMobile, String storeName, String checkUserName) {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = sf.format(date);
		String smsParam = JSON.toJSONString(new SmokeContentTemplate(storeName, checkUserName, time));
		try {
			String result = BaseSMSSender.sendSMS(smsConfig.getUrl(), smsConfig.getAppkey(), smsConfig.getAppsecret(),
					smsConfig.getResultformat(), smsConfig.getSmsType(), smsConfig.getSmsFreeSignName(),
					smsParam, smsConfig.getPropSmsSmokeTemplateCode(), targetMobile);
			return ResultParser.parseJSON(result);
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}
	}
	
	public StandardResult sendRegisterSMS(String phone, String giveBalance) {
		try {
			String smsParam = JSON.toJSONString(new RegisterTemplate(giveBalance));
			String result = BaseSMSSender.sendSMS(smsConfig.getUrl(), smsConfig.getAppkey(), smsConfig.getAppsecret(), 
								  smsConfig.getResultformat(), smsConfig.getSmsType(), "呆萝卜",
								  smsParam, smsConfig.getRegisterTemplateCode(), phone);
			return ResultParser.parseJSON(result);
		} catch (ApiException e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}
	}
}
