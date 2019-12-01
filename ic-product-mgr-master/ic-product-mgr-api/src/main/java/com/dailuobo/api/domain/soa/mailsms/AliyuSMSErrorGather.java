package com.dailuobo.api.domain.soa.mailsms;

import com.mallcai.backend.common.utils.BaseConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * AliyuSMSErrorGather<br/>
 * 描述: 错误集
 * @author huangwei
 * @since 2016-1-15<br/>
 */
public class AliyuSMSErrorGather implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;
	
	/** logger */
	private Logger logger = LoggerFactory.getLogger(AliyuSMSErrorGather.class);
	
	/** 错误集 */
	private Map<String, AliyuSMSError> errorGather = new HashMap<String, AliyuSMSError>();
	
	/** 单实例 */
	private static AliyuSMSErrorGather instance = new AliyuSMSErrorGather();

	/** 配置文件 */
	Properties prop = new Properties();

	private AliyuSMSErrorGather(){
		try {
			prop.load(BaseConfig.getConfigPath("aliyuError"));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/** 单实例 */
	public static AliyuSMSErrorGather getInstance(){
		return instance;
	}
	
	/**
	 * 根据错误code得到对应的错误对象
	 * @param errCode
	 * @return
	 */
	public AliyuSMSError getAliyuSMSError(String errCode){
		if(errorGather.get(errCode) == null){
			String errorMsgs = prop.getProperty(errCode);
			if(!StringUtils.isBlank(errorMsgs)){
				return new AliyuSMSError(errCode, "未知错误", "请与大鱼联系");
			}
			String[] errorMsgArr = errorMsgs.split("[|]");
			AliyuSMSError aliyuSMSError = new AliyuSMSError(errorMsgArr[0], errorMsgArr[1], errorMsgArr[2]);
			errorGather.put(errCode, aliyuSMSError);
		}
		return errorGather.get(errCode);
	}
}
