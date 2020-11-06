package com.organizedcars.springboot.USUARIOVEHICULOS;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.VEHICULO.Vehiculo;

public interface UsuarioVehiculoDAO extends JpaRepository<UsuarioVehiculo, Long>  {
	
	List<UsuarioVehiculo> findByUsuario(Usuario usuario);
	
	Optional<UsuarioVehiculo> findByUsuarioAndVehiculo(Usuario usuario, Vehiculo vehiculo);
}