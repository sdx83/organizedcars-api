package com.organizedcars.springboot.VEHICULO;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="vehiculos")
public class Vehiculo implements Serializable {

	private static final long serialVersionUID = 5048310680793495937L;

	@Id
	@Column(name="id_vehiculo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVehiculo;
	
	@Column(name="dominio", nullable=false, length=10, unique=true)
	private String dominio;
	
	@Column(nullable=false,length=15)
	private String marca;
	
	@Column(nullable=true,length=15)
	private String marcaMotor;
	
	@Column(nullable=true,length=15)
	private String tipo;
	
	@Column(nullable=false,length=15)
	private String modelo;
	
	@Column(nullable=true,length=15)
	private String nroMotor;
	
	@Column(nullable=true,length=15)
	private String marcaChasis;
	
	@Column(nullable=true,length=15)
	private String nroChasis;
	
	@Column(nullable=false,length=4)
	private String anioFabricacion;
	
	@Column(nullable=false,length=4)
	private String anioModelo;
	
	@Column(nullable=false, length=8)
	private String fechaAdquisicion;
	
	@Column(nullable=false,length=15)
	private String carroceria;
	
	@Column(nullable=true,length=15)
	private String peso;
	
	@Column(nullable=true,length=15)
	private String nroOblea;
	
	@Column(nullable=true, length=8)
	private String vtoOblea;
	
}
