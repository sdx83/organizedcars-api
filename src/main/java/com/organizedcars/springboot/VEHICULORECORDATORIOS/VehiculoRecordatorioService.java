package com.organizedcars.springboot.VEHICULORECORDATORIOS;

import java.util.List;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

public interface VehiculoRecordatorioService {
	
	public VehiculoRecordatorio save(String nombreRecordatorio,String fechaRecordatorio,Vehiculo vehiculo);
	
	public Void delete(VehiculoRecordatorio recordatorio);
	
	public List<VehiculoRecordatorio> findByVehiculo(Vehiculo vehiculo);
	
	
}
