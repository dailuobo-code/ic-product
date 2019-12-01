package com.dailuobo.api.domain.soa.mailsms;

import com.mallcai.backend.common.utils.BaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;


public class WWConfig implements Serializable {
	private static final long serialVersionUID = 1;
	/** 配置文件路径. */
	private final static String PROP_FILE_PATH = "ww.properties";
	
	/** prop文件中sname的标识 提交账户 Y */
	private final static String PROP_SNAME = "sname";
	
	/** prop文件中spwd的标识 提交账户的密码  Y*/
	private final static String PROP_SPWD = "spwd";
	
	/** prop文件中scorpid的标识 企业代码 N*/
	private final static String PROP_SCORPID = "scorpid";
	
	/** prop文件中sprdid的标识 产品编号 Y*/
	private final static String PROP_SPRDID = "sprdid";
	
	private final static String PROP_SPRDID812 = "sprdid812";
	private final static String PROP_SPRDID818 = "sprdid818";
	
	/** prop文件中sbegindate的标识 */
	private final static String PROP_SBEGIN_DATE = "sbegindate";
	
	/** prop文件中key的标识 N*/
	private final static String PROP_KEY = "key";
	
	/** prop文件中url的标识 Y*/
	private final static String PROP_URL = "url";
	
	
	private String sname;
	private String spwd;
	private String scorpid;
	private String sprdid;
	private String sprdid812;
	private String sprdid818;
	private String sbegindate;
	private String key;
	private String url;
	
	/** 日志处理. */
	private static Logger logger = LoggerFactory.getLogger(EMailSender.class);
	
	/** 默认配置config. */
	private static WWConfig defaultConfig = null;
	
	/** 当前是否默认的config. */
	private static Boolean isDefault = Boolean.FALSE;
	
	/**
	 * 获取默认的config
	 * @return
	 */
	public static WWConfig getDefaultConfig(){
		if(defaultConfig == null || !isDefault){
			synchronized (isDefault) {
				defaultConfig = new WWConfig(BaseConfig.getConfigPath(BaseConfig.ConfigType.WW));
			}
			isDefault = Boolean.TRUE;
		}
		return defaultConfig;
	}
	
	
	public static WWConfig getDefaultConfig(InputStream is){
		if(defaultConfig == null || isDefault){
			synchronized (isDefault) {
				defaultConfig = new WWConfig(is);
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
	private WWConfig(InputStream is){
		try {
			Properties prop = new Properties();
			prop.load(is);
			sname = prop.getProperty(PROP_SNAME);
			spwd = prop.getProperty(PROP_SPWD);
			sprdid = prop.getProperty(PROP_SPRDID);
			sprdid812 = prop.getProperty(PROP_SPRDID812);
			sprdid818 = prop.getProperty(PROP_SPRDID818);
			url = prop.getProperty(PROP_URL);
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}


	public String getSname() {
		return sname;
	}


	public String getSpwd() {
		return spwd;
	}


	public String getScorpid() {
		return scorpid;
	}


	public String getSprdid() {
		return sprdid;
	}


	public String getSprdid812() {
		return sprdid812;
	}


	public void setSprdid812(String sprdid812) {
		this.sprdid812 = sprdid812;
	}


	public String getSprdid818() {
		return sprdid818;
	}


	public void setSprdid818(String sprdid818) {
		this.sprdid818 = sprdid818;
	}


	public String getSbegindate() {
		return sbegindate;
	}


	public String getKey() {
		return key;
	}


	public String getUrl() {
		return url;
	}
	
	
	
	
	
	
}
