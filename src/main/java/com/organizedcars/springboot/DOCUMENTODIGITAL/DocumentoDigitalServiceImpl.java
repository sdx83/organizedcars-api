package com.organizedcars.springboot.DOCUMENTODIGITAL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

@Service
@Transactional(readOnly = false)
public class DocumentoDigitalServiceImpl implements DocumentoDigitalService {
	
	@Autowired
	DocumentoDigitalDAO documentoDigitalDAO;
	
	@Transactional(readOnly = true)
	public Optional<DocumentoDigital> findById(Long id) {
		return documentoDigitalDAO.findById(id);
	}

	@Override
	public DocumentoDigital save(DocumentoDigital documentoDigital) {
		
		return documentoDigitalDAO.save(documentoDigital);
	}
	
	@Override
	public Void delete(DocumentoDigital documentoDigital) {
		documentoDigitalDAO.delete(documentoDigital);
		return null;
	}
	

	@Transactional(readOnly = true)
	public List<DocumentoDigital> findByVehiculo(Vehiculo vehiculo) {
		return documentoDigitalDAO.findByVehiculo(vehiculo);
	}
}
