package com.organizedcars.springboot.USUARIOVEHICULOS;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.organizedcars.springboot.USUARIO.Usuario;

@Service
@Transactional(readOnly = false)
public class UsuarioVehiculoServiceImpl implements UsuarioVehiculoService {
	
	@Autowired
	UsuarioVehiculoDAO usuarioVehiculoDAO;
	
	@Override
	public Optional<List<UsuarioVehiculo>> findByUsuario(Usuario usuario) {
		return usuarioVehiculoDAO.findByUsuario(usuario);
	}
	
}
