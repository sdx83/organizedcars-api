package com.organizedcars.springboot.DOCUMENTODIGITAL;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="documentos_digitales")

public class DocumentoDigital implements Serializable  {

	private static final long serialVersionUID = -7154051002112153299L;
	@Id
	@Column(name="id_documento_digital")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDocumentoDigital;

	@ManyToOne
	@JoinColumn(name="id_vehiculo",nullable=false)
	private Vehiculo vehiculo;

	@Column(nullable=false,length=30)
	private String descripcion;

	@Column(nullable=false,columnDefinition="clob")
	@Lob
	private byte[] adjunto;

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

	public byte[] getAdjunto() {
		return adjunto;
	}

	public void setAdjunto(byte[] adjunto) {
		this.adjunto = adjunto;
	}

	public Long getIdDocumentoDigital() {
		return idDocumentoDigital;
	}

	public void setIdDocumentoDigital(Long idDocumentoDigital) {
		this.idDocumentoDigital = idDocumentoDigital;
	}

}


