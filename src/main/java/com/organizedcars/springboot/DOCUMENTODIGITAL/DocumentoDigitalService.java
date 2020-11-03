package com.organizedcars.springboot.DOCUMENTODIGITAL;

import java.util.List;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

public interface DocumentoDigitalService {
	
	public DocumentoDigital save(DocumentoDigital documentoDigital);
	
	public DocumentoDigital delete(DocumentoDigital documentoDigital);
	
	public List<DocumentoDigital> findByVehiculo(Vehiculo vehiculo);
	
}
