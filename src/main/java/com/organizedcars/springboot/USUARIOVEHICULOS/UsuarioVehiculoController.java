package com.organizedcars.springboot.USUARIOVEHICULOS;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.USUARIO.UsuarioServiceImpl;
import com.organizedcars.springboot.VEHICULO.Vehiculo;


@RestController
@RequestMapping("/MisVehiculos")
public class UsuarioVehiculoController {	
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
    @Autowired
	private UsuarioVehiculoServiceImpl usuarioVehiculoService;
	
   	// GET: http://localhost:8080/MisVehiculos/usuario
 	@RequestMapping(value="/{usuario}")
	public ResponseEntity<List<Vehiculo>> obtenerMisVehiculos(@PathVariable("usuario") String nombreUsuario) throws Exception{		
 		
 		try {
 	 		Optional<Usuario> usuario;
 	 		List<UsuarioVehiculo> usuarioVehiculos = new ArrayList<UsuarioVehiculo>();
 	 		List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
 	 		
 			usuario = usuarioService.findByUsuario(nombreUsuario);

 	 		if(usuario.isPresent()) {
 	 			usuarioVehiculos = usuarioVehiculoService.findByUsuario(usuario.get()).get();
 	 			if (usuarioVehiculos != null && usuarioVehiculos.size() > 0) {
 	 				for (UsuarioVehiculo usuarioVehiculo : usuarioVehiculos) {
 	 					vehiculos.add(usuarioVehiculo.getVehiculo());
 	 				}
 	 	 			return ResponseEntity.ok(vehiculos);
 	 			}else {
 	 				throw new Exception("No se pudieron obtener los vehiculos");
 	 			}
 	 		}else { 
 	 			throw new Exception("No se pudieron obtener los vehiculos");
 	 		}
		} catch (Exception e) {
			throw new Exception("No se pudieron obtener los vehiculos");
		}
	}
}
