package com.organizedcars.springboot.RECORDATORIO.MAIL_DEVELOPMENT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailServiceImple implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;





    public void send(String to, String subject, String text, Date date) throws MailException {

        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setFrom("noreply@organizedcars.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        //Aca le podemos mandar que fecha quiere mandarlo.
        simpleMailMessage.setSentDate(date);

        javaMailSender.send(simpleMailMessage);
    }


    @Override
    public void send(SimpleMailMessage simpleMessage) throws MailException {
        System.out.println("Metodo que no quiero implementar");
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {
        System.out.println("Metodo que tampoco quiero implementar");
    }
}
