package com.dailuobo.api.domain.soa.userCorrelation;

import java.io.IOException;
import java.util.Date;

import com.dailuobo.api.domain.soa.UserDeveiceKeyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * UserDeveiceKeyUtils<br/>
 * 描述:
 * @author huangwei
 * @since 2016-1-5<br/>
 */
public class UserDeveiceKeyUtils{
	
	/** 分隔符 */
	private final static String SEPARATOR = "|";
	
	/** 加密key */
	private final static String encryptKey = "mallcaiMALLCAI";//ResourceBundle.getBundle("key").getString("encryptKey");
	
	
	/** logger */
	private Logger logger = LoggerFactory.getLogger(UserDeveiceKeyUtils.class);
	

	/**
	 * 
	 */
	public UserDeveiceKeyUtils(){
		
	}

	/**
	 * 根据userDeveiceKey解析出对应的user信息
	 * @param userDeveiceKey
	 * @return
	 */
	public UserDeveiceKeyVO parse(String userDeveiceKey){
		try {
			String tempKey = DesEncryptUtils.decrypt(userDeveiceKey, encryptKey);
			String[] keys = tempKey.split("[" + SEPARATOR + "]");
			String userId = keys[0];
			Long loginTime = Long.parseLong(keys[1]);
			Long validateDuration = Long.parseLong(keys[2]);
			UserDeveiceKeyVO userDeveiceKeyVO = new UserDeveiceKeyVO();
			userDeveiceKeyVO.setUserId(Integer.parseInt(userId));
			userDeveiceKeyVO.setLoginTime(new Date(loginTime));
			userDeveiceKeyVO.setValidateDuration(validateDuration);
			return userDeveiceKeyVO;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	/**
	 * 根据userId，登录时间生成UserDeveiceKey
	 * @param userId 用户id
	 * @param loginTime 登录时间
	 * @param validateDuration 有效时间【以秒为单位，如果为-1，则表明不限时长】
	 * @return 生成的userDeveiceKey
	 */
	public String generator(Integer userId, Date loginTime, Long validateDuration){
		StringBuffer tempKey = new StringBuffer();
		tempKey.append(userId).append(SEPARATOR)
		       .append(loginTime.getTime()).append(SEPARATOR)
		       .append(validateDuration);
		try {
			return DesEncryptUtils.encrypt(tempKey.toString(), encryptKey);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}
