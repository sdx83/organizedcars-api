package com.organizedcars.springboot.RECORDATORIO;

import com.organizedcars.springboot.RECORDATORIO.MAIL_DEVELOPMENT.EmailServiceImple;
import com.organizedcars.springboot.RECORDATORIO.MAIL_DEVELOPMENT.JavaMailSenderProperties;
import com.organizedcars.springboot.VEHICULO.Vehiculo;
import com.organizedcars.springboot.VEHICULORECORDATORIOS.VehiculoRecordatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class MailRecordatorioHelper {

	@Autowired
	private EmailServiceImple emailServiceImple;

	@Autowired
	private JavaMailSenderProperties javaMailSenderProperties;


	private static final String MENSAJE="Organized cars te informa que tu recordatorio para el auto con dominio: " ;
	private static final String SUBJECT="ALERTA DE RECORDATORIO";
	public void mandarMail(Optional<VehiculoRecordatorio> vehiculoRecordatorio, String to) throws MailException{


		try{
			if(vehiculoRecordatorio.get()!=null) {
				System.out.println("Aca entro padre");
				emailServiceImple.sendOnly(to, SUBJECT,MENSAJE + "ABC123" + " se creó/modificó y vence el: " + " PAGARLE EL MANTENIMIENTO DEL AUTO A CACHO", Date.valueOf("2020-11-17"));
			}else{
				System.out.println("El vehiculoRecordatorio es null");
			}
		}catch (NullPointerException e){
			System.out.println("Error"+e.getLocalizedMessage());
			System.out.println("Causa"+e.getCause());
			e.printStackTrace();
		}
		catch (MailException e){
			System.out.println("Error en el mail"+e.getMessage());
		}


		//Entonces, nosotros le podemos poner cada cuento
	}

	public  void enviarNotificaciones(List<VehiculoRecordatorio> recordatorios, String mail) throws Exception {

		
		
		for (VehiculoRecordatorio vehiculoRecordatorio : recordatorios) {
			//TODO: rutina para enviar los mails correspondientes por cada recordatorio
			//Para mandar un mail seria :
			try{
				//mandarMail(Optional.of(vehiculoRecordatorio),mail);
			}catch(MailSendException e){
				System.out.println("Errro al tratar de mandar el mail"+e.getMessage());
				throw new Error("Error al mandar el mail");
			}


		}
	}
}
