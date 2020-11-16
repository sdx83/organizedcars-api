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
import org.springframework.web.bind.annotation.RequestMethod;
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
    
	// GET: http://localhost:8080/Vehiculos/{dominio}
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
 	
 	// POST: http://localhost:8080/Vehiculos/user
	@PostMapping(value="/{usuario}")
	public ResponseEntity<Vehiculo> altaVehiculo(@PathVariable("usuario") String user,
															@RequestBody Vehiculo vehiculo) throws Exception{
		
		try {
			Optional<Usuario> usuario = usuarioService.findByUsuario(user);
			Optional<Vehiculo> vehiculoDB = vehiculoService.findByDominio(vehiculo.getDominio().trim());
			
			if(!usuario.isPresent()) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Usuario inexistente");
			}
			if (vehiculoDB.isPresent()) {
					throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Ya existe un vehículo con el dominio ingresado");
			}
			Vehiculo nuevoVehiculo = vehiculoService.save(vehiculo, usuario.get());
			return ResponseEntity.ok(nuevoVehiculo);
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getReason());
		} catch (Exception e) {
			throw new Exception("Error el cargar el vehículo");
		}
	}
	
	// DELETE: http://localhost:8080/Vehiculos/1
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
	
    //PUT: http://localhost:8080/Vehiculos/1
 	@RequestMapping(value = "/{dominio}", method = RequestMethod.PUT)
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable("dominio") String dominio,
   		 															@RequestBody Vehiculo nuevoVehiculo) throws Exception {
 		
 		try {
 			Optional<Vehiculo> vehiculoExistente = vehiculoService.findByDominio(dominio.trim());
 			if (!vehiculoExistente.isPresent()) {
 				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Vehiculo no encontrado");
 			}
 			return ResponseEntity.ok(vehiculoService.update(vehiculoExistente.get(), nuevoVehiculo));
 		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getReason());
 		} catch (Exception e) {
			throw new Exception("Error al actualizar el vehiculo");
		}
    }
}
