package com.organizedcars.springboot.SERVICIO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="servicios")
public class Servicio implements Serializable  {
	
	private static final long serialVersionUID = 4822987216150150600L;

	@Id
	@Column(name="id_servicio")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idServicio;
	
	@Column(nullable=false,length=20)
	private String nombre;
	
	@Column(nullable=false,length=30)
	private String descripcion;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
}