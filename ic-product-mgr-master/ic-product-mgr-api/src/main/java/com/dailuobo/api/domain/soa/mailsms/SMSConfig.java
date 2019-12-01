package com.dailuobo.api.domain.soa.mailsms;

import com.mallcai.backend.common.utils.BaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;


/**
 * SMSConfig<br/>
 * 描述:短信配置类
 * 
 * @author huangwei
 * @since 2016-1-15<br/>
 */
public class SMSConfig implements Serializable {
	private static final long serialVersionUID = 1;

	/** 配置文件路径. */
	private final static String PROP_FILE_PATH = "sms.properties";

	/** prop文件中appkey的标识 */
	private final static String PROP_APPKEY = "appkey";

	/** prop文件中appsecret的标识 */
	private final static String PROP_APPSECRET = "appsecret";

	/** prop文件中url的标识 */
	private final static String PROP_URL = "url";
	
	/** prop文件中的sms_type标识*/
	private final static String PROP_SMS_TYPE = "sms_type";

	/** prop文件中sms_free_sign_name的标识 */
	private final static String PROP_SMS_FREE_SIGN_NAME = "sms_free_sign_name";

	/** prop文件中sms_validate_template_code的标识 */
	private final static String PROP_SMS_VALIDATE_TEMPLATE_CODE = "sms_validate_template_code";
	
	/** prop文件中的sms_monitor_template_code的标识*/
	private final static String PROP_SMS_MONITOR_TEMPLATE_CODE = "sms_monitor_template_code";
	
	/** prop文件中的stock_alarm_template_code的标识 */
	private final static String PROP_STOCK_ALARM_TEMPLATE_CODE = "stock_alarm_template_code";

	/** prop文件中smoke_template_code的标识 */
	private final static String PROP_SMS_SMOKE_TEMPLATE_CODE = "smoke_template_code";
	
	private final static String PROP_ACTIVITY_TEMPLATE_CODE    = "activity_template_code";
	
	private final static String PROP_SMS_PICKUP_TEMPLATE_CODE1 = "sms_pickup_template_code1";
	
	private final static String PROP_SMS_PICKUP_TEMPLATE_CODE2 = "sms_pickup_template_code2";

	private final static String PROP_SMS_PICKUP_TEMPLATE_CODE3 = "sms_pickup_template_code3";
	
	private final static String PROP_REGISTER_TEMPLATE_CODE    = "register_template_code";
	
	/** appkey值 */
	private String appkey;
	
	/** appkey值 */
	private String appsecret;
	
	/** appkey值 */
	private String url; 
	
	/** 短信签名的值 */
	private String smsFreeSignName;
	
	/** 短信模板的中关于验证码的code */
	private String smsValidateTemplateCode;

	/** 短信模板中的对应监控的template Code*/
	private String smsMonitorTemplateCode;

	/** 短信模板中的对应库存报警的template_code*/
	private String stockAlarmTemplateCode;

	/**短信模板中对应香烟库存异常的 template_code*/
	private String smokeTemplateCode;

	private String smsPickupTemplateCode1;
	
	private String smsPickupTemplateCode2;
	
	private String smsPickupTemplateCode3;
	
	private String activityTemplateCode;
	
	private String registerTemplateCode;
	
	/** smstype*/
	private String smsType;
	
	/** 返回数据格式 */
	private final static String resultFormat = "JSON";
	
	/** 日志处理. */
	private static Logger logger = LoggerFactory.getLogger(EMailSender.class);
	
	/** 默认配置config. */
	private static SMSConfig defaultConfig = null;
	
	/** 当前是否默认的config. */
	private static Boolean isDefault = Boolean.FALSE;
	
	/**
	 * 获取默认的config
	 * @return
	 */
	public static SMSConfig getDefaultConfig(){
		if(defaultConfig == null || !isDefault){
			synchronized (isDefault) {
				defaultConfig = new SMSConfig(BaseConfig.getConfigPath(BaseConfig.ConfigType.SMS));
			}
			isDefault = Boolean.TRUE;
		}
		return defaultConfig;
	}
	
	
	public static SMSConfig getDefaultConfig(InputStream is){
		if(defaultConfig == null || isDefault){
			synchronized (isDefault) {
				defaultConfig = new SMSConfig(is);
			}
			isDefault = Boolean.FALSE;
		}
		return defaultConfig;
	}
	
	
	/**
	 * Instantiates a new sms config.
	 *
	 * @param is the is
	 */
	private SMSConfig(InputStream is){
		try {
			Properties prop = new Properties();
			prop.load(is);
			appkey = prop.getProperty(PROP_APPKEY);
			appsecret = prop.getProperty(PROP_APPSECRET);
			smsFreeSignName = prop.getProperty(PROP_SMS_FREE_SIGN_NAME);
			url = prop.getProperty(PROP_URL);
			smsValidateTemplateCode = prop.getProperty(PROP_SMS_VALIDATE_TEMPLATE_CODE);
			smsType = prop.getProperty(PROP_SMS_TYPE);
			smsMonitorTemplateCode = prop.getProperty(PROP_SMS_MONITOR_TEMPLATE_CODE);
			registerTemplateCode   = prop.getProperty(PROP_REGISTER_TEMPLATE_CODE);
			stockAlarmTemplateCode = prop.getProperty(PROP_STOCK_ALARM_TEMPLATE_CODE);
			activityTemplateCode   = prop.getProperty(PROP_ACTIVITY_TEMPLATE_CODE);
			smsPickupTemplateCode1 = prop.getProperty(PROP_SMS_PICKUP_TEMPLATE_CODE1);
			smsPickupTemplateCode2 = prop.getProperty(PROP_SMS_PICKUP_TEMPLATE_CODE2);
			smsPickupTemplateCode3 = prop.getProperty(PROP_SMS_PICKUP_TEMPLATE_CODE3);
			smokeTemplateCode = prop.getProperty(PROP_SMS_SMOKE_TEMPLATE_CODE);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}


	/**
	 * @return the smsType
	 */
	public String getSmsType() {
		return smsType;
	}


	/**
	 * @return the appkey
	 */
	public String getAppkey() {
		return appkey;
	}


	/**
	 * @return the appsecret
	 */
	public String getAppsecret() {
		return appsecret;
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @return the smsFreeSignName
	 */
	public String getSmsFreeSignName() {
		return smsFreeSignName;
	}


	/**
	 * @return the smsValidateTemplateCode
	 */
	public String getSmsValidateTemplateCode() {
		return smsValidateTemplateCode;
	}

	/**
	 * @return the smokeTemplateCode
	 */
	public String getPropSmsSmokeTemplateCode() {
		return smokeTemplateCode;
	}
	/**
	 * @return the resultformat
	 */
	public String getResultformat() {
		return resultFormat;
	}


	/**
	 * @return the smsMonitorTemplateCode
	 */
	public String getSmsMonitorTemplateCode() {
		return smsMonitorTemplateCode;
	}


	/**
	 * @return the stockAlarmTemplateCode
	 */
	public String getStockAlarmTemplateCode() {
		return stockAlarmTemplateCode;
	}


	public String getActivityTemplateCode() {
		return activityTemplateCode;
	}


	public void setActivityTemplateCode(String activityTemplateCode) {
		this.activityTemplateCode = activityTemplateCode;
	}


	/**
	 * 获取  smsPickupTemplateCode1
	 * @return smsPickupTemplateCode1
	 */
	public String getSmsPickupTemplateCode1() {
		return smsPickupTemplateCode1;
	}


	/**
	 * 设置 smsPickupTemplateCode1
	 * @param smsPickupTemplateCode1
	 */
	public void setSmsPickupTemplateCode1(String smsPickupTemplateCode1) {
		this.smsPickupTemplateCode1 = smsPickupTemplateCode1;
	}


	/**
	 * 获取  smsPickupTemplateCode2
	 * @return smsPickupTemplateCode2
	 */
	public String getSmsPickupTemplateCode2() {
		return smsPickupTemplateCode2;
	}


	/**
	 * 设置 smsPickupTemplateCode2
	 * @param smsPickupTemplateCode2
	 */
	public void setSmsPickupTemplateCode2(String smsPickupTemplateCode2) {
		this.smsPickupTemplateCode2 = smsPickupTemplateCode2;
	}


	public String getSmsPickupTemplateCode3() {
		return smsPickupTemplateCode3;
	}


	public void setSmsPickupTemplateCode3(String smsPickupTemplateCode3) {
		this.smsPickupTemplateCode3 = smsPickupTemplateCode3;
	}


	public String getRegisterTemplateCode() {
		return registerTemplateCode;
	}


	public void setRegisterTemplateCode(String registerTemplateCode) {
		this.registerTemplateCode = registerTemplateCode;
	}
	
	
}
