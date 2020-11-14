package com.organizedcars.springboot.RECORDATORIO;

import java.util.List;

import com.organizedcars.springboot.VEHICULORECORDATORIOS.VehiculoRecordatorio;

public class MailRecordatorioHelper {

	public static void enviarNotificaciones(List<VehiculoRecordatorio> recordatorios, String mail) throws Exception {
		
		for (VehiculoRecordatorio vehiculoRecordatorio : recordatorios) {
			//TODO: rutina para enviar los mails correspondientes por cada recordatorio			
		}
	}
}
