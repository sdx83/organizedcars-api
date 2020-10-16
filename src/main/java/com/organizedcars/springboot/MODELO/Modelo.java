package com.organizedcars.springboot.MODELO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="modelos")
public class Modelo implements Serializable  {
	
	private static final long serialVersionUID = -6945635605409394978L;

	@Id
	@Column(name="id_modelo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idModelo;
	
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
}