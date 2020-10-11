package com.organizedcars.springboot.USUARIO;

import java.util.Optional;

public interface UsuarioService {
	
	public Usuario save(Usuario usuario);
	
	Optional<Usuario> findByUsuarioAndPassword(String usuario,String password);
	
}
