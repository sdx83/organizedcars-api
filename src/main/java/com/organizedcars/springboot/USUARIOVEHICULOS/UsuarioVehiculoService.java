package com.organizedcars.springboot.USUARIOVEHICULOS;

import java.util.List;
import java.util.Optional;

import com.organizedcars.springboot.USUARIO.Usuario;

public interface UsuarioVehiculoService {
	
	Optional<List<UsuarioVehiculo>> findByUsuario(Usuario usuario);
	
}
