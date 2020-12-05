package com.organizedcars.springboot.VEHICULORECORDATORIOS;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.VEHICULO.Vehiculo;

public interface VehiculoRecordatorioService {
	
	public VehiculoRecordatorio save(String nombreRecordatorio,String fechaRecordatorio,Vehiculo vehiculo, String mail);
	
	public Void delete(VehiculoRecordatorio recordatorio);
	
	public List<VehiculoRecordatorio> findByVehiculo(Vehiculo vehiculo);
	
	public VehiculoRecordatorio update(VehiculoRecordatorio vehiculoRecordatorioExistente, VehiculoRecordatorio vehiculoRecordatorioModificado, String mail);
	
	public VehiculoRecordatorio updateEstado(VehiculoRecordatorio vehiculoRecordatorioExistente, Boolean habilitado);
	
	public List<VehiculoRecordatorio> enviarNotificaciones(Usuario usuario) throws ParseException, Exception;
	
	public void mandarMail(Optional<VehiculoRecordatorio> vehiculoRecordatorio, String to, String accion) throws Exception;
}
