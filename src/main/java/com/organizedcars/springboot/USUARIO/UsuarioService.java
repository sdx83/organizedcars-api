package com.organizedcars.springboot.USUARIO;

import java.util.Optional;

public interface UsuarioService {
	
	public Usuario save(Usuario usuario);
	
	Optional<Usuario> findByUsuarioAndPassword(String usuario,String password);
	
	Optional<Usuario> findByUsuario(String usuario);
	
	public Usuario update(Usuario usuarioExistente, Usuario usuarioModificado);
	
	public Usuario delete(Usuario usuario);
	
	public Usuario activate(Usuario usuario);
	
}
