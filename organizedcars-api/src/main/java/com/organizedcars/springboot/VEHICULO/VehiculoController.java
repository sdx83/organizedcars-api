package com.organizedcars.springboot.VEHICULO;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Vehiculos")
public class VehiculoController {	
	
    @Autowired
	private VehiculoServiceImpl vehiculoService;
	
    // GET: http://localhost:1317/Vehiculos
    @GetMapping
	public ResponseEntity<List<Vehiculo>> getPacientes(){		
		List<Vehiculo> pacientes = vehiculoService.findAll();
		return ResponseEntity.ok(pacientes);
	}

 	// GET: http://localhost:1317/Vehiculos/1
 	@RequestMapping(value="/{pacienteID}")
	public ResponseEntity<Vehiculo> getPacienteByID(@PathVariable("pacienteID") Long id){		
		Optional<Vehiculo> optionalVehiculo = vehiculoService.findById(id);
		if(optionalVehiculo.isPresent()) {
			return ResponseEntity.ok(optionalVehiculo.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}	
	}
 	
	// GET: http://localhost:1317/Vehiculos/dominio/1
 	@RequestMapping(value="/dominio/{documento}")
	public ResponseEntity<Vehiculo> getVehiculoByDominio(@PathVariable("dominio") String dominio){		
 		Optional<Vehiculo> vehiculo = vehiculoService.findByDominio(dominio);
 		if(vehiculo.isPresent()) {
			return ResponseEntity.ok(vehiculo.get());
		}
		else {
			return ResponseEntity.noContent().build();
		}	
	}
 	
 	// POST: http://localhost:1317/Vehiculos
	@PostMapping
	public ResponseEntity<Vehiculo> createVehiculo(@RequestBody Vehiculo vehiculo){
		Vehiculo nuevoPaciente = vehiculoService.save(vehiculo);
		return ResponseEntity.ok(nuevoPaciente);
	}
	
	// DELETE: http://localhost:1317/Vehiculos/1
	@DeleteMapping(value="/{pacienteID}")
		public ResponseEntity<Void> deleteVehiculo(@PathVariable("vehiculoID") Long id){
		vehiculoService.deleteByID(id);
		return ResponseEntity.ok(null);
	}
}
