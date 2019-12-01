package com.dailuobo.api.domain.soa.mailsms;

import com.dailuobo.api.domain.soa.StandardResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WWSender implements Serializable {
	private static final long serialVersionUID = 1;
	private WWConfig WWConfig;
	
	private Logger logger = LoggerFactory.getLogger(WWConfig.class);
	
	public WWSender(WWConfig wwConfig){
		this.WWConfig = wwConfig;
	}
	
	/**
	 * 发送验证码短信
	 * @param targetMobile	目标手机
	 * @param validateCode	验证码
	 * @return
	 */
	public StandardResult sendValidateCode(String targetMobile, String validateCode){
		try {
			String result = Send.SMS(
					"sname="+WWConfig.getSname()+"&spwd="+WWConfig.getSpwd()+"&sprdid="+WWConfig.getSprdid()+"&sdst="+targetMobile+"&smsg="+java.net.URLEncoder.encode("【呆萝卜】验证码"+validateCode+"，15分钟内有效，呆萝卜绝不会以任何形式索取此验证码，切勿告知他人。","utf-8")
					,WWConfig.getUrl());
			return WWResultParser.parseJSON(result);
		} catch (Exception e) {
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
		
		try {
			String result = Send.SMS(
					"sname="+WWConfig.getSname()+"&spwd="+WWConfig.getSpwd()+"&sprdid="+WWConfig.getSprdid()+"&sdst="+targetMobile+"&smsg="+java.net.URLEncoder.encode("【呆萝卜】库存预警:"+cityProductName+"[商品编号:" + cityProductCode + "]","utf-8")
					,WWConfig.getUrl());
			return WWResultParser.parseJSON(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}
	}
	
	// 发送待获取短信通知
	public StandardResult sendPickupNotice(String phoneNo, Integer noticeType) {
		String smsg = "";
		if(noticeType == 1){
			smsg = "【呆萝卜】您有今日订单未取货，若明天取货品质将得不到保证，门店营业时间是早7点到晚8点，请尽快取货哦！-回复T退订";
		}else if(noticeType == 2){
			smsg = "【呆萝卜】您有昨日订单未取货，门店营业时间是早7点到晚8点，如您今日不取货视为放弃，不予退款，请尽快取货哦！-回复T退订";
		}else if(noticeType == 3){
			smsg = "【呆萝卜】您在呆萝卜购买的预售商品明日可以取货啦，切勿忘记到下单的门店把美味带回家哦！";
		}
		try {
			
			String result = Send.SMS(
					"sname="+WWConfig.getSname()+"&spwd="+WWConfig.getSpwd()+"&sprdid="+WWConfig.getSprdid812()+"&sdst="+phoneNo+"&smsg="+java.net.URLEncoder.encode(smsg,"utf-8")
					,WWConfig.getUrl());
			StandardResult sr = WWResultParser.parseJSON(result);
			if(sr.getSuccess()) {
				logger.info("发送取货提醒短信成功,号码组:" + phoneNo);
			} else {
				logger.error("发送取货提醒短信失败,号码组:" + phoneNo + ",失败原因:" + sr.getErrorMsg());
			}
			return sr;
		} catch (Exception e) {
			logger.error("发送取货提醒短信异常,号码组:" + phoneNo + ",异常原因:" + e.getMessage(), e);
			return StandardResult.getDefaultFailedMsg(e.getMessage());
		}
	}
	
	//线下库存
	public StandardResult sendUndStockAlarmSMS(List<String> targetMobileList,
			String cityProductName) {
		StringBuffer sb = new StringBuffer();
		for(String mobile:targetMobileList){
			sb.append(mobile).append(",");
		}
		String targetMobile = sb.substring(0, sb.length() - 1);
		
		try {
			String result = Send.SMS(
					"sname="+WWConfig.getSname()+"&spwd="+WWConfig.getSpwd()+"&sprdid="+WWConfig.getSprdid()+"&sdst="+targetMobile+"&smsg="+java.net.URLEncoder.encode("【呆萝卜】库存预警:"+cityProductName,"utf-8")
					,WWConfig.getUrl());
			return WWResultParser.parseJSON(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}
	}
	
	
	/** 
	 发送提醒短信
	*/
	public StandardResult sendActivitySMS(String targetMobile, String activity,String date,String storeName){
		
		try {
			String result = Send.SMS(
					"sname="+WWConfig.getSname()+"&spwd="+WWConfig.getSpwd()+"&sprdid="+WWConfig.getSprdid()+"&sdst="+targetMobile+"&smsg="+java.net.URLEncoder.encode("【呆萝卜】您已成功参与"+activity+"活动，请于"+date+"，出示正确的姓名与手机号到"+storeName+"领取。。","utf-8")
					,WWConfig.getUrl());
			return WWResultParser.parseJSON(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}
	}
	
	//盘点异常
	public StandardResult sendInventorySMS(String targetMobile, String storeName, String checkUserName) {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = sf.format(date);
		/*String smsParam = JSON.toJSONString(new SmokeContentTemplate(storeName, checkUserName, time));*/
		try {						
			String result = Send.SMS(
					"sname="+WWConfig.getSname()+"&spwd="+WWConfig.getSpwd()+"&sprdid="+WWConfig.getSprdid()+"&sdst="+targetMobile+"&smsg="+java.net.URLEncoder.encode("【呆萝卜】("+storeName+"门店)，盘点异常，盘点人"+checkUserName+"，盘点时间"+time+"，请您及时到店核实确认，以免影响销售！","utf-8")
					,WWConfig.getUrl());
			return WWResultParser.parseJSON(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}
	}
	
	//注册
	public StandardResult sendRegisterSMS(String phone, String giveBalance) {
		try {
			String result = Send.SMS(
					"sname="+WWConfig.getSname()+"&spwd="+WWConfig.getSpwd()+"&sprdid="+WWConfig.getSprdid()+"&sdst="+phone+"&smsg="+java.net.URLEncoder.encode("【呆萝卜】恭喜您成功注册，阿呆已快马加鞭为您送上"+giveBalance+"元的现金在您的呆萝卜余额账户，随买随用哦！","utf-8")
					,WWConfig.getUrl());
			return WWResultParser.parseJSON(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}
	}

	//团长审核
	public StandardResult sendGroupUserAuditSMS(String phone, String reason,Integer status,String msg) {
		
		
		
		try {
			if(status.intValue()!=2){
				//审核不通过
				msg = msg.replace("{reason}", reason);
			}
			String result = Send.SMS(
					"sname="+WWConfig.getSname()+"&spwd="+WWConfig.getSpwd()+"&sprdid="+WWConfig.getSprdid()+"&sdst="+phone+"&smsg="+java.net.URLEncoder.encode(msg,"utf-8")
					,WWConfig.getUrl());
			return WWResultParser.parseJSON(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}
	}


	public StandardResult sendCompensateMessage(String targetMobile, String msg){
		try {
			String result = Send.SMS(
					"sname="+WWConfig.getSname()+"&spwd="+WWConfig.getSpwd()+"&sprdid="+WWConfig.getSprdid()+"&sdst="+targetMobile+"&smsg="+java.net.URLEncoder.encode(msg,"utf-8")
					,WWConfig.getUrl());
			return WWResultParser.parseJSON(result);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}
	}
	
}
