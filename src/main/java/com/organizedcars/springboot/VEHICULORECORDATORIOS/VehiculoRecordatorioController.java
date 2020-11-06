package com.organizedcars.springboot.VEHICULORECORDATORIOS;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.organizedcars.springboot.VEHICULO.Vehiculo;
import com.organizedcars.springboot.VEHICULO.VehiculoServiceImpl;


@RestController
@RequestMapping("/VehiculoRecordatorios")
@CrossOrigin(origins = "*")
public class VehiculoRecordatorioController {	
	
    @Autowired
	private VehiculoRecordatorioServiceImpl vehiculoRecordatorioService;
    
    @Autowired
	private VehiculoServiceImpl vehiculoService;
	
    //GET: http://localhost:8080/VehiculoRecordatorios/1
    @GetMapping(value="/{idRecordatorio}")
 	public ResponseEntity<VehiculoRecordatorio> getRecordatorioByID(@PathVariable("idrecordatorio") Long id) throws Exception{	
  		
  		try {
  			Optional<VehiculoRecordatorio> vr = vehiculoRecordatorioService.findById(id);
  	 		if(vr.isPresent()) {
  	 			return ResponseEntity.ok(vr.get());
  	 		}
  	 		else {
  	 			return ResponseEntity.noContent().build();
  	 		}
		} catch (Exception e) {
			throw new Exception("Error al obtener el recordatorio");
		}
 	}
  	
 	
  	// POST: http://localhost:8080/VehiculoRecordatorios
 	@PostMapping
 	public ResponseEntity<VehiculoRecordatorio> crearRecordatorioCustom(@PathVariable("nombreRecordatorio") String nombreRecordatorio,
 																			@PathVariable("fechaRecordatorio") String fechaRecordatorio,
 																				@PathVariable("dominio") String dominio) throws Exception {

 		try {
 			Optional<Vehiculo> vehiculo = vehiculoService.findByDominio(dominio);
 			
 			if (!vehiculo.isPresent()) {
 				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehículo no encontrado");
 			}else {
 				//aca guarda el recordatorio custom y asigno al vehiculo en la tabla VehiculoRecordatorios
 	 			return ResponseEntity.ok(vehiculoRecordatorioService.save(nombreRecordatorio,fechaRecordatorio,vehiculo.get()));
 			}
 		} catch (ResponseStatusException e) {
 	 		throw new Exception(e.getReason());
 	 	} catch (Exception e) {
 	 		throw new Exception("Error al crear el recordatorio");
 	 	}
 	}
 	  	
    // DELETE: http://localhost:8080/VehiculoRecordatorios/1
 	@RequestMapping(value = "/{idVehiculoRecordatorio}", method = RequestMethod.DELETE)
 	public ResponseEntity<Void> eliminarVehiculoRecordatorio(@PathVariable("idVehiculoRecordatorio") Long id) throws Exception {

 		try {
 			Optional<VehiculoRecordatorio> vrecordatorioEliminar = vehiculoRecordatorioService.findById(id);
 			if(vrecordatorioEliminar.isPresent()) {
 				vehiculoRecordatorioService.delete(vrecordatorioEliminar.get());
 				return ResponseEntity.ok(null);
  	 		}
  	 		else {
  	 			return ResponseEntity.noContent().build();
  	 		}
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getReason());
		} catch (Exception e) {
			throw new Exception("Error al eliminar el recordatorio del vehiculo");
		}
 	}
 	
 	// GET: http://localhost:8080/VehiculoRecordatorios/Vehiculos/{dominio}
    @GetMapping(value="/Vehiculos/{dominio}")
	public ResponseEntity<List<VehiculoRecordatorio>> obtenerRecordatoriosPorDominio(@PathVariable("dominio") String dominio) throws Exception{		
 		
 		try {
 			Optional<Vehiculo> vehiculo = vehiculoService.findByDominio(dominio);
 			
 			if (!vehiculo.isPresent()) {
 				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehículo no encontrado");
 			}
 		
 			List<VehiculoRecordatorio> recordatoriosDeVehiculo = vehiculoRecordatorioService.findByVehiculo(vehiculo.get());
				
 			if(recordatoriosDeVehiculo != null && recordatoriosDeVehiculo.size() > 0) {
 				return ResponseEntity.ok(recordatoriosDeVehiculo);
 	 		}
 			else {
 	 			return ResponseEntity.noContent().build();
 	 		}
 		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getReason());
		} catch (Exception e) {
			throw new Exception("Error al obtener los recordatorios");
		}
	}
}
