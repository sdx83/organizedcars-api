package com.organizedcars.springboot.USUARIOVEHICULOS;

import java.util.List;

import com.organizedcars.springboot.USUARIO.Usuario;

public interface UsuarioVehiculoService {
	
	List<UsuarioVehiculo> findByUsuario(Usuario usuario);
	
}
