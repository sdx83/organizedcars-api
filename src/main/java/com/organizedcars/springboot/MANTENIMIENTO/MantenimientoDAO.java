package com.organizedcars.springboot.MANTENIMIENTO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

public interface MantenimientoDAO extends JpaRepository<Mantenimiento, Long>  {

	List<Mantenimiento> findByVehiculo(Vehiculo vehiculo);
}