package com.organizedcars.springboot.MANTENIMIENTO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

@Service
@Transactional(readOnly = false)
public class MantenimientoServiceImpl implements MantenimientoService {
	
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
	
	@Transactional(readOnly = true)
	public Optional<Mantenimiento> findById(Long id) {
		return mantenimientoDAO.findById(id);
	}
	
	public ResponseEntity<Void> deleteByID(Long idMantenimiento) {
		mantenimientoDAO.deleteById(idMantenimiento);
		return ResponseEntity.ok(null);
	}
}
