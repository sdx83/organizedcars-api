package com.organizedcars.springboot.USUARIOVEHICULOS;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.USUARIO.UsuarioServiceImpl;

@RestController
@RequestMapping("/MisVehiculos")
@CrossOrigin(origins = "*")
public class UsuarioVehiculoController {	
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
    @Autowired
	private UsuarioVehiculoServiceImpl usuarioVehiculoService;
	
   	// GET: http://localhost:8080/MisVehiculos/usuario
    @GetMapping(value="/{usuario}")
	public ResponseEntity<List<UsuarioVehiculo>> obtenerMisVehiculos(@PathVariable("usuario") String nombreUsuario) throws Exception{		
 		
 		try {
 	 		Optional<Usuario> usuario;
 	 		List<UsuarioVehiculo> usuarioVehiculos = new ArrayList<UsuarioVehiculo>();
 	 		usuario = usuarioService.findByUsuario(nombreUsuario);

 	 		if(usuario.isPresent()) {
 	 			usuarioVehiculos = usuarioVehiculoService.findByUsuario(usuario.get());
 	 			
 	 			if (usuarioVehiculos != null && usuarioVehiculos.size() > 0) {
 	 				return ResponseEntity.ok(usuarioVehiculos);
 	 			}else {
 	 				throw new Exception("No se pudieron obtener los vehiculos");
 	 			}
 	 		}else { 
 	 			throw new Exception("No se pudieron obtener los vehiculos");
 	 		}
		} catch (NoSuchElementException e) {
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			throw new Exception("No se pudieron obtener los vehiculos");
		}
	}
}
