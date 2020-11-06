package com.organizedcars.springboot.VEHICULO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.USUARIOVEHICULOS.UsuarioVehiculo;
import com.organizedcars.springboot.USUARIOVEHICULOS.UsuarioVehiculoDAO;

@Service
@Transactional(readOnly = false)
public class VehiculoServiceImpl implements VehiculoService {
	
	@Autowired
	VehiculoDAO vehiculoDAO;
	
	@Autowired
	UsuarioVehiculoDAO usuarioVehiculoDAO;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public Vehiculo save(Vehiculo vehiculo, Usuario usuario) throws Exception {
		
		
		Vehiculo vehiculoGuardado =  vehiculoDAO.save(vehiculo);
		
		if(vehiculoGuardado != null) {
			UsuarioVehiculo usuarioVehiculo = new UsuarioVehiculo(usuario,vehiculoGuardado);
			usuarioVehiculoDAO.save(usuarioVehiculo);
		}else {
			throw new Exception("Error al guardar el vehículo");
		}
		return vehiculoDAO.save(vehiculo);
	}

	@Override
	public Optional<Vehiculo> findByDominio(String dominio) {
		return vehiculoDAO.findByDominio(dominio);
	}
	
	@Transactional(readOnly = true)
	public Optional<Vehiculo> findById(Long id) {
		return vehiculoDAO.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Vehiculo> findAll() {
		return vehiculoDAO.findAll();
	}
	
	public ResponseEntity<Void> deleteByID(Long idVehiculo) {
		vehiculoDAO.deleteById(idVehiculo);
		return ResponseEntity.ok(null);
	}
	
	@Override
	public Vehiculo update(Vehiculo vehiculoExistente, Vehiculo vehiculoModificado) {
		
		vehiculoExistente.setMarca(vehiculoModificado.getMarca().trim());
		vehiculoExistente.setModelo(vehiculoModificado.getModelo().trim());
		vehiculoExistente.setPais(vehiculoModificado.getPais().trim());
		vehiculoExistente.setMarcaMotor(vehiculoModificado.getMarcaMotor().trim());
		vehiculoExistente.setTipo(vehiculoModificado.getTipo().trim());
		vehiculoExistente.setNroMotor(vehiculoModificado.getNroMotor().trim());
		vehiculoExistente.setMarcaChasis(vehiculoModificado.getMarcaChasis().trim());
		vehiculoExistente.setNroChasis(vehiculoModificado.getNroChasis().trim());
		vehiculoExistente.setAnioFabricacion(vehiculoModificado.getAnioFabricacion().trim());
		vehiculoExistente.setAnioModelo(vehiculoModificado.getAnioModelo().trim());
		vehiculoExistente.setFechaAdquisicion(vehiculoModificado.getFechaAdquisicion().trim());
		vehiculoExistente.setCarroceria(vehiculoModificado.getCarroceria().trim());
		vehiculoExistente.setPeso(vehiculoModificado.getPeso());
		vehiculoExistente.setNroOblea(vehiculoModificado.getNroOblea().trim());
		vehiculoExistente.setVtoOblea(vehiculoModificado.getVtoOblea().trim());
		vehiculoExistente.setCiaSeguro(vehiculoModificado.getCiaSeguro().trim());
		vehiculoExistente.setCoberturaSeguro(vehiculoModificado.getCoberturaSeguro().trim());
		vehiculoExistente.setDescripcionVehiculo(vehiculoModificado.getDescripcionVehiculo().trim());
		
		return vehiculoDAO.save(vehiculoExistente);
	}
}
