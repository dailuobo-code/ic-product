/**
 * 
 */
package com.dailuobo.api.domain.soa.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dailuobo.api.domain.soa.StandardHttpResp;
import com.dailuobo.api.domain.soa.StandardResult;
import com.dailuobo.api.domain.util.LoggerUtils;
import com.dailuobo.api.domain.util.ZooKeeperLock;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.Charset;

/**
 * @author Aministartor
 *
 */
public class EasemobUtil implements Serializable {
	private static final long serialVersionUID = 1;
	private static final String SPLIT_CHAR = "/";
	private static final String EASEMOB_TOKEN_LOCK = "/lock/easemob/token";
	private String grantType;
	private String clientId;
	private String clientSecret;
	private String apiUrl;
	private String appkey;
	private String orgname;
	private String appname;
	private String testUsername;
	private ZooKeeperLock lock;

	public StandardHttpResp regUser(String username, String password, String nickname, String token) {
		JSONObject obj = new JSONObject();
		obj.put("username", username);
		obj.put("password", password);
		obj.put("nickname", nickname);
		String reqUrl = getApiUrl() + "users";
		StandardHttpResp standardHttpResp = sendRequestPostHttpClient(obj.toJSONString(), reqUrl, token);
		return standardHttpResp;
	}
	
	public Boolean checkToken(String token) {
		String reqUrl = getApiUrl() + "users" + SPLIT_CHAR + testUsername;
		StandardHttpResp standardHttpResp = sendRequestPostHttpClient(null, reqUrl, token);
		return standardHttpResp.getIsSuccess();
	}
	
	public StandardResult getToken() {
		StandardResult standardResult = null;
		try {
			if(lock.lock(EASEMOB_TOKEN_LOCK, 0)) {
				JSONObject obj = new JSONObject();
				obj.put("grant_type", grantType);
				obj.put("client_id", clientId);
				obj.put("client_secret", clientSecret);
				String reqUrl = getApiUrl() + "token";
				StandardHttpResp standardHttpResp = sendRequestPostHttpClient(obj.toJSONString(), reqUrl, null);
				if(standardHttpResp.getIsSuccess()) {
					JSONObject jsonObject = JSON.parseObject(standardHttpResp.getResponseMsg());
					String token = jsonObject.getString("access_token");
					String expiresin = jsonObject.getString("expires_in");
					standardResult = new StandardResult(true,token,expiresin);
				} else {
					standardResult = new StandardResult(false, standardHttpResp.getResponseCode(), standardHttpResp.getReasonPhrase());
				}
			} else {
				//未拿到锁-1
				standardResult = new StandardResult(false, "-1", "刷新token时未拿到锁");
			}
		} finally {
			lock.unlock();
		}
		
		return standardResult;
	}
	
	private String getApiUrl() {
		return apiUrl + SPLIT_CHAR + orgname + SPLIT_CHAR + appname + SPLIT_CHAR;
	}
	
	private StandardHttpResp sendRequestPostHttpClient(String reqMsg, String reqUrl, String token) {
		HttpPost httpPost = new HttpPost(reqUrl);
		if(!StringUtils.isBlank(reqMsg)) {
			StringEntity entity = new StringEntity(reqMsg, ContentType.create(ContentType.APPLICATION_JSON.getMimeType(), "utf-8"));
			httpPost.setEntity(entity);
		}
		if(!StringUtils.isBlank(token))
			httpPost.setHeader("Authorization", "Bearer " + token);
		SSLContext sslContext = SSLContexts.createSystemDefault();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,NoopHostnameVerifier.INSTANCE);
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
			if(respCharset == null) respCharset = Charset.forName("utf-8");
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
	}

	/**
	 * 获取grantType
	 * @return the grantType
	 */
	public String getGrantType() {
		return grantType;
	}

	/**
	 * 设置grantType
	 * @param grantType the grantType to set
	 */
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	/**
	 * 获取clientId
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * 设置clientId
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * 获取clientSecret
	 * @return the clientSecret
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * 设置clientSecret
	 * @param clientSecret the clientSecret to set
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	/**
	 * 获取appkey
	 * @return the appkey
	 */
	public String getAppkey() {
		return appkey;
	}

	/**
	 * 设置appkey
	 * @param appkey the appkey to set
	 */
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	/**
	 * 获取orgname
	 * @return the orgname
	 */
	public String getOrgname() {
		return orgname;
	}

	/**
	 * 设置orgname
	 * @param orgname the orgname to set
	 */
	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	/**
	 * 获取appname
	 * @return the appname
	 */
	public String getAppname() {
		return appname;
	}

	/**
	 * 设置appname
	 * @param appname the appname to set
	 */
	public void setAppname(String appname) {
		this.appname = appname;
	}

	/**
	 * 设置apiUrl
	 * @param apiUrl the apiUrl to set
	 */
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	/**
	 * 获取testUsername
	 * @return the testUsername
	 */
	public String getTestUsername() {
		return testUsername;
	}

	/**
	 * 设置testUsername
	 * @param testUsername the testUsername to set
	 */
	public void setTestUsername(String testUsername) {
		this.testUsername = testUsername;
	}

	/**
	 * 获取lock
	 * @return the lock
	 */
	public ZooKeeperLock getLock() {
		return lock;
	}

	/**
	 * 设置lock
	 * @param lock the lock to set
	 */
	public void setLock(ZooKeeperLock lock) {
		this.lock = lock;
	}

}
