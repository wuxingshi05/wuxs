package com.wuxs.myspringbootmybatis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@Component
public class EmailUtil {

    @Autowired
    JavaMailSender jms;
    /**
     * 发送普通邮件
     * @param addto 收件人地址
     */
    public int sendSimpleEmail(String addto) {
        int code = randomCode();
        //建立邮件消息
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //邮件发送方
        simpleMailMessage.setFrom("wuxingshi05@gmail.com");
        //邮件接收方
        simpleMailMessage.setTo(addto);
        //邮件主题
        simpleMailMessage.setSubject("验证码");
        //邮件验证码
        simpleMailMessage.setText("验证码："+ code);
        //发送邮件
        jms.send(simpleMailMessage);
        return code;
    }

    /**
     * 生成验证码
     * @return
     */
    public static int randomCode() {
        return (int) ((Math.random() * 9 + 1) * 100000);
    }

    public void findRandom (HttpServletResponse response) throws IOException {
        // 验证码字符个数
        int codeCount = 4;
        char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        // 创建一个随机数生成器类
        Random random = new Random();
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        PrintWriter out = response.getWriter();
        out.print(randomCode);
    }
}
