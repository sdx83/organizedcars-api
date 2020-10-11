package com.organizedcars.springboot.USUARIO;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Usuarios")
public class UsuarioREST {	
	
    @Autowired
	private UsuarioServiceImpl usuarioService;
	
   	// GET: http://localhost:1317/Usuarios/user/pass/tipo
 	@RequestMapping(value="/{usuario}/{pass}/{tipo}")
	public ResponseEntity<Usuario> getUsuarioByUserAndPass(@PathVariable("usuario") String user,@PathVariable("pass") String pass){		
 		Optional<Usuario> usuario = null;
 		

		usuario = usuarioService.findByUsuarioAndPassword(user, pass);

 		if(usuario.isPresent())
 			return ResponseEntity.ok(usuario.get());
 		
 		else 
 			return ResponseEntity.noContent().build();
	}
 	
    //GET: http://localhost:1317/Usuarios/1
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
  	
     //PUT: http://localhost:1317/Usuarios/1
  	 @RequestMapping(value = "/{idUsuario}", method = RequestMethod.PUT)
     public ResponseEntity<Usuario> actualizarUsuario(@PathVariable("idUsuario") long idUsuario, @RequestBody Usuario nuevoUsuario) {
  		
  		 Optional<Usuario> usuario = usuarioService.findById(idUsuario);
  		 if(usuario.isPresent()) {
  			usuario.get().setMail(nuevoUsuario.getMail().trim());
  			usuario.get().setTelefono(nuevoUsuario.getTelefono().trim());
  			return ResponseEntity.ok(usuarioService.save(usuario.get()));
  		 }
  		 else {
 			return ResponseEntity.noContent().build();
  		 }	
     }
}
