package com.organizedcars.springboot.USUARIO;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.organizedcars.springboot.VALIDACIONES.UsuarioHelper;


@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {	
	
    @Autowired
	private UsuarioServiceImpl usuarioService;
	
   	// GET: http://localhost:8080/Usuarios/user/pass
 	@RequestMapping(value="/{usuario}/{pass}")
	public ResponseEntity<Usuario> getUsuarioByUserAndPass(@PathVariable("usuario") String user,@PathVariable("pass") String pass) throws Exception{		
 		
 		try {
 			Optional<Usuario> usuario;
 			usuario = usuarioService.findByUsuarioAndPassword(user, pass);

 			if(usuario.isPresent()) {
 	 			return ResponseEntity.ok(usuario.get());
 	 		}else { 
 	 			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuario y/o Password incorrectos");
 	 		}
 		} catch (ResponseStatusException e) {
			throw new Exception(e.getReason());
		} catch (Exception e) {
			throw new Exception("Error inesperado");
		}
	}
 	
    //GET: http://localhost:8080/Usuarios/1
  	@RequestMapping(value="/{idUsuario}")
 	public ResponseEntity<Usuario> getUsuarioByID(@PathVariable("idUsuario") Long id) throws Exception{	
  		
  		try {
  			Optional<Usuario> usuario = usuarioService.findById(id);
  	 		if(usuario.isPresent()) {
  	 			return ResponseEntity.ok(usuario.get());
  	 		}
  	 		else {
  	 			return ResponseEntity.noContent().build();
  	 		}
		} catch (Exception e) {
			throw new Exception("Error al obtener el usuario");
		}
 	}
  	
 	
  	// POST: http://localhost:8080/Usuarios
 	@PostMapping
 	public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) throws Exception {

 		Optional<Usuario> usuarioExistente = usuarioService.findByUsuario(usuario.getUsuario().trim());
 	 		
 		if (usuarioExistente != null && usuarioExistente.isPresent()) {
 			throw new Exception("Usuario existente");
 		}

 		try {
 			UsuarioHelper.validarUsuario(usuario, AccionUsuario.ALTA);
 			Usuario nuevoUsuario = usuarioService.save(usuario);
 	 		
 			return ResponseEntity.ok(nuevoUsuario);
 	 	} catch (Exception e) {
 	 		throw new Exception("Error al crear el usuario");
 	 	}
 	}
 	  	
     //PUT: http://localhost:8080/Usuarios/1
  	 @RequestMapping(value = "/{idUsuario}", method = RequestMethod.PUT)
     public ResponseEntity<Usuario> actualizarUsuario(@PathVariable("idUsuario") long idUsuario,
    		 															@RequestBody Usuario nuevoUsuario) throws Exception {
  		
  		Optional<Usuario> usuarioExistente = usuarioService.findById(idUsuario);
  		if (!usuarioExistente.isPresent()) {
  			throw new Exception("Usuario no encontrado");
  		}
  		
  		try {
  			UsuarioHelper.validarUsuario(nuevoUsuario, AccionUsuario.MODIFICACION);
			Usuario usuario = usuarioService.update(usuarioExistente.get(), nuevoUsuario);
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			throw new Exception("Error al actualizar el usuario");
		}
     }
  	 
    // PUT: http://localhost:8080/Usuarios/Eliminar/1
 	@RequestMapping(value = "/Eliminar/{idUsuario}", method = RequestMethod.PUT)
 	public ResponseEntity<Usuario> eliminarUsuario(@PathVariable("idUsuario") long idUsuario) throws Exception {

 		Optional<Usuario> usuario = usuarioService.findById(idUsuario);

 		if (!usuario.isPresent()) {
 			throw new Exception("Usuario no encontrado");
 		}
 		
 		try {
 			Usuario usuarioEliminado = usuarioService.delete(usuario.get());
 			return ResponseEntity.ok(usuarioEliminado);
		} catch (Exception e) {
			throw new Exception("Error al eliminar el usuario");
		}
 	}
}
