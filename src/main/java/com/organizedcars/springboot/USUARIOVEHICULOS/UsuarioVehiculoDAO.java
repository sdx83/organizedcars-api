package com.organizedcars.springboot.USUARIOVEHICULOS;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizedcars.springboot.USUARIO.Usuario;

public interface UsuarioVehiculoDAO extends JpaRepository<UsuarioVehiculo, Long>  {
	
	Optional<List<UsuarioVehiculo>> findByUsuario(Usuario usuario);
	
}