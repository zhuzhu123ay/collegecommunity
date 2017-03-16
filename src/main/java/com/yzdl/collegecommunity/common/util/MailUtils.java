package com.yzdl.collegecommunity.common.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils{
	public static String myEmailSMTPHost = "smtp.qq.com";
	public static Integer validate; 

	public static Integer sendMail(String to) throws Exception{
		// 设置邮件服务器
		Properties props=new Properties();
		
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");    
      //1.创建连接对象，连接到邮箱服务器
		Session session=Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("2514362803@qq.com", "mjjswdlrlswfeahb");
			}
		});
	  //2.创建邮件对象
		Message message=new MimeMessage(session);
		//设置发件人
		message.setFrom(new InternetAddress("2514362803@qq.com"));
		//设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		//设置主题
		message.setSubject("来自大学生社区的验证码邮件");
		//设置邮件的正文
		validate=UUIDUtits.getUUID(1, 999999);
		message.setText("邮件验证码:"+validate+" 欢迎加入大学生社区!");
	  //3.发送邮件
		Transport.send(message);
		return validate;
	}
}
