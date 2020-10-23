package com.organizedcars.springboot.VALIDACIONES;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import com.organizedcars.springboot.USUARIO.AccionUsuario;
import com.organizedcars.springboot.USUARIO.Usuario;

public class UsuarioHelper {

	public static void validarUsuario(Usuario usuario, AccionUsuario accion) throws Exception{
		
		//Validaciones a realizar

		String rg_length = ".{8,15}";
		String rg_email = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		String rg_tel = "^[0-9]+$";
		
		//Validaciones de nombre y apellido
		if(usuario.getNombre().trim().equals(""))
			throw new Exception("El nombre del usuario no puede estar vacío");
		
		if(usuario.getNombre().trim().length() > 15)
			throw new Exception("El nombre del usuario no puede contener más de 15 caracteres");

		if(usuario.getApellido().trim().equals(""))
			throw new Exception("El apellido del usuario no puede estar vacío");
		
		if(usuario.getApellido().trim().length() > 15)
			throw new Exception("El apellido del usuario no puede contener más de 15 caracteres");
		
		if (accion.equals(AccionUsuario.ALTA)) {
			if(usuario.getUsuario().trim().equals(""))
				throw new Exception("El usuario no puede estar vacío");
			
			if(usuario.getUsuario().trim().length() > 10)
				throw new Exception("El usuario no puede contener más de 10 caracteres");
		}
		
		//Validaciones password	
		if( usuario.getPassword().equals(""))
			throw new Exception("La password del usuario no puede estar vacía");
		
		
		if (!Pattern.matches(rg_length, usuario.getPassword())) {
			throw new Exception("El password debe tener al menos 8 caracteres y como máximo 15");
		}				
		
		//Validaciones email
		if(usuario.getMail().trim().length() > 30)
			throw new Exception("El mail del usuario no puede contener más de 30 caracteres");
		
		if( !Pattern.matches(rg_email, usuario.getMail()))
			throw new Exception("El email tiene un formato inválido");
		
		//Validaciones tel
		if( !Pattern.matches(rg_tel, usuario.getTelefono()))
			throw new Exception("El teléfono tiene un formato inválido");
		
		if( usuario.getTelefono().trim().length() < 8 || usuario.getTelefono().trim().length() > 20)
			throw new Exception("El teléfono debe contener entre 8 y 20 caracteres sin guiones u otro caracter");	
	
	}
	
	public static String convertirFechaAFormatoJapones(Date fecha) {
		
		return new SimpleDateFormat("yyyyMMdd").format(fecha);
	}
}
		