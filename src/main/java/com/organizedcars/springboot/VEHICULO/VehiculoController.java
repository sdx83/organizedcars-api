package com.organizedcars.springboot.VEHICULO;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Vehiculos")
@CrossOrigin(origins = "*")
public class VehiculoController {	
	
    @Autowired
	private VehiculoServiceImpl vehiculoService;
	
	// GET: http://localhost:1317/Vehiculos/{dominio}
 	@RequestMapping(value="/{dominio}")
	public ResponseEntity<Vehiculo> getVehiculoByDominio(@PathVariable("dominio") String dominio) throws Exception{		
 		
 		try {
 			Optional<Vehiculo> vehiculo = vehiculoService.findByDominio(dominio);
 	 		if(vehiculo.isPresent()) {
 				return ResponseEntity.ok(vehiculo.get());
 			}
 			else {
 				return ResponseEntity.noContent().build();
 			}	
		} catch (Exception e) {
			throw new Exception("Error al obtener el vehículo");
		}
	}
 	
 	// POST: http://localhost:1317/Vehiculos
	@PostMapping
	public ResponseEntity<Vehiculo> altaVehiculo(@RequestBody Vehiculo vehiculo) throws Exception{
		
		try {
			Vehiculo nuevoVehiculo = vehiculoService.save(vehiculo);
			return ResponseEntity.ok(nuevoVehiculo);	
		} catch (Exception e) {
			throw new Exception("Error el cargar el vehículo");
		}
	}
	
	// DELETE: http://localhost:1317/Vehiculos/1
	@DeleteMapping(value="/{dominio}")
		public ResponseEntity<Void> deleteVehiculo(@PathVariable("dominio") String dominio) throws Exception{
		
		Optional<Vehiculo> vehiculo;
		
		try {
			vehiculo = vehiculoService.findByDominio(dominio);
			
			if(vehiculo.isPresent()) {
				vehiculoService.deleteByID(vehiculo.get().getIdVehiculo());
				return ResponseEntity.ok(null);
			}else {
				throw new Exception("Error al eliminar el vehículo");
			}
		} catch (Exception e) {
			throw new Exception("Error al eliminar el vehículo");
		}
	}
}
