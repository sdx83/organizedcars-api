package com.organizedcars.springboot.VEHICULORECORDATORIOS;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

public interface VehiculoRecordatorioDAO extends JpaRepository<VehiculoRecordatorio, Long>  {
	
	List<VehiculoRecordatorio> findByVehiculo(Vehiculo vehiculo);
	
	List<VehiculoRecordatorio> findByVehiculoAndHabilitadoAndFechaRecordatorioNotNull(Vehiculo vehiculo,Boolean estado);
}