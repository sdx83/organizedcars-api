package com.organizedcars.springboot.MANTENIMIENTO;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizedcars.springboot.VEHICULO.Vehiculo;
import com.organizedcars.springboot.VEHICULO.VehiculoServiceImpl;

@RestController
@RequestMapping("/Mantenimientos")
public class MantenimientoController {	
	
    @Autowired
	private VehiculoServiceImpl vehiculoService;
    
    @Autowired
	private MantenimientoServiceImpl mantenimientoService;
	
	// GET: http://localhost:1317/Mantenimientos/{dominio}
 	@RequestMapping(value="/{dominio}")
	public ResponseEntity<List<Mantenimiento>> obtenerMantenimientosPorDominio(@PathVariable("dominio") String dominio) throws Exception{		
 		
 		Optional<Vehiculo> vehiculo = vehiculoService.findByDominio(dominio);
 			
 		if (!vehiculo.isPresent()) {
 			throw new Exception("Vehículo no encontrado");
 		}
 		
 		try {
 			List<Mantenimiento> mantenimientos = mantenimientoService.findByVehiculo(vehiculo.get());
				
 			if(mantenimientos != null && mantenimientos.size() > 0) {
 				return ResponseEntity.ok(mantenimientos);
 	 		}
 			else {
 	 			return ResponseEntity.noContent().build();
 	 		}
		} catch (Exception e) {
			throw new Exception("Error al obtener los mantenimientos");
		}
	}
 	
 	// POST: http://localhost:1317/Mantenimientos
	@PostMapping
	public ResponseEntity<Mantenimiento> altaMantenimiento(@RequestBody Mantenimiento mantenimiento) throws Exception{
		
		try {
			Mantenimiento nuevoMantenimiento = mantenimientoService.save(mantenimiento);
			return ResponseEntity.ok(nuevoMantenimiento);	
		} catch (Exception e) {
			throw new Exception("Error al cargar el mantenimiento");
		}
	}
	
	// DELETE: http://localhost:1317/Mantenimientos/1
	@DeleteMapping(value="/{ot}")
	public ResponseEntity<Void> deleteMantenimiento(@PathVariable("ordenDeTrabajo") String ot) throws Exception{
		
		Optional<Mantenimiento> mantenimiento;
		
		mantenimiento = mantenimientoService.findByOrdeDeTrabajo(ot);
		
		if(!mantenimiento.isPresent()) {
			throw new Exception("No se encontró el mantenimiento");
		}
		
		try {
			mantenimientoService.deleteByID(mantenimiento.get().getIdMantenimiento());
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			throw new Exception("Error al eliminar el mantenimiento");
		}
		
	}
}
