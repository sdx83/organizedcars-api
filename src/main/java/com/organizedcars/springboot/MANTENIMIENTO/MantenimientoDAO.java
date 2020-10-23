package com.organizedcars.springboot.MANTENIMIENTO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

public interface MantenimientoDAO extends JpaRepository<Mantenimiento, Long>  {

	Optional<Mantenimiento> findByOrdenDeTrabajo(String ot);
	
	List<Mantenimiento> findByVehiculo(Vehiculo vehiculo);
	
}