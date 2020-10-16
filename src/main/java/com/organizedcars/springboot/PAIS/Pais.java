package com.organizedcars.springboot.PAIS;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="paises")
public class Pais implements Serializable  {
	

	private static final long serialVersionUID = -6119682356697648946L;

	@Id
	@Column(name="id_pais")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPais;
	
	@Column(nullable=false,length=20)
	private String nombre;
	
	@Column(nullable=false,length=5)
	private String sigla;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
}