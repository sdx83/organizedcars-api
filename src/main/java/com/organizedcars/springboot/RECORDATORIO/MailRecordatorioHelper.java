package com.organizedcars.springboot.RECORDATORIO;

import java.sql.Date;
import java.util.List;

import com.organizedcars.springboot.RECORDATORIO.MAIL_DEVELOPMENT.EmailServiceImple;
import com.organizedcars.springboot.VEHICULORECORDATORIOS.VehiculoRecordatorio;
import org.apache.naming.factory.SendMailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;

public class MailRecordatorioHelper {

	@Autowired
	private EmailServiceImple emailServiceImple;


	private static final String MENSAJE="Organized cars te informa que tu recordatorio esta proximo a vencer:";

	public void mandarMail(VehiculoRecordatorio vehiculoRecordatorio,String mail){
		//Tomar el mail del usuario falta.
		String mensajeRecordatorio=vehiculoRecordatorio.getRecordatorio().getDescripcion();
		//El To aca cambia, hay que poner el mail del user que lo sacamos del localStorage segurmamente.
		emailServiceImple.send(mail,mensajeRecordatorio,MENSAJE+mensajeRecordatorio, Date.valueOf(vehiculoRecordatorio.getFechaRecordatorio()));
		//Entonces, nosotros le podemos poner cada cuento
	}

	public  void enviarNotificaciones(List<VehiculoRecordatorio> recordatorios, String mail) throws Exception {
		
		for (VehiculoRecordatorio vehiculoRecordatorio : recordatorios) {
			//TODO: rutina para enviar los mails correspondientes por cada recordatorio
			//Para mandar un mail seria :
			try{
				mandarMail(vehiculoRecordatorio,mail);
			}catch(MailSendException e){
				System.out.println("Errro al tratar de mandar el mail"+e.getMessage());
				throw new Error("Error al mandar el mail");
			}


		}
	}
}
