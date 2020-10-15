package com.organizedcars.springboot.USUARIO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable  {
	
	
	private static final long serialVersionUID = -3359031128100969764L;

	@Id
	@Column(name="id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	
	@Column(nullable=false,length=15)
	private String nombre;
	
	@Column(nullable=false,length=15)
	private String apellido;
	
	@Column(nullable=false, length=20, unique=true)
	
	private String usuario;
	
	@Column(nullable=false, length=20)
	private String password;
	
	@Column(nullable=false, length=30)
	private String mail;
	

	@Column(nullable=false, length=8)
	private String fecha_nacimiento;
	
	@Column(nullable=false, length=20)
	private String telefono;
	
	@Column(nullable = false)
	private boolean enable;
	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	public Usuario() {
    }
	
    public Usuario(String nombre,String apellido, String usuario, String pass, String mail,String sexo, String fechaNac, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.password = pass;
        this.mail = mail;
        this.fecha_nacimiento= fechaNac;
        this.telefono= telefono;
    }
}