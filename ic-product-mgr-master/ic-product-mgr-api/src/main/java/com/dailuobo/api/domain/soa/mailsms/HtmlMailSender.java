package com.dailuobo.api.domain.soa.mailsms;

import com.dailuobo.api.domain.soa.StandardResult;
import com.mallcai.backend.common.utils.IAsyncCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * SimpleEMailSender<br/>
 * 描述:简单html邮件发送
 * @author huangwei
 * @since 2016-1-15<br/>
 */
public class HtmlMailSender implements Serializable {
	private static final long serialVersionUID = -4204100760666154808L;
	
	/** 日志处理. */
	private static Logger logger = LoggerFactory.getLogger(EMailSender.class);
	
	
	/** */
	public static StandardResult sendMonitorHtmlMail(String mainTitle, String mainContent){
		return sendHtmlMail(MailConfig.getDefaultConfig().getMonitorMails(), mainTitle, mainContent);
	}
	
	/**
	 * 即时发送邮件
	 * @param mailConfig	mail发送配置
	 * @param toMails		目标收件人
	 * @param mainTitle		mail头
	 * @param mainContent	mail内容
	 * @return
	 */
	public static StandardResult sendHtmlMail(List<String> toMails, String mainTitle, String mainContent){
		return sendSyncHtmlMail(MailConfig.getDefaultConfig(), toMails, mainTitle, mainContent, new Date());
	}
	
	
	/**
	 * 即时发送html邮件
	 * @param mailConfig	mail发送配置
	 * @param toMails		目标收件人
	 * @param mainTitle		mail头
	 * @param mainContent	mail内容
	 * @return
	 */
	public static StandardResult sendHtmlMail(MailConfig mailConfig, List<String> toMails, String mainTitle, String mainContent){
		return sendSyncHtmlMail(mailConfig, toMails, mainTitle, mainContent, new Date());
	}
	
	/**
	 * 定时发送html邮件
	 * @param mailConfig	mail发送配置
	 * @param mainTitle		mail头
	 * @param mainContent	mail内容
	 * @param sendTime		发送时间
	 */
	public static StandardResult sendHtmlMailToAnyMan(MailConfig mailConfig, List<String> toMails, String mainTitle, String mainContent, Date sendTime){
		return sendSyncHtmlMail(mailConfig, toMails, mainTitle, mainContent, sendTime);
	}

	/**
	 * 异步发送邮件根方法
	 * @param mailConfig 	发送邮件配置
	 * @param toMails		目标收件人
	 * @param mainTitle		邮件头
	 * @param mainContent	邮件正文
	 * @param sendTime		发送时间
	 * @param asyncCallback	异步回调的接口
	 */
	@SuppressWarnings("unused")
	private static void sendAsyncHtmlMail(final MailConfig mailConfig, final List<String> toMails, final String mainTitle, final String mainContent, 
											final Date sendTime, final IAsyncCallback asyncCallback){
		final Thread t = new Thread(new Runnable() {
			public void run() {
				if(sendSyncHtmlMail(mailConfig, toMails, mainTitle, mainContent, sendTime).getSuccess()){
					asyncCallback.onSuccess();
				}else{
					asyncCallback.onFailed();
				}
			}
		});
		t.start();
	}
	
	

	/**
	 * 同步发送简单邮件的根方法
	 * @param mailConfig 	邮件配置	
	 * @param toMails		目标收件人
	 * @param mainTitle		邮件头
	 * @param mainContent	邮件内容
	 * @param sendTime		发送时间
	 * @return
	 */
	private static StandardResult sendSyncHtmlMail(MailConfig mailConfig, List<String> toMails, String mainTitle, String mainContent, Date sendTime){
		Session session = Session.getInstance(mailConfig.getProp());
		MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象 
		try {
			message.setFrom(new InternetAddress(mailConfig.getFromMail()));
			Address[] toMailAddress = new InternetAddress[toMails.size()];
			for(int i = 0;i < toMails.size();i ++){
				toMailAddress[i] = new InternetAddress(toMails.get(i));
			}
			message.setRecipients(Message.RecipientType.TO, toMailAddress);//设置收件人,并设置其接收类型为TO  
			message.setSubject(mainTitle);//设置标题  
			message.setContent(mainContent, "text/html;charset=gbk"); //发送HTML邮件，内容样式比较丰富
			message.setSentDate(sendTime);//设置发信时间
			message.saveChanges();//存储邮件信息  
			Transport transport = session.getTransport();  
			transport.connect(mailConfig.getFromMailUser(), mailConfig.getFromMailPwd());  
			transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
			transport.close();
		} catch (AddressException e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}catch (MessagingException e) {
			logger.error(e.getMessage(), e);
			return StandardResult.getDefaultFailed(e.getMessage());
		}
		return StandardResult.getDefaultSuccess();
	}
}
