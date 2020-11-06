package com.organizedcars.springboot.GASTO;
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
@RequestMapping("/Gastos")
@CrossOrigin(origins = "*")
public class GastoController {	
	
    @Autowired
	private GastoServiceImpl gastoService;
    
    @Autowired
	private VehiculoServiceImpl vehiculoService;
	
    //GET: http://localhost:8080/Gastos/1
    @GetMapping(value="/{idGasto}")
 	public ResponseEntity<Gasto> getGastoByID(@PathVariable("idGasto") Long id) throws Exception{	
  		
  		try {
  			Optional<Gasto> gasto = gastoService.findById(id);
  	 		if(gasto.isPresent()) {
  	 			return ResponseEntity.ok(gasto.get());
  	 		}
  	 		else {
  	 			return ResponseEntity.noContent().build();
  	 		}
		} catch (Exception e) {
			throw new Exception("Error al obtener el gasto");
		}
 	}
  	
 	
  	// POST: http://localhost:8080/Gastos
 	@PostMapping
 	public ResponseEntity<Gasto> crearGasto(@RequestBody Gasto gasto) throws Exception {

 		try {
 			return ResponseEntity.ok(gastoService.save(gasto));
 		} catch (ResponseStatusException e) {
 	 		throw new Exception(e.getReason());
 	 	} catch (Exception e) {
 	 		throw new Exception("Error al crear el gasto");
 	 	}
 	}
 	  	
    // DELETE: http://localhost:8080/Gastos/1
 	@RequestMapping(value = "/{idGasto}", method = RequestMethod.DELETE)
 	public ResponseEntity<Void> eliminarGasto(@PathVariable("idGasto") Long idGasto) throws Exception {

 		try {
 			Optional<Gasto> gastoEliminar = gastoService.findById(idGasto);
 			if(gastoEliminar.isPresent()) {
 				gastoService.delete(gastoEliminar.get());
 				return ResponseEntity.ok(null);
  	 		}
  	 		else {
  	 			return ResponseEntity.noContent().build();
  	 		}
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getReason());
		} catch (Exception e) {
			throw new Exception("Error al eliminar el gasto");
		}
 	}
 	
 	// GET: http://localhost:8080/Gastos/Vehiculos/{dominio}
    @GetMapping(value="/Vehiculos/{dominio}")
	public ResponseEntity<List<Gasto>> obtenerGastosPorDominio(@PathVariable("dominio") String dominio) throws Exception{		
 		
 		try {
 			Optional<Vehiculo> vehiculo = vehiculoService.findByDominio(dominio);
 			
 			if (!vehiculo.isPresent()) {
 				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehículo no encontrado");
 			}
 		
 			List<Gasto> gastos = gastoService.findByVehiculo(vehiculo.get());
				
 			if(gastos != null && gastos.size() > 0) {
 				return ResponseEntity.ok(gastos);
 	 		}
 			else {
 	 			return ResponseEntity.noContent().build();
 	 		}
 		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getReason());
		} catch (Exception e) {
			throw new Exception("Error al obtener los gastos");
		}
	}
}
