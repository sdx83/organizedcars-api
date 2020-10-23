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

import com.organizedcars.springboot.USUARIOVEHICULOS.UsuarioVehiculo;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable  {
	
	
	private static final long serialVersionUID = -3359031128100969764L;

	@Id
	@Column(name="id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	
	@OneToMany(mappedBy = "vehiculo")
    private List<UsuarioVehiculo> vehiculos;
	
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

	@Column(nullable=false, length=5)
	private String tipoDocumento;
	
	@Column(nullable=false, length=15)
	private String nroDocumento;
	
	@Column(nullable=false, length=50)
	private String calle;
	
	@Column(nullable=false, length=50)
	private String nroCalle;
	
	@Column(nullable=false, length=50)
	private String codigoPostal;
	
	@Column(nullable=false, length=50)
	private String localidad;
	
	@Column(nullable=false, length=50)
	private String provincia;
	
	@Column(nullable=false, length=50)
	private String pais;
	
	
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
	
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<UsuarioVehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<UsuarioVehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public String getNroCalle() {
		return nroCalle;
	}

	public void setNroCalle(String nroCalle) {
		this.nroCalle = nroCalle;
	}

}