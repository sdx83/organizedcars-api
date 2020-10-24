package com.organizedcars.springboot.MANTENIMIENTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.USUARIOVEHICULOS.UsuarioVehiculo;
import com.organizedcars.springboot.USUARIOVEHICULOS.UsuarioVehiculoDAO;
import com.organizedcars.springboot.VEHICULO.Vehiculo;

@Service
@Transactional(readOnly = false)
public class MantenimientoServiceImpl implements MantenimientoService {
	
	@Autowired
	UsuarioVehiculoDAO usuarioVehiculoDAO;
	
	@Autowired
	MantenimientoDAO mantenimientoDAO;

	@Override
	public Mantenimiento save(Mantenimiento mantenimiento) {
		return mantenimientoDAO.save(mantenimiento);
	}

	@Override
	public Optional<Mantenimiento> findByOrdeDeTrabajo(String ot) {
		return mantenimientoDAO.findByOrdenDeTrabajo(ot);
	}
	
	@Override
	public List<Mantenimiento> findByVehiculo(Vehiculo vehiculo) {
		return mantenimientoDAO.findByVehiculo(vehiculo);
	}
	
	@Override
	public List<Mantenimiento> findByUsuario(Usuario usuario) {
		
		List<UsuarioVehiculo> usuarioVehiculos = usuarioVehiculoDAO.findByUsuario(usuario);
		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		List<Mantenimiento> mantenimientos = new ArrayList<Mantenimiento>();
		
		if(usuarioVehiculos != null && usuarioVehiculos.size() > 0) {
			for (UsuarioVehiculo usuarioVehiculo : usuarioVehiculos) {
				vehiculos.add(usuarioVehiculo.getVehiculo());
			}
		}else {
			return new ArrayList<Mantenimiento>();
		}
			
		if(vehiculos.size() > 0) {
			for (Vehiculo vehiculo : vehiculos) {
				mantenimientos.addAll(vehiculo.getMantenimientos());
			}
		}else {
			return new ArrayList<Mantenimiento>();
		}
		
		//ordeno los mantenimientos por dominio y fecha
 		Comparator<Mantenimiento> comparadorMantenimientos = (Mantenimiento m1, Mantenimiento m2) -> {
 			return ((m1.getVehiculo().getDominio() + m1.getVehiculo().getDominio() + m1.getFecha()).compareTo((m2.getFecha())) * -1);
 		};
 		
 		Collections.sort(mantenimientos, comparadorMantenimientos);
 		
		return mantenimientos;
	}
	
	@Transactional(readOnly = true)
	public Optional<Mantenimiento> findById(Long id) {
		return mantenimientoDAO.findById(id);
	}
	
	public ResponseEntity<Void> deleteByID(Long idMantenimiento) {
		mantenimientoDAO.deleteById(idMantenimiento);
		return ResponseEntity.ok(null);
	}
}
