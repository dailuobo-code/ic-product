package com.dailuobo.api.domain.soa.mailsms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dailuobo.api.domain.soa.StandardResult;
import com.dailuobo.api.domain.util.LoggerUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class WWResultParser implements Serializable {
	private static final long serialVersionUID = 1;
	/** 日志 */
	private static Logger logger = LoggerFactory.getLogger(WWResultParser.class);
	
	
	/**
	 * 将大鱼发送短信回来的信息进行解析成标准格式
	 * @param result 大鱼返回信息
	 * @return
	 */
	public static StandardResult parseJSON(String result){
		try{
			LoggerUtils.getLogger().info("发送短信待解析:" + result);
			JSONObject jsonObject = JSON.parseObject(result);
			String status = jsonObject.getString("State");
			if(!StringUtils.isEmpty(status)){
				if(status.equals("0")){
					return StandardResult.getDefaultSuccess();
				}else{
					return StandardResult.getDefaultFailed(jsonObject.getString("MsgState"));
				}
			}else{
				return StandardResult.getDefaultFailed(jsonObject.getString("MsgState"));
			}
			
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed("not found");
		}
	}
}
