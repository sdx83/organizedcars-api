package com.organizedcars.springboot.VEHICULO;

import java.util.Optional;

public interface VehiculoService {
	
	
	public Vehiculo save(Vehiculo vehiculo);
	
	public Optional<Vehiculo> findByDominio(String dominio);

	
}
