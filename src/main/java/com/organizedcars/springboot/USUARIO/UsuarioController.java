package com.organizedcars.springboot.USUARIO;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.organizedcars.springboot.VALIDACIONES.UsuarioHelper;


@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {	
	
    @Autowired
	private UsuarioServiceImpl usuarioService;
	
   	// GET: http://localhost:8080/Usuarios/user/pass
 	@RequestMapping(value="/{usuario}/{pass}")
	public ResponseEntity<Usuario> getUsuarioByUserAndPass(@PathVariable("usuario") String user,@PathVariable("pass") String pass) throws Exception{		
 		Optional<Usuario> usuario = null;
 		
		usuario = usuarioService.findByUsuarioAndPassword(user, pass);

 		if(usuario.isPresent())
 			return ResponseEntity.ok(usuario.get());
 		
 		else 
 			throw new Exception("usuario y/o password incorrectos");
	}
 	
    //GET: http://localhost:8080/Usuarios/1
  	@RequestMapping(value="/{idUsuario}")
 	public ResponseEntity<Usuario> getUsuarioByID(@PathVariable("idUsuario") Long id){		
 		Optional<Usuario> usuario = usuarioService.findById(id);
 		if(usuario.isPresent()) {
 			return ResponseEntity.ok(usuario.get());
 		}
 		else {
 			return ResponseEntity.noContent().build();
 		}	
 	}
  	
 	
  	// POST: http://localhost:8080/Usuarios
 	@PostMapping
 	public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) throws Exception {

 		Optional<Usuario> usuarioExistente = usuarioService.findByUsuario(usuario.getUsuario().trim());
 		
 		if (usuarioExistente != null && usuarioExistente.isPresent()) {
 			throw new Exception("Usuario existente");
 		}

 		UsuarioHelper.validarUsuario(usuario, AccionUsuario.ALTA);
 		Usuario nuevoUsuario = usuarioService.save(usuario);
 		
 		return ResponseEntity.ok(nuevoUsuario);
 	}
 	  	
     //PUT: http://localhost:8080/Usuarios/1
  	 @RequestMapping(value = "/{idUsuario}", method = RequestMethod.PUT)
     public ResponseEntity<Usuario> actualizarUsuario(@PathVariable("idUsuario") long idUsuario, @RequestBody Usuario nuevoUsuario) throws Exception {
  		
  		 Optional<Usuario> usuarioExistente = usuarioService.findById(idUsuario);
  		
  		if (usuarioExistente != null && usuarioExistente.isPresent()) {

			UsuarioHelper.validarUsuario(nuevoUsuario, AccionUsuario.MODIFICACION);
			Usuario usuario = usuarioService.update(usuarioExistente.get(), nuevoUsuario);
			return ResponseEntity.ok(usuario);
		} else {
			throw new Exception("Usuario no encontrado");
		}

     }
  	 
    // PUT: http://localhost:8080/Usuarios/Eliminar/1
 	@RequestMapping(value = "/Eliminar/{idUsuario}", method = RequestMethod.PUT)
 	public ResponseEntity<Usuario> eliminarUsuario(@PathVariable("idUsuario") long idUsuario) throws Exception {

 		Optional<Usuario> usuario = usuarioService.findById(idUsuario);

 		if (usuario != null && usuario.isPresent()) {
 			Usuario usuarioEliminado = usuarioService.delete(usuario.get());
 			return ResponseEntity.ok(usuarioEliminado);
 		} else {
 			throw new Exception("Usuario no encontrado");
 		}
 	}
 	
 // PUT: http://localhost:8080/Usuarios/Habilitar/1
 	@RequestMapping(value = "/Habilitar/{idUsuario}", method = RequestMethod.PUT)
 	public ResponseEntity<Usuario> habilitarUsuario(@PathVariable("idUsuario") long idUsuario) throws Exception {

 		Optional<Usuario> usuario = usuarioService.findById(idUsuario);

 		if (usuario != null && usuario.isPresent()) {
 			Usuario usuarioHabilitado = usuarioService.activate(usuario.get());
 			return ResponseEntity.ok(usuarioHabilitado);
 		} else {
 			throw new Exception("Usuario no encontrado");
 		}
 	}
}
