package com.organizedcars.springboot.GASTO;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

@Entity
@Table(name="gastos")
public class Gasto implements Serializable  {
	
	private static final long serialVersionUID = -94588662499242941L;

	@Id
	@Column(name="id_gasto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGasto;
	
	@ManyToOne
	@JoinColumn(name="id_vehiculo",nullable=false)
	private Vehiculo vehiculo;
	
	@Column(nullable=false,length=30)
	private String descripcion;
	
	@Column(nullable=false,scale = 5, precision = 2)
	private BigDecimal precio;

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

}