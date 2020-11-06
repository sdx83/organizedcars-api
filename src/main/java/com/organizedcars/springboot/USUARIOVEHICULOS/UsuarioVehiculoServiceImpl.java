package com.organizedcars.springboot.USUARIOVEHICULOS;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.VEHICULO.Vehiculo;

@Service
@Transactional(readOnly = false)
public class UsuarioVehiculoServiceImpl implements UsuarioVehiculoService {
	
	@Autowired
	UsuarioVehiculoDAO usuarioVehiculoDAO;
	
	@Override
	public List<UsuarioVehiculo> findByUsuario(Usuario usuario) {
		return usuarioVehiculoDAO.findByUsuario(usuario);
	}
	
	public Optional<UsuarioVehiculo> findByUsuarioAndVehiculo(Usuario usuario, Vehiculo vehiculo) {
		return usuarioVehiculoDAO.findByUsuarioAndVehiculo(usuario, vehiculo);
	}

	public UsuarioVehiculo save(Usuario usuario, Vehiculo vehiculo) {
		return usuarioVehiculoDAO.save(new UsuarioVehiculo(usuario, vehiculo));
	}
}
