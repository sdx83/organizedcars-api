package com.organizedcars.springboot.VEHICULO;
import java.io.Serializable;
import java.math.BigDecimal;

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
	
	@Column(nullable=false,length=20)
	private String modelo;
	
	@Column(nullable=true,length=20)
	private String nroMotor;
	
	@Column(nullable=true,length=20)
	private String marcaChasis;
	
	@Column(nullable=true,length=20)
	private String nroChasis;
	
	@Column(nullable=true,length=4)
	private String anioFabricacion;
	
	@Column(nullable=false,length=4)
	private String anioModelo;
	
	@Column(nullable=false, length=8)
	private String fechaAdquisicion;
	
	@Column(nullable=true,length=20)
	private String carroceria;
	
	@Column(nullable=true,scale = 3, precision = 2)
	private BigDecimal peso;
	
	@Column(nullable=true,length=20)
	private String nroOblea;
	
	@Column(nullable=true, length=8)
	private String vtoOblea;
	

	@Column(nullable=true,length=50)
	private String usuarioVehicuclo;
	
	@Column(nullable=true,length=50)
	private String ciaSeguro;
	
	@Column(nullable=true,length=50)
	private String coberturaSeguro;
	
	@Column(nullable=true,length=255)
	private String descripcionVehiculo;
	
	
	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMarcaMotor() {
		return marcaMotor;
	}

	public void setMarcaMotor(String marcaMotor) {
		this.marcaMotor = marcaMotor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getNroMotor() {
		return nroMotor;
	}

	public void setNroMotor(String nroMotor) {
		this.nroMotor = nroMotor;
	}

	public String getMarcaChasis() {
		return marcaChasis;
	}

	public void setMarcaChasis(String marcaChasis) {
		this.marcaChasis = marcaChasis;
	}

	public String getNroChasis() {
		return nroChasis;
	}

	public void setNroChasis(String nroChasis) {
		this.nroChasis = nroChasis;
	}

	public String getAnioFabricacion() {
		return anioFabricacion;
	}

	public void setAnioFabricacion(String anioFabricacion) {
		this.anioFabricacion = anioFabricacion;
	}

	public String getAnioModelo() {
		return anioModelo;
	}

	public void setAnioModelo(String anioModelo) {
		this.anioModelo = anioModelo;
	}

	public String getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public void setFechaAdquisicion(String fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public String getCarroceria() {
		return carroceria;
	}

	public void setCarroceria(String carroceria) {
		this.carroceria = carroceria;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public String getNroOblea() {
		return nroOblea;
	}

	public void setNroOblea(String nroOblea) {
		this.nroOblea = nroOblea;
	}

	public String getVtoOblea() {
		return vtoOblea;
	}

	public void setVtoOblea(String vtoOblea) {
		this.vtoOblea = vtoOblea;
	}

	public String getUsuarioVehicuclo() {
		return usuarioVehicuclo;
	}

	public void setUsuarioVehicuclo(String usuarioVehicuclo) {
		this.usuarioVehicuclo = usuarioVehicuclo;
	}

	public String getCiaSeguro() {
		return ciaSeguro;
	}

	public void setCiaSeguro(String ciaSeguro) {
		this.ciaSeguro = ciaSeguro;
	}

	public String getCoberturaSeguro() {
		return coberturaSeguro;
	}

	public void setCoberturaSeguro(String coberturaSeguro) {
		this.coberturaSeguro = coberturaSeguro;
	}

	public String getDescripcionVehiculo() {
		return descripcionVehiculo;
	}

	public void setDescripcionVehiculo(String descripcionVehiculo) {
		this.descripcionVehiculo = descripcionVehiculo;
	}
	
}
