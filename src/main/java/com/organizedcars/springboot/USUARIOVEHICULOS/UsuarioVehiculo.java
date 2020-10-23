package com.organizedcars.springboot.USUARIOVEHICULOS;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.VEHICULO.Vehiculo;


@Entity
@Table(name="usuario_vehiculos",uniqueConstraints={
	    @UniqueConstraint(columnNames = {"id_usuario", "id_vehiculo"})

	}) 
public class UsuarioVehiculo implements Serializable {

	private static final long serialVersionUID = -1589703704023149532L;

	@Id
	@Column(name = "id_usuario_vehiculo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuarioVehiculo;
	
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	@JsonIgnore
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_vehiculo", nullable = false)
	@JsonIgnore
	private Vehiculo vehiculo;
	
	@Transient
	private String dominio;
	
	@Transient
	private String tipo;
	
	@Transient
	private String marca;
	
	@Transient
	private String modelo;
	
	@Transient
	private String pais;
	
	@Transient
	private String anio;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getDominio() {
		return this.getVehiculo().getDominio();
	}

	public String getTipo() {
		return this.getVehiculo().getTipo();
	}

	public String getMarca() {
		return this.getVehiculo().getMarca();
	}

	public String getModelo() {
		return this.getVehiculo().getModelo();
	}

	public String getPais() {
		return this.getVehiculo().getPais();
	}

	public String getAnio() {
		return this.getVehiculo().getAnioFabricacion();
	}
	
}
