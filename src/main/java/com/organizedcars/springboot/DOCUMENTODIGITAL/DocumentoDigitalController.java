package com.organizedcars.springboot.DOCUMENTODIGITAL;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.organizedcars.springboot.VEHICULO.Vehiculo;
import com.organizedcars.springboot.VEHICULO.VehiculoServiceImpl;


@RestController
@RequestMapping("/DocumentosDigitales")
@CrossOrigin(origins = "*")
public class DocumentoDigitalController {	
	
    @Autowired
	private DocumentoDigitalServiceImpl documentoDigitalService;
    
    @Autowired
	private VehiculoServiceImpl vehiculoService;
	
    //GET: http://localhost:8080/DocumentosDigitales/1
    @GetMapping(value="/{idDD}")
 	public ResponseEntity<DocumentoDigital> getDDByID(@PathVariable("idDD") Long id) throws Exception{	
  		
  		try {
  			Optional<DocumentoDigital> dd = documentoDigitalService.findById(id);
  	 		if(dd.isPresent()) {
  	 			return ResponseEntity.ok(dd.get());
  	 		}
  	 		else {
  	 			return ResponseEntity.noContent().build();
  	 		}
		} catch (Exception e) {
			throw new Exception("Error al obtener el Documento Digital");
		}
 	}
  	
 	
  	// POST: http://localhost:8080/DocumentosDigitales
 	@PostMapping
 	public ResponseEntity<DocumentoDigital> crearDD(@RequestBody DocumentoDigital dd) throws Exception {

 		try {
 			return ResponseEntity.ok(documentoDigitalService.save(dd));
 		} catch (ResponseStatusException e) {
 	 		throw new Exception(e.getReason());
 	 	} catch (Exception e) {
 	 		throw new Exception("Error al crear el Documento Digital");
 	 	}
 	}
 	  	
    // DELETE: http://localhost:8080/DocumentosDigitales/1
 	@RequestMapping(value = "/{idDD}", method = RequestMethod.DELETE)
 	public ResponseEntity<DocumentoDigital> eliminarDD(@PathVariable("idDD") Long idDD) throws Exception {

 		try {
 			Optional<DocumentoDigital> ddEliminar = documentoDigitalService.findById(idDD);
 			if(ddEliminar.isPresent()) {
 				return ResponseEntity.ok(documentoDigitalService.delete(ddEliminar.get()));
  	 		}
  	 		else {
  	 			return ResponseEntity.noContent().build();
  	 		}
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getReason());
		} catch (Exception e) {
			throw new Exception("Error al eliminar el Documento Digital");
		}
 	}
 	
 // GET: http://localhost:1317/DocumentosDigitales/Vehiculos/{dominio}
    @GetMapping(value="/DocumentosDigitales/Vehiculos/{dominio}")
	public ResponseEntity<List<DocumentoDigital>> obtenerDDsPorDominio(@PathVariable("dominio") String dominio) throws Exception{		
 		
 		try {
 			Optional<Vehiculo> vehiculo = vehiculoService.findByDominio(dominio);
 			
 			if (!vehiculo.isPresent()) {
 				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehículo no encontrado");
 			}
 		
 			List<DocumentoDigital> documentos = documentoDigitalService.findByVehiculo(vehiculo.get());
				
 			if(documentos != null && documentos.size() > 0) {
 				return ResponseEntity.ok(documentos);
 	 		}
 			else {
 	 			return ResponseEntity.noContent().build();
 	 		}
 		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getReason());
		} catch (Exception e) {
			throw new Exception("Error al obtener los documentos");
		}
	}
}
