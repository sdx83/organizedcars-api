package com.organizedcars.springboot.DOCUMENTODIGITAL;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

public interface DocumentoDigitalDAO extends JpaRepository<DocumentoDigital, Long>  {
	
	List<DocumentoDigital> findByVehiculo(Vehiculo vehiculo);
}