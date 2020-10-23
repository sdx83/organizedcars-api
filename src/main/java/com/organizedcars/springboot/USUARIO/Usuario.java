package com.organizedcars.springboot.USUARIO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.organizedcars.springboot.USUARIOVEHICULOS.UsuarioVehiculo;

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
	
	@Column(nullable=true, length=20)
	private String telefono;
	
	@Column(nullable = false)
	private boolean enable;

	@Column(nullable=false, length=15)
	private String nroDocumento;
	
	@Column(nullable=true, length=100)
	private String direccion;
	
	@Column(nullable=true, length=50)
	private String localidad;
	
	@Column(nullable=true, length=50)
	private String provincia;
	
	@OneToMany(mappedBy = "vehiculo")
	@JsonIgnore
    private List<UsuarioVehiculo> vehiculos;
	

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
	
	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public List<UsuarioVehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<UsuarioVehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}