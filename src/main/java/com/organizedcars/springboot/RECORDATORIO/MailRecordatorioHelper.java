package com.organizedcars.springboot.RECORDATORIO;

import java.util.List;

import org.springframework.core.env.Environment;

import com.organizedcars.springboot.VEHICULORECORDATORIOS.VehiculoRecordatorio;

public class MailRecordatorioHelper {

	public static void enviarNotificaciones(List<VehiculoRecordatorio> recordatorios, String mail) throws Exception {
		
		//Prueba cloudinary
		Environment environment = null;
		String key = environment.getProperty("api_secret_cloudinary").toString();
		
		
		for (VehiculoRecordatorio vehiculoRecordatorio : recordatorios) {
			//TODO: rutina para enviar los mails correspondientes por cada recordatorio			
		}
	}
}
