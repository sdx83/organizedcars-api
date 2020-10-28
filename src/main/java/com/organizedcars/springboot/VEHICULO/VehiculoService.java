package com.organizedcars.springboot.VEHICULO;

import java.util.Optional;

import com.organizedcars.springboot.USUARIO.Usuario;

public interface VehiculoService {
	
	
	public Vehiculo save(Vehiculo vehiculo, Usuario usuario) throws Exception;
	
	public Optional<Vehiculo> findByDominio(String dominio);

	
}
