package com.dailuobo.api.domain.soa.mailsms;

import com.mallcai.backend.common.utils.BaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * MailConfig<br/>
 * 描述:.
 *
 * @author huangwei
 * @since 2016-1-15<br/>
 */
public class MailConfig implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;
	
	/** 配置文件路径. */
	private final static String PROP_FILE_PATH = "mail.properties";
	
	/** 发送邮件的邮箱地址. */
	private final static String PROP_FROM_MAIL = "fromMail";
	
	/** 发送邮件的邮箱用户名. */
	private final static String PROP_FROM_MAIL_USER = "fromMailUser";
	
	/** 发送邮件的邮箱密码. */
	private final static String PROP_FROM_MAIL_PWD = "fromMailPwd";
	
	/** 监控时需要发送到的email */
	private final static String MONITOR_MAILS = "monitorMails";
	
	/** 监控邮件的分隔符 */
	private final static String MONITOR_MAILS_REGEX = "[|]";
	
	/**
	private final static String PROP_TO_MAILS = "toMails";
	
	
	/** 日志处理. */
	private static Logger logger = LoggerFactory.getLogger(EMailSender.class);
	
	/** 配置properties. */
	private Properties prop = new Properties();
	
	/** 默认配置config. */
	private static MailConfig defaultConfig = null;
	
	/** 当前是否默认的config. */
	private static Boolean isDefault = Boolean.FALSE;
	
	/** 发送的mail */
	private String fromMail;
	
	/** 监控会发到的mail */
	private List<String> monitorMails;
	
	/** 发送邮箱用户名 */
	private String fromMailUser;
	
	/** 发送邮箱的密码 */
	private String fromMailPwd;
	
	
	
	/**
	 * 获取默认的config.
	 *
	 * @return the default config
	 */
	public static MailConfig getDefaultConfig(){
		if(defaultConfig == null || !isDefault){
			synchronized (isDefault) {
				defaultConfig = new MailConfig(BaseConfig.getConfigPath(BaseConfig.ConfigType.MAIL));
			}
			isDefault = Boolean.TRUE;
		}
		return defaultConfig;
	}
	
	/**
	 * Gets the default config.
	 *
	 * @param is the is
	 * @return the default config
	 */
	public static MailConfig getDefaultConfig(InputStream is){
		if(defaultConfig == null || isDefault){
			synchronized (isDefault) {
				defaultConfig = new MailConfig(is);
			}
			isDefault = Boolean.FALSE;
		}
		return defaultConfig;
	}
	
	
	/**
	 * Instantiates a new mail config.
	 *
	 * @param is the is
	 */
	private MailConfig(InputStream is){
		try {
			prop.load(is);
			fromMail = prop.getProperty(PROP_FROM_MAIL);
			fromMailUser = prop.getProperty(PROP_FROM_MAIL_USER);
			fromMailPwd = prop.getProperty(PROP_FROM_MAIL_PWD);
			monitorMails = Arrays.asList(prop.getProperty(MONITOR_MAILS).split(MONITOR_MAILS_REGEX));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * Gets the prop.
	 *
	 * @return the prop
	 */
	public Properties getProp() {
		return prop;
	}

	/**
	 * @return the fromMail
	 */
	public String getFromMail() {
		return fromMail;
	}

	/**
	 * @return the monitorMails
	 */
	public List<String> getMonitorMails() {
		return monitorMails;
	}

	/**
	 * @return the fromMailUser
	 */
	public String getFromMailUser() {
		return fromMailUser;
	}

	/**
	 * @return the fromMailPwd
	 */
	public String getFromMailPwd() {
		return fromMailPwd;
	}
}
