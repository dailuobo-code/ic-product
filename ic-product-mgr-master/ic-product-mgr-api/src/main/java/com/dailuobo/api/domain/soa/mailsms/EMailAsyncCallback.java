package com.dailuobo.api.domain.soa.mailsms;


import com.mallcai.backend.common.utils.IAsyncCallback;

/**
 *
 * EMailAsyncCallback
 * 描述:
 * @since 2015-12-24
 * @author huangwei
 * 
 */
public class EMailAsyncCallback implements IAsyncCallback {

	/* (non-Javadoc)
	 * @see com.mallcai.utils.IAsyncCallback#onSuccess()
	 */
	@Override
	public void onSuccess() {
		System.out.println("邮件发送成功");
	}

	/* (non-Javadoc)
	 * @see com.mallcai.utils.IAsyncCallback#onFailed()
	 */
	@Override
	public void onFailed() {
		System.out.println("邮件发送失败");
	}
	
}
