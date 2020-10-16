package com.organizedcars.springboot.MARCA;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.organizedcars.springboot.MODELO.Modelo;

@Entity
@Table(name="marcas")
public class Marca implements Serializable  {
	
	private static final long serialVersionUID = -94588662499242941L;

	@Id
	@Column(name="id_marca")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMarca;
	
	@Column(nullable=false,length=20)
	private String nombre;
	
	@Column(nullable=false,length=30)
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name="id_modelo",nullable=false)
	private Modelo modelo;

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

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
}