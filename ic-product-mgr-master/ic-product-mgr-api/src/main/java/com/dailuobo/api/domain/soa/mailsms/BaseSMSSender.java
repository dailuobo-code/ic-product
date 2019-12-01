package com.dailuobo.api.domain.soa.mailsms;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import java.io.Serializable;

/**
 * BaseSMSSender<br/>
 * 描述:
 * @author huangwei
 * @since 2016-1-15<br/>
 */
class BaseSMSSender implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;
	
	/**
	 * 最底层的发送短信的方法
	 * @param smsConfig		配置
	 * @param targetMobile	目标手机号
	 * @param smsParam		短信参数配置
	 * @return
	 * @throws ApiException 
	 */
	protected static String sendSMS(String url, String appKey, String appSecret, String resultFormat,
						   String smsType, String smsFreeSignName, String smsParam,
						   String templateCode, String targetMobile) throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient(url, appKey, appSecret, resultFormat);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType(smsType);
		req.setSmsFreeSignName(smsFreeSignName);
		req.setSmsParamString(smsParam);
		req.setRecNum(targetMobile);
		req.setSmsTemplateCode(templateCode);
		AlibabaAliqinFcSmsNumSendResponse rsp;
		rsp = client.execute(req);
		return rsp.getBody();
	}
}
