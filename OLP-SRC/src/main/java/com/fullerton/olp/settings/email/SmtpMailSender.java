package com.fullerton.olp.settings.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SmtpMailSender {

    @Autowired
    private JavaMailSender javaMailSender;
    
    @Value("${mail.from}")
    private String mailFrom;
    
    public void send(String to, String subject, String body) {
    		Thread t = new Thread() {
    			@Override
    			public void run() {
    				 try {
						MimeMessage message = javaMailSender.createMimeMessage();
						MimeMessageHelper helper;

						helper = new MimeMessageHelper(message, true); // true indicates
						// multipart message
						helper.setSubject(subject);
						helper.setTo(to.split("[\\s,;]+"));
						helper.setText(body, true); // true indicates html
						// continue using helper object for more functionalities like adding attachments, etc.
						helper.setFrom(mailFrom);
						javaMailSender.send(message);
					} catch (MailException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    		};
    		t.start();

    }

}
