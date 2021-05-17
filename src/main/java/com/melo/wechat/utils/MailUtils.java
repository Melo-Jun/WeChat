package com.melo.wechat.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

public class MailUtils extends Thread {

    //发件人信息
    private String manager = "微信团队";
    //发件人邮箱
    private String recipient = "1158280627@qq.com";
    //邮箱密码
    private String password = "vabaevnblgyybagf";
    //邮件发送的服务器
    private String host = "smtp.qq.com";

    //收件人信息
    private String email;
    private String code;
    public MailUtils(String email, String code){
        this.email = email;
        this.code=code;
    }

    @Override
    public void run(){
        try {
            Properties properties = new Properties();

            properties.setProperty("mail.host","smtp.qq.com");

            properties.setProperty("mail.transport.protocol","smtp");

            properties.setProperty("mail.smtp.auth","true");

            //QQ存在一个特性设置SSL加密
            MailSSLSocketFactory sf = null;
            try {
                sf = new MailSSLSocketFactory();
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            //创建一个session对象
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(recipient,password);
                }
            });

            //开启debug模式
            session.setDebug(true);

            //获取连接对象
            Transport transport = null;
            try {
                transport = session.getTransport();
            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            }

            //连接服务器
            transport.connect(host,manager,password);

            //创建一个邮件发送对象
            MimeMessage mimeMessage = new MimeMessage(session);
            //邮件发送人
            mimeMessage.setFrom(new InternetAddress(recipient));

            //邮件接收人
            mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(email));

            //邮件标题
            mimeMessage.setSubject("你在注册微信时,请求了验证码");

            //邮件内容
            mimeMessage.setContent("你的注册码为: "+code+" ,该验证码将在你再次发送请求时失效,请正确填写最新收到的注册码,并完成注册","text/html;charset=UTF-8");

            //发送邮件
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

            transport.close();


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}


