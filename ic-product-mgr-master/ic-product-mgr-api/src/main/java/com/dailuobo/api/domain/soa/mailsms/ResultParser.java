package com.dailuobo.api.domain.soa.mailsms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dailuobo.api.domain.soa.StandardResult;
import com.dailuobo.api.domain.util.LoggerUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * ResultParser<br/>
 * 描述: 阿里大鱼的返回值处理
 * @author huangwei
 * @since 2016-1-15<br/>
 */
public class ResultParser implements Serializable {
	private static final long serialVersionUID = 1;
	
	
	/** 日志 */
	private static Logger logger = LoggerFactory.getLogger(ResultParser.class);

	/**
	 * 将大鱼发送短信回来的信息进行解析成标准格式
	 * @param result 大鱼返回信息
	 * @return
	 */
	public static StandardResult parseJSON(String result){
		try{
			LoggerUtils.getLogger().info("发送短信待解析:" + result);
			JSONObject jsonObject = JSON.parseObject(result);
			String errorResponse = jsonObject.getString("error_response");
			if(!StringUtils.isEmpty(errorResponse)){
				JSONObject resultObject = JSON.parseObject(errorResponse);
				AliyuSMSError aliyuSMSError = AliyuSMSErrorGather.getInstance().getAliyuSMSError(resultObject.getString("msg"));
				StringBuilder sb = new StringBuilder();
				sb.append("出现问题:").append(aliyuSMSError.getErrorMsg())
				  .append("\r\n解决方案:").append(aliyuSMSError.getSolution());
				return new StandardResult(Boolean.FALSE, aliyuSMSError.getCode(), sb.toString());
			}else{
				String entity = jsonObject.getString("alibaba_aliqin_fc_sms_num_send_response");
				JSONObject resultObject = JSON.parseObject(entity);
				JSONObject realResult = JSON.parseObject(resultObject.getString("result"));
				if(realResult.getBoolean("success")){
					return StandardResult.getDefaultSuccess();
				}else{
					return StandardResult.getDefaultFailed(realResult.getString("err_code"));
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed("not found");
		}
	}
}
