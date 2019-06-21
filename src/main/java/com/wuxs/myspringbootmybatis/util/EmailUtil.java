package com.wuxs.myspringbootmybatis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    JavaMailSender jms;
    /**
     * 发送普通邮件
     * @param addto 收件人地址
     */
    public String sendSimpleEmail(String addto) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("wuxingshi05@gmail.com");
        simpleMailMessage.setTo(addto);
        simpleMailMessage.setSubject("验证码");
        simpleMailMessage.setText("验证码：888888");
        jms.send(simpleMailMessage);
        return null;

    }
}
