package com.organizedcars.springboot.RECORDATORIO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.organizedcars.springboot.VEHICULORECORDATORIOS.VehiculoRecordatorioDAO;

@Service
@Transactional(readOnly = false)
public class RecordatorioServiceImpl implements RecordatorioService {
	
	@Autowired
	RecordatorioDAO recordatorioDAO;
	
	@Autowired
	VehiculoRecordatorioDAO vehiculoRecordatorioDAO;
	
	@Transactional(readOnly = true)
	public Optional<Recordatorio> findById(Long id) {
		return recordatorioDAO.findById(id);
	}

	@Override
	public Void delete(Recordatorio recordatorio) {
		recordatorioDAO.delete(recordatorio);
		return null;
	}
}
