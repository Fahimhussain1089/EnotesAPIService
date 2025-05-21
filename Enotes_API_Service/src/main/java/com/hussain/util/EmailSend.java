package com.hussain.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.hussain.dto.EmailRequest;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailSend {

	
	@Autowired
	private  JavaMailSender mailSender;

	@Value("${email.send.from}")
	private String mailFrom;

	
	public void send (EmailRequest emailReq) throws Exception{
		
		MimeMessage message =  mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom(mailFrom, emailReq.getTitle());
		helper.setTo(emailReq.getTo());
		helper.setSubject(emailReq.getSubject());
		helper.setText(emailReq.getMessage());

		mailSender.send(message);
		

		
	}


}
