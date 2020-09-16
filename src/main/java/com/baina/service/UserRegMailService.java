package com.baina.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.baina.dto.UserMailRequest;
import com.baina.dto.UserMailResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class UserRegMailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private Configuration configuration;
	
	public UserMailResponse sendMail(UserMailRequest userMailRequest,Map<String,Object> model) {
		
		UserMailResponse userMailResponse=new UserMailResponse();
		MimeMessage MimeMessage=javaMailSender.createMimeMessage();
		try {
			//set media type
			MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(MimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,StandardCharsets.UTF_8.name());
			
			//add attachment
			mimeMessageHelper.addAttachment("logo.png",new ClassPathResource("logo.png"));
			
			Template template=configuration.getTemplate("email-template.ftl");
			String html=FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			
			mimeMessageHelper.setFrom(userMailRequest.getFrom());
			mimeMessageHelper.setTo(userMailRequest.getTo());
			mimeMessageHelper.setSubject(userMailRequest.getSubject());
			mimeMessageHelper.setText(html,true);
			javaMailSender.send(MimeMessage);
			
			userMailResponse.setMessage("mail sent to :"+userMailRequest.getTo());
			userMailResponse.setStatus(Boolean.TRUE);
			
			
			
		}catch (MessagingException | IOException | TemplateException e) {
			userMailResponse.setMessage(e.getMessage());
			userMailResponse.setStatus(Boolean.FALSE);
		}
		return userMailResponse;
	}

}
