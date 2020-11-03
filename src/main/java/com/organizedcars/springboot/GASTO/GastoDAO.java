package com.organizedcars.springboot.GASTO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

public interface GastoDAO extends JpaRepository<Gasto, Long>  {
	
	List<Gasto> findByVehiculo(Vehiculo vehiculo);
}