package com.organizedcars.springboot.MANTENIMIENTO;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.USUARIO.UsuarioServiceImpl;
import com.organizedcars.springboot.VEHICULO.Vehiculo;
import com.organizedcars.springboot.VEHICULO.VehiculoServiceImpl;

@RestController
@RequestMapping("/Mantenimientos")
@CrossOrigin(origins = "*")
public class MantenimientoController {	
	
    @Autowired
	private UsuarioServiceImpl usuarioService;
    
    @Autowired
	private VehiculoServiceImpl vehiculoService;
    
    @Autowired
	private MantenimientoServiceImpl mantenimientoService;
	
    
	// GET: http://localhost:1317/Mantenimientos/Vehiculos/{dominio}
    @GetMapping(value="/Vehiculos/{dominio}")
	public ResponseEntity<List<Mantenimiento>> obtenerMantenimientosPorDominio(@PathVariable("dominio") String dominio) throws Exception{		
 		
 		try {
 			Optional<Vehiculo> vehiculo = vehiculoService.findByDominio(dominio);
 			
 			if (!vehiculo.isPresent()) {
 				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehículo no encontrado");
 			}
 		
 			List<Mantenimiento> mantenimientos = mantenimientoService.findByVehiculo(vehiculo.get());
				
 			if(mantenimientos != null && mantenimientos.size() > 0) {
 				return ResponseEntity.ok(mantenimientos);
 	 		}
 			else {
 	 			return ResponseEntity.noContent().build();
 	 		}
 		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getReason());
		} catch (Exception e) {
			throw new Exception("Error al obtener los mantenimientos");
		}
	}
 	
	// GET: http://localhost:1317/Mantenimientos/Usuarios/{usuario}
    @GetMapping(value="/Usuarios/{usuario}")
	public ResponseEntity<List<Mantenimiento>> obtenerMantenimientosPorUsuario(@PathVariable("usuario") String nombreUsuario) throws Exception{		
 		
 		try {
 			Optional<Usuario> usuario = usuarioService.findByUsuario(nombreUsuario);
 			
 			if (!usuario.isPresent()) {
 				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
 			}
 		
 			List<Mantenimiento> mantenimientos = mantenimientoService.findByUsuario(usuario.get());
 			
 			if(mantenimientos != null && mantenimientos.size() > 0) {
 				return ResponseEntity.ok(mantenimientos);
 	 		}
 			else {
 	 			return ResponseEntity.noContent().build();
 	 		}
 		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getReason());
		} catch (Exception e) {
			throw new Exception("Error al obtener los mantenimientos");
		}
	}
 	
 	
	// GET: http://localhost:1317/Mantenimientos/{id}
    @GetMapping(value="/{idMantenimiento}")
	public ResponseEntity<Mantenimiento> obtenerMantenimientoPorID(@PathVariable("idMantenimiento") Long id) throws Exception{		
 		
 		try {
 			Optional<Mantenimiento> mantenimiento = mantenimientoService.findById(id);

 			if (mantenimiento.isPresent()) {
 				return ResponseEntity.ok(mantenimiento.get());
 			}else {
 				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado el mantenimiento");
 			}
 		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getReason());
		} catch (Exception e) {
			throw new Exception("Error al obtener el mantenimiento");
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
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deleteMantenimiento(@PathVariable("id") Long id) throws Exception{
		
		Optional<Mantenimiento> mantenimiento;
		
		mantenimiento = mantenimientoService.findById(id);
		
		if(!mantenimiento.isPresent()) {
			throw new Exception("No se encontró el mantenimiento");
		}
		
		try {
			mantenimientoService.deleteByID(id);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			throw new Exception("Error al eliminar el mantenimiento");
		}
		
	}
}
