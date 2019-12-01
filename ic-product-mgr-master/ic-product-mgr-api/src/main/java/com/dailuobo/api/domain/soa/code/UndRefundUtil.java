package com.dailuobo.api.domain.soa.code;

import com.dailuobo.api.domain.soa.StandardHttpResp;
import com.dailuobo.api.domain.soa.StandardResult;
import com.dailuobo.api.domain.util.Arith;
import com.dailuobo.api.domain.util.DatetimeUtil;
import com.dailuobo.api.domain.util.LoggerUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.*;
import java.util.Map.Entry;


/**
 * 微信退款操作
 */
@Component("undRefundUtil")
public class UndRefundUtil implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;
	
	private static final String INTER_FLAG_FAIL = "FAIL";
	private static final String INTER_FLAG_SUCCESS = "SUCCESS";
	private static final String ALGORITHM_MD5 = "MD5";
	private static final String PAY_TYPE_WX = "WX";
	private static final String EQUALS_SIGN = "=";
	private static final String CONNECTOR = "&";
	private static final String PAY_TYPE_ALI = "ALI";
	private static final String CHARSET_UTF8 = "UTF-8";
	private static final String BLANK_STR = "";
	private static final String WX_PAY_NONCE_SRC = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	private String wxpayAppid;
	private String wxpayMchid;
	private String wxpayKey;
	private String wxRefundUrl;
	private String wxRefundCertP12;
	
	private String alipayPartner;
	private String alipayRefundNotifyUrl;
	private String alipayRefundKey;
	private String alipayRefundUrl;
	
	
	public StandardResult refund(UndRefundOrder refundOrder, String type, Boolean isCheckSign) throws Exception {
		if("1".equals(type)) {
			return refundzfb(refundOrder);
		} else {
			return refundwx(refundOrder, isCheckSign);
		}
	}
	
	public StandardResult query(String outRefundNo, Boolean isCheckSign) throws Exception {
		String requestXml = generateQueryMsg(outRefundNo);
		StandardHttpResp standardHttpResp = sendRefundRequest(requestXml, false);
		String returnXml = standardHttpResp.getResponseMsg();
		if(!standardHttpResp.getIsSuccess()) {
			// 调用失败
			LoggerUtils.getLogger().info("[微信支付查询退款接口]接口调用失败,响应code={},响应报文={}", standardHttpResp.getResponseCode(), standardHttpResp.getResponseMsg());
			StandardResult standardResult = StandardResult.getDefaultFailed(standardHttpResp.getResponseCode());
			standardResult.setErrorMsg(standardHttpResp.getResponseMsg());
			return standardResult;
		}
		
		Document doucment = DocumentUtil.parseText(returnXml);
		String returnCode = doucment.selectSingleNode("//xml/return_code").getStringValue();
		if(INTER_FLAG_FAIL.equals(returnCode)) {
			// 接口返回失败
			String returnMsg = doucment.selectSingleNode("//xml/return_msg").getStringValue();
			StandardResult standardResult = StandardResult.getDefaultFailed(returnCode);
			standardResult.setErrorMsg(returnMsg);
			standardResult.setErrorCode(returnCode);
			return standardResult;
		}
		
		boolean checkSign = Boolean.TRUE;
		if(isCheckSign) {// 签名校验
			checkSign = checkSign(doucment);
		}
		
		if(checkSign) {
			String resultCode = getNodeValue(doucment.selectSingleNode("//xml/result_code"));
			if(INTER_FLAG_SUCCESS.equals(resultCode)) {// 退款查询成功
				//String outRefundNo = getNodeValue(doucment.selectSingleNode("//xml/out_refund_no"));
				/** SUCCESS—退款成功  FAIL—退款失败  PROCESSING—退款处理中
				 *  NOTSURE—未确定，需要商户原退款单号重新发起
				 *  CHANGE—转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干预，通过线下或者财付通转账的方式进行退款
				 */
				String refundStatus = getNodeValue(doucment.selectSingleNode("//xml/refund_status_0"));
				StandardResult standardResult = StandardResult.getDefaultSuccess();
				standardResult.setErrorCode(refundStatus);
				return standardResult;
			} else {// 退款查询失败
				String errCode = getNodeValue(doucment.selectSingleNode("//xml/err_code"));
				String errCodeDesc = getNodeValue(doucment.selectSingleNode("//xml/err_code_des"));
				StandardResult standardResult = StandardResult.getDefaultFailed(errCode);
				standardResult.setErrorMsg(errCodeDesc);
				return standardResult;
			}
		} else {
			// 验签失败 疑似非法请求
			StandardResult standardResult = StandardResult.getDefaultFailed("Err-0001");
			standardResult.setErrorMsg("验签失败");
			return standardResult;
		}
	}
	
	private StandardResult refundzfb(UndRefundOrder refundOrder) throws Exception {
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "refund_fastpay_by_platform_nopwd");
		sParaTemp.put("partner", alipayPartner);
		sParaTemp.put("_input_charset", CHARSET_UTF8);
		sParaTemp.put("notify_url", alipayRefundNotifyUrl);
		sParaTemp.put("batch_no", refundOrder.getOutRefundNo());
		sParaTemp.put("refund_date", DatetimeUtil.getNowStr());
		sParaTemp.put("batch_num", "1");
		String detailData = refundOrder.getTransactionId() + "^" + Arith.mul(refundOrder.getRefundFee(), 0.01f) + "^用户取消订单,在线退款";
		sParaTemp.put("detail_data", detailData);
		Map<String, String> sPara = buildRequestPara(sParaTemp);
		StandardHttpResp standardHttpResp = sendRefundRequestAlipay(sPara);
		String returnXml = standardHttpResp.getResponseMsg();
		if(!standardHttpResp.getIsSuccess()) {
			// 调用失败
			LoggerUtils.getLogger().info("[支付宝退款接口]接口调用失败,响应code={},响应报文={}", standardHttpResp.getResponseCode(), standardHttpResp.getResponseMsg());
			StandardResult standardResult = StandardResult.getDefaultFailed(standardHttpResp.getResponseCode());
			standardResult.setErrorMsg(standardHttpResp.getResponseMsg());
			return standardResult;
		}
		
		Document doucment = DocumentUtil.parseText(returnXml);
		String isSuccess = doucment.selectSingleNode("//alipay/is_success").getStringValue();
		if("F".equals(isSuccess)) {
			String returnMsg = doucment.selectSingleNode("//alipay/error").getStringValue();
			StandardResult standardResult = StandardResult.getDefaultFailed(isSuccess);
			standardResult.setErrorMsg(returnMsg);
			standardResult.setErrorCode(isSuccess);
			return standardResult;
		}
		
		StandardResult standardResult = StandardResult.getDefaultSuccess();
		standardResult.setErrorCode(isSuccess);
		return standardResult;
	}
	
	private StandardResult refundwx(UndRefundOrder refundOrder, Boolean isCheckSign) throws Exception {
		String requestXml = generateReqMsg(refundOrder);
		StandardHttpResp standardHttpResp = sendRefundRequest(requestXml, true);
		String returnXml = standardHttpResp.getResponseMsg();
		if(!standardHttpResp.getIsSuccess()) {
			// 调用失败
			LoggerUtils.getLogger().info("[微信支付退款接口]接口调用失败,响应code={},响应报文={}", standardHttpResp.getResponseCode(), standardHttpResp.getResponseMsg());
			StandardResult standardResult = StandardResult.getDefaultFailed(standardHttpResp.getResponseCode());
			standardResult.setErrorMsg(standardHttpResp.getResponseMsg());
			return standardResult;
		}
		
		Document doucment = DocumentUtil.parseText(returnXml);
		String returnCode = doucment.selectSingleNode("//xml/return_code").getStringValue();
		if(INTER_FLAG_FAIL.equals(returnCode)) {
			// 接口返回失败
			String returnMsg = doucment.selectSingleNode("//xml/return_msg").getStringValue();
			StandardResult standardResult = StandardResult.getDefaultFailed(returnCode);
			standardResult.setErrorMsg(returnMsg);
			standardResult.setErrorCode(returnCode);
			return standardResult;
		}
		
		boolean checkSign = Boolean.TRUE;
		if(isCheckSign) {// 签名校验
			checkSign = checkSign(doucment);
		}
		
		if(checkSign) {
			String resultCode = getNodeValue(doucment.selectSingleNode("//xml/result_code"));
			if(INTER_FLAG_SUCCESS.equals(resultCode)) {//退款申请接收成功
				//String outRefundNo = getNodeValue(doucment.selectSingleNode("//xml/out_refund_no"));
				StandardResult standardResult = StandardResult.getDefaultSuccess();
				standardResult.setErrorCode(resultCode);
				return standardResult;
			} else {// 提交业务失败
				String errCode = getNodeValue(doucment.selectSingleNode("//xml/err_code"));
				String errCodeDesc = getNodeValue(doucment.selectSingleNode("//xml/err_code_des"));
				StandardResult standardResult = StandardResult.getDefaultFailed(errCode);
				standardResult.setErrorMsg(errCodeDesc);
				return standardResult;
			}
		} else {
			// 验签失败 疑似非法请求
			StandardResult standardResult = StandardResult.getDefaultFailed("Err-0001");
			standardResult.setErrorMsg("验签失败");
			return standardResult;
		}
	}
	
	private String generateReqMsg(UndRefundOrder refundOrder) {
		Map<String,String> params = new HashMap<>();
		params.put("appid", wxpayAppid);
		params.put("mch_id", wxpayMchid);
		params.put("nonce_str", generateNonceStr(24, 0));
		params.put("op_user_id", wxpayMchid);
		params.put("out_refund_no", refundOrder.getOutRefundNo());
		params.put("out_trade_no", refundOrder.getOutTradeNo());
		params.put("refund_fee", String.valueOf(refundOrder.getRefundFee()));
		params.put("total_fee", String.valueOf(refundOrder.getTotalFee()));
		params.put("transaction_id", refundOrder.getTransactionId());
		params.put("sign", generateSign(params, wxpayKey, ALGORITHM_MD5));
		return arrayToXml(params);
	}
	
	private String generateQueryMsg(String outRefundNo) {
		Map<String,String> params = new HashMap<>();
		params.put("appid", wxpayAppid);
		params.put("mch_id", wxpayMchid);
		params.put("nonce_str", generateNonceStr(24, 0));
		params.put("out_refund_no", outRefundNo);
		params.put("sign", generateSign(params, wxpayKey, ALGORITHM_MD5));
		return arrayToXml(params);
	}
	
	private StandardHttpResp sendRefundRequest(String reqXml, boolean isCert) {
		try {
			SSLConnectionSocketFactory sslsf = null;
			if(isCert) {
				KeyStore keyStore = KeyStore.getInstance("PKCS12");
				Resource resource = new UrlResource(wxRefundCertP12);
				keyStore.load(resource.getInputStream(), wxpayMchid.toCharArray());
				SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, wxpayMchid.toCharArray()).build();
				sslsf = new SSLConnectionSocketFactory(sslcontext,new String[] {"TLSv1"},null,new DefaultHostnameVerifier());
			} else {
				SSLContext sslContext = SSLContexts.createSystemDefault();
				sslsf = new SSLConnectionSocketFactory(sslContext,NoopHostnameVerifier.INSTANCE);
			}
			HttpPost httpPost = new HttpPost(wxRefundUrl);
			StringEntity entity = new StringEntity(reqXml, ContentType.create(ContentType.TEXT_XML.getMimeType(), CHARSET_UTF8));
			httpPost.setEntity(entity);
			
			try(CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
					CloseableHttpResponse response = httpclient.execute(httpPost);) {
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() >= 300) {
					return StandardHttpResp.getDefaultFail(statusLine.getStatusCode(), statusLine.getReasonPhrase());
				}
				
				HttpEntity respEntity = response.getEntity();
				if(respEntity == null) {
					return StandardHttpResp.getDefaultFail(statusLine.getStatusCode(), "Response contains no content");
				}
				
				ContentType contentType = ContentType.getOrDefault(respEntity);
				Charset respCharset = contentType.getCharset();
				if(respCharset == null) respCharset = Charset.forName(CHARSET_UTF8);
				InputStreamReader isr = new InputStreamReader(respEntity.getContent(), respCharset);
				BufferedReader br = new BufferedReader(isr);
				String respMsg = "";
				String tmp = "";
				while((tmp = br.readLine()) != null) {
					respMsg += tmp;
				}
				
				LoggerUtils.getLogger().info("响应报文:{}", respMsg);
				return StandardHttpResp.getDefaultSuccess(statusLine.getStatusCode(), respMsg);
			} catch (Exception e) {
				return StandardHttpResp.getDefaultFail(null, e.getMessage());
			}
		} catch(Exception e) {
			return StandardHttpResp.getDefaultFail(null, e.getMessage());
		}
	}
	
	private StandardHttpResp sendRefundRequestAlipay(Map<String, String> sParaTemp) {
		try {
			SSLContext sslContext = SSLContexts.createSystemDefault();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,NoopHostnameVerifier.INSTANCE);
			HttpPost httpPost = new HttpPost(alipayRefundUrl);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(generatNameValuePair(sParaTemp), CHARSET_UTF8);
			httpPost.setEntity(entity);
			try(CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
					CloseableHttpResponse response = httpclient.execute(httpPost);) {
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() >= 300) {
					return StandardHttpResp.getDefaultFail(statusLine.getStatusCode(), statusLine.getReasonPhrase());
				}
				
				HttpEntity respEntity = response.getEntity();
				if(respEntity == null) {
					return StandardHttpResp.getDefaultFail(statusLine.getStatusCode(), "Response contains no content");
				}
				
				ContentType contentType = ContentType.getOrDefault(respEntity);
				Charset respCharset = contentType.getCharset();
				if(respCharset == null) respCharset = Charset.forName(CHARSET_UTF8);
				InputStreamReader isr = new InputStreamReader(respEntity.getContent(), respCharset);
				BufferedReader br = new BufferedReader(isr);
				String respMsg = "";
				String tmp = "";
				while((tmp = br.readLine()) != null) {
					respMsg += tmp;
				}
				
				LoggerUtils.getLogger().info("响应报文:{}", respMsg);
				return StandardHttpResp.getDefaultSuccess(statusLine.getStatusCode(), respMsg);
			} catch (Exception e) {
				return StandardHttpResp.getDefaultFail(null, e.getMessage());
			}
		} catch(Exception e) {
			return StandardHttpResp.getDefaultFail(null, e.getMessage());
		}
	}
	
	private List<NameValuePair> generatNameValuePair(Map<String, String> properties) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
        	params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        	LoggerUtils.getLogger().info(Thread.currentThread().getName() + ">>>支付宝退款请求参数key={},value={}", entry.getKey(), entry.getValue());
        }

        return params;
    }
	
	/**
	 * 校验签名是否正常
	 * 调用该方法前提条件是报文有sign节点 否则没有实际意义
	 * @param doucment
	 * @return
	 */
	private boolean checkSign(Document doucment) {
		@SuppressWarnings("unchecked")
		Iterator<Element> it = doucment.getRootElement().elementIterator();
		Map<String, String> nodes = new HashMap<String, String>();
		
		while(it.hasNext()) {
			Element ele = it.next();
			nodes.put(ele.getName(), ele.getText());
		}
		
		String calcSign = generateSign(nodes, wxpayKey, ALGORITHM_MD5);
		String sign = nodes.get("sign");
		if(calcSign != null && calcSign.equals(sign)) {
			return Boolean.TRUE;
		}
		
		return Boolean.FALSE;
	}
	
	private String generateSign(Map<String, String> paraMap,String key,String digestType) {
		Map<String, String> sPara = paraFilter(paraMap, PAY_TYPE_WX);// 过滤掉空的字段
		StringBuffer prestr = createLinkString(sPara); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
		//String stringSignTemp = prestr + "&key=" + key;
		// 如果密钥key为空,则不添加在参数中了（主要考虑目前还不太明确这个key是否有）
		String stringSignTemp = (StringUtils.isBlank(key)) ? prestr.toString() : prestr.append("&key=").append(key).toString();
		String sign = null;
		if("MD5".equals(digestType)){
			sign = DigestUtils.md5Hex(stringSignTemp).toUpperCase();
		}else if("SHA-256".equals(digestType)){
			sign = DigestUtils.sha256Hex(stringSignTemp).toUpperCase();
		}
		return sign;
	}
	
	/** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    private Map<String, String> paraFilter(Map<String, String> sArray, String type) {
        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || "".equals(value) || "sign".equalsIgnoreCase(key) || ("sign_type".equalsIgnoreCase(key) && PAY_TYPE_ALI.equals(type))) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
    
    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    private StringBuffer createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuffer prestr = new StringBuffer("");
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr.append(key).append(EQUALS_SIGN).append(value);
			} else {
				prestr.append(key).append(EQUALS_SIGN).append(value).append(CONNECTOR);
			}
		}
		return prestr;
	}
    
    private String getNodeValue(Node node) {
		return node == null ? BLANK_STR : node.getStringValue();
	}
    
    private String generateNonceStr(int strLen, int flag) {
		int len = WX_PAY_NONCE_SRC.length() - 1;
		StringBuffer res = new StringBuffer("");
		for (int i = 0; i < strLen; i++) {
			java.util.Random rd = new java.util.Random();
			res.append(WX_PAY_NONCE_SRC.charAt(rd.nextInt(len)));
		}
		return res.toString();
	}
    
    private String arrayToXml(Map<String, String> arr) {
		StringBuffer xml = new StringBuffer("<xml>");
		Iterator<Entry<String, String>> iter = arr.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			String key = entry.getKey();
			String val = entry.getValue();
			xml.append("<").append(key).append(">").append(val).append("</").append(key).append(">");
		}
		xml.append("</xml>");
		return xml.toString();
	}
    
    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    private String createLinkStringzfb(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
    
    /**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	public String buildRequestMysign(Map<String, String> sPara) {
    	String prestr = createLinkStringzfb(sPara); //把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String mysign = "";
        if(ALGORITHM_MD5.equals("MD5") ) {
        	mysign = MD5.sign(prestr, alipayRefundKey, CHARSET_UTF8);
        }
        return mysign;
    }
	
    /**
     * 生成要请求给支付宝的参数数组
     * @param sParaTemp 请求前的参数数组
     * @return 要请求的参数数组
     */
    private Map<String, String> buildRequestPara(Map<String, String> sParaTemp) {
        //除去数组中的空值和签名参数
        Map<String, String> sPara = paraFilter(sParaTemp, PAY_TYPE_ALI);
        //生成签名结果
        String mysign = buildRequestMysign(sPara);

        //签名结果与签名方式加入请求提交参数组中
        sPara.put("sign", mysign);
        sPara.put("sign_type", ALGORITHM_MD5);

        return sPara;
    }

	/**
	 * 获取  wxpayAppid
	 * @return wxpayAppid
	 */
	public String getWxpayAppid() {
		return wxpayAppid;
	}

	/**
	 * 设置 wxpayAppid
	 * @param wxpayAppid
	 */
	public void setWxpayAppid(String wxpayAppid) {
		this.wxpayAppid = wxpayAppid;
	}

	/**
	 * 获取  wxpayMchid
	 * @return wxpayMchid
	 */
	public String getWxpayMchid() {
		return wxpayMchid;
	}

	/**
	 * 设置 wxpayMchid
	 * @param wxpayMchid
	 */
	public void setWxpayMchid(String wxpayMchid) {
		this.wxpayMchid = wxpayMchid;
	}

	/**
	 * 获取  wxpayKey
	 * @return wxpayKey
	 */
	public String getWxpayKey() {
		return wxpayKey;
	}

	/**
	 * 设置 wxpayKey
	 * @param wxpayKey
	 */
	public void setWxpayKey(String wxpayKey) {
		this.wxpayKey = wxpayKey;
	}

	/**
	 * 获取  wxRefundUrl
	 * @return wxRefundUrl
	 */
	public String getWxRefundUrl() {
		return wxRefundUrl;
	}

	/**
	 * 设置 wxRefundUrl
	 * @param wxRefundUrl
	 */
	public void setWxRefundUrl(String wxRefundUrl) {
		this.wxRefundUrl = wxRefundUrl;
	}

	/**
	 * 获取  wxRefundCertP12
	 * @return wxRefundCertP12
	 */
	public String getWxRefundCertP12() {
		return wxRefundCertP12;
	}

	/**
	 * 设置 wxRefundCertP12
	 * @param wxRefundCertP12
	 */
	public void setWxRefundCertP12(String wxRefundCertP12) {
		this.wxRefundCertP12 = wxRefundCertP12;
	}

	/**
	 * 获取  alipayPartner
	 * @return alipayPartner
	 */
	public String getAlipayPartner() {
		return alipayPartner;
	}

	/**
	 * 设置 alipayPartner
	 * @param alipayPartner
	 */
	public void setAlipayPartner(String alipayPartner) {
		this.alipayPartner = alipayPartner;
	}

	/**
	 * 获取  alipayRefundNotifyUrl
	 * @return alipayRefundNotifyUrl
	 */
	public String getAlipayRefundNotifyUrl() {
		return alipayRefundNotifyUrl;
	}

	/**
	 * 设置 alipayRefundNotifyUrl
	 * @param alipayRefundNotifyUrl
	 */
	public void setAlipayRefundNotifyUrl(String alipayRefundNotifyUrl) {
		this.alipayRefundNotifyUrl = alipayRefundNotifyUrl;
	}

	/**
	 * 获取  alipayRefundKey
	 * @return alipayRefundKey
	 */
	public String getAlipayRefundKey() {
		return alipayRefundKey;
	}

	/**
	 * 设置 alipayRefundKey
	 * @param alipayRefundKey
	 */
	public void setAlipayRefundKey(String alipayRefundKey) {
		this.alipayRefundKey = alipayRefundKey;
	}

	/**
	 * 获取  alipayRefundUrl
	 * @return alipayRefundUrl
	 */
	public String getAlipayRefundUrl() {
		return alipayRefundUrl;
	}

	/**
	 * 设置 alipayRefundUrl
	 * @param alipayRefundUrl
	 */
	public void setAlipayRefundUrl(String alipayRefundUrl) {
		this.alipayRefundUrl = alipayRefundUrl;
	}
}
