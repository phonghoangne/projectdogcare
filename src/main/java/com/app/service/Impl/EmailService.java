package com.app.service.Impl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendEmail(String to,String subject,String body,File attachment)  throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message ,true ,StandardCharsets.UTF_8.name());
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body);
        if(attachment != null ){
            helper.addAttachment(attachment.getName(),attachment);
        }
        mailSender.send(message);
    }



}
