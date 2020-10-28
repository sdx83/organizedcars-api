package com.organizedcars.springboot.VEHICULO;
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

@RestController
@RequestMapping("/Vehiculos")
@CrossOrigin(origins = "*")
public class VehiculoController {	
	
    @Autowired
	private VehiculoServiceImpl vehiculoService;
    
    @Autowired
	private UsuarioServiceImpl usuarioService;
	
	// GET: http://localhost:1317/Vehiculos/{dominio}
    @GetMapping(value="/{dominio}")
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
 	
 	// POST: http://localhost:1317/Vehiculos/user
	@PostMapping(value="/{usuario}")
	public ResponseEntity<Vehiculo> altaVehiculo(@PathVariable("usuario") String user,
															@RequestBody Vehiculo vehiculo) throws Exception{
		
		try {
			Optional<Usuario> usuario = usuarioService.findByUsuario(user);
			Optional<Vehiculo> v = vehiculoService.findByDominio(vehiculo.getDominio().trim());
			
			if (v.isPresent()) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Ya existe un vehículo con el mismo dominio");
			}
			
			if(usuario.isPresent()) {
				Vehiculo nuevoVehiculo = vehiculoService.save(vehiculo, usuario.get());
				return ResponseEntity.ok(nuevoVehiculo);	
			}else {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Usuario inexistente");
			}
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getReason());
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
