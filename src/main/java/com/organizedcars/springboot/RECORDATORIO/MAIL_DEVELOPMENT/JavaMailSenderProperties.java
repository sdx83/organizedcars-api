package com.organizedcars.springboot.RECORDATORIO.MAIL_DEVELOPMENT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.organizedcars.springboot.HELPERS.UsefulValues;

import java.util.Properties;

@Configuration
public class JavaMailSenderProperties {

    @Bean
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(UsefulValues.PROPERTIES.get("username"));
        mailSender.setPassword(UsefulValues.PROPERTIES.get("password"));


        Properties properties=mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.debug","true");
        properties.put("mail.smtp.ssl.trust","smtp.gmail.com");

        return mailSender;
    }
}
