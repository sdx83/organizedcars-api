package com.organizedcars.springboot.VALIDACIONES;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.organizedcars.springboot.USUARIO.AccionUsuario;
import com.organizedcars.springboot.USUARIO.Usuario;

public class UsuarioHelper {

	public static void validarUsuario(Usuario usuario, AccionUsuario accion) throws Exception{
		
		//Validaciones a realizar

		String rg_email = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		String rg_tel = "^[0-9]+$";
		
		//Validaciones de nombre y apellido
		if(usuario.getNombre().trim().equals(""))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El nombre del usuario no puede estar vacío");
		
		if(usuario.getNombre().trim().length() > 15)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,"El nombre del usuario no puede contener más de 15 caracteres");

		if(usuario.getApellido().trim().equals(""))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,"El apellido del usuario no puede estar vacío");
		
	
		if (accion.equals(AccionUsuario.ALTA)) {
			if(usuario.getUsuario().trim().equals(""))
				throw new ResponseStatusException(HttpStatus.FORBIDDEN,"El usuario no puede estar vacío");
			
			if(usuario.getUsuario().trim().length() > 10)
				throw new ResponseStatusException(HttpStatus.FORBIDDEN,"El usuario no puede contener más de 10 caracteres");
		}
		
		//Validaciones password	
		if( usuario.getPassword().equals(""))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,"La password del usuario no puede estar vacía");
		
		
		//Validaciones email
		if(usuario.getMail().trim().length() > 30)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,"El mail del usuario no puede contener más de 30 caracteres");
		
		if( !Pattern.matches(rg_email, usuario.getMail()))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,"El email tiene un formato inválido");
		
		//Validaciones tel
		if( !Pattern.matches(rg_tel, usuario.getTelefono()))
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,"El teléfono tiene un formato inválido");
		
		if(usuario.getTelefono().trim().length() > 20)
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,"El teléfono debe contener menos de 20 caracteres sin guiones u otro caracter");	
	
	}
	
	public static String convertirFechaAFormatoJapones(Date fecha) {
		
		return new SimpleDateFormat("yyyyMMdd").format(fecha);
	}
}
		