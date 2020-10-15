package com.organizedcars.springboot.VEHICULO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculoDAO extends JpaRepository<Vehiculo, Long>  {

	Optional<Vehiculo> findByDominio(String dominio);
	
}