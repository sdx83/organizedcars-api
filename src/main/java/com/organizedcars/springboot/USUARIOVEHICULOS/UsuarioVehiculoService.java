package com.organizedcars.springboot.USUARIOVEHICULOS;

import java.util.List;
import java.util.Optional;

import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.VEHICULO.Vehiculo;

public interface UsuarioVehiculoService {
	
	List<UsuarioVehiculo> findByUsuario(Usuario usuario);
	
	Optional<UsuarioVehiculo> findByUsuarioAndVehiculo(Usuario usuario, Vehiculo vehiculo);
	
	UsuarioVehiculo save(Usuario usuario, Vehiculo vehiculo);
}
