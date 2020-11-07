package com.organizedcars.springboot.VEHICULORECORDATORIOS;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.organizedcars.springboot.RECORDATORIO.Recordatorio;
import com.organizedcars.springboot.RECORDATORIO.RecordatorioCustom;
import com.organizedcars.springboot.RECORDATORIO.RecordatorioDAO;
import com.organizedcars.springboot.VEHICULO.Vehiculo;

@Service
@Transactional(readOnly = false)
public class VehiculoRecordatorioServiceImpl implements VehiculoRecordatorioService {
	
	@Autowired
	VehiculoRecordatorioDAO vehiculoRecordatorioDAO;
	
	@Autowired
	RecordatorioDAO recordatorioDAO;
	
	@Autowired
	RecordatorioDAO recordatorioCustomDAO;
	
	@Transactional(readOnly = true)
	public Optional<VehiculoRecordatorio> findById(Long id) {
		return vehiculoRecordatorioDAO.findById(id);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public VehiculoRecordatorio save(String nombreRecordatorio,String fechaRecordatorio,Vehiculo vehiculo) {
		
		Recordatorio rc = new RecordatorioCustom();
		rc.setNombre(nombreRecordatorio);
		rc.setDescripcion(nombreRecordatorio);
		
		Recordatorio recordatorioGuardado = recordatorioDAO.save(rc);
		
		VehiculoRecordatorio vr = new VehiculoRecordatorio();
		vr.setVehiculo(vehiculo);
		vr.setFechaRecordatorio(fechaRecordatorio);
		vr.setRecordatorio(recordatorioGuardado);
		vr.setHabilitado(true);
		
		return vehiculoRecordatorioDAO.save(vr);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public Void delete(VehiculoRecordatorio vrecordatorio) {
		vehiculoRecordatorioDAO.delete(vrecordatorio);
		recordatorioCustomDAO.deleteById(vrecordatorio.getRecordatorio().getIdRecordatorio());
		return null;
	}

	@Override
	public List<VehiculoRecordatorio> findByVehiculo(Vehiculo vehiculo) {
		return vehiculoRecordatorioDAO.findByVehiculo(vehiculo);
	}
}
