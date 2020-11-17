package com.organizedcars.springboot.RECORDATORIO.MAIL_DEVELOPMENT;

import com.organizedcars.springboot.RECORDATORIO.MailRecordatorioHelper;
import com.organizedcars.springboot.VEHICULORECORDATORIOS.VehiculoRecordatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "mails/")
@CrossOrigin(value = "*")
public class EmailSenderController {
    @Autowired
    private EmailServiceImple emailServiceImple;

    @Autowired
    private JavaMailSenderProperties javaMailSenderProperties;

    MailRecordatorioHelper mailRecordatorioHelper=new MailRecordatorioHelper();

    @PostMapping(value = "/send/")
    public ResponseEntity<String> sendMailReminder(@RequestParam(value = "destinatario") String to,@RequestParam(value = "subject") String subject,@RequestParam(value = "text") String text,@RequestParam(name = "fecha") String fecha){
        try{
            //Este destinatario, deberiamos sacarlo directo del localStorage

            emailServiceImple.sendOnly(to,subject,text,Date.valueOf(fecha));
            VehiculoRecordatorio vehiculoRecordatorio=new VehiculoRecordatorio();
            vehiculoRecordatorio.setFechaRecordatorio("2020-11-17");
            //Nos estan cagando los Threads, hay que ver como lo hacemos. --> Listo, quedo en StandBy lo otyro.

            //mailRecordatorioHelper.mandarMail(Optional.of(vehiculoRecordatorio),to);
            //Para mi es una cosa de los Threads y el runtime.
        }catch(MailException e){
            System.out.println(e.getMessage());

            return new ResponseEntity<>("Error al mandar el mail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Mail correctamente enviado",HttpStatus.OK);
    }


}
