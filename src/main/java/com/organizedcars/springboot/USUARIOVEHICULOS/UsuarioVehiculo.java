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

import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.VEHICULO.Vehiculo;


@Entity
@Table(name="usuario_vehiculos")
public class UsuarioVehiculo implements Serializable {

	private static final long serialVersionUID = -1589703704023149532L;

	@Id
	@Column(name = "id_usuario_vehiculo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuarioVehiculo;
	
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_vehiculo", nullable = false)
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
		return this.getDominio();
	}

	public String getTipo() {
		return this.getTipo();
	}

	public String getMarca() {
		return this.getMarca();
	}

	public String getModelo() {
		return this.getModelo();
	}

	public String getPais() {
		return this.getPais();
	}

	public String getAnio() {
		return this.getAnio();
	}
	
}
