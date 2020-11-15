package com.organizedcars.springboot.RECORDATORIO.MAIL_DEVELOPMENT;

import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping(value = "mails/")
@CrossOrigin(value = "*")
public class EmailSenderController {
    @Autowired
    private EmailServiceImple emailServiceImple;

    @Autowired
    private JavaMailSenderProperties javaMailSenderProperties;

    @PostMapping(value = "/send/")
    public ResponseEntity<String> sendMailReminder(@RequestParam(value = "destinatario") String to,@RequestParam(value = "subject") String subject,@RequestParam(value = "text") String text,@RequestParam(name = "fecha") String fecha){
        try{
            emailServiceImple.send(to,subject,text,Date.valueOf(fecha));
            System.out.println("Paso por acá");
        }catch(MailException e){
            System.out.println(e.getMessage());

            return new ResponseEntity<>("Error al mandar el mail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Mail correctamente enviado",HttpStatus.OK);
    }

}
