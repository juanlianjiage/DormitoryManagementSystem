package com.example.springboot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class EmailUtils {

    @Autowired
    private JavaMailSender mailSender;
    public  String sendEmail(String email, String subject, String text) {

        // 创建一个简单邮件消息对象
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2521856799@qq.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        // 发送邮件
        mailSender.send(message);
        return "邮件发送成功";
    }

}
