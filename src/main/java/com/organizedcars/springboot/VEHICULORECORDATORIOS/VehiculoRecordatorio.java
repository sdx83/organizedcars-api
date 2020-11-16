package com.organizedcars.springboot.VEHICULORECORDATORIOS;

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

import com.organizedcars.springboot.RECORDATORIO.Recordatorio;
import com.organizedcars.springboot.VEHICULO.Vehiculo;


@Entity
@Table(name="vehiculo_recordatorios")
public class VehiculoRecordatorio implements Serializable {


	private static final long serialVersionUID = 7287136163327418403L;

	@Id
	@Column(name = "id_vehiculo_recordatorio")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVehiculoRecordatorio;
	
	@ManyToOne
	@JoinColumn(name = "id_vehiculo", nullable = false)
	private Vehiculo vehiculo;

	@ManyToOne
	@JoinColumn(name = "id_recordatorio", nullable = false)
	private Recordatorio recordatorio;
	
	@Column(nullable=true, length=8)
	private String fechaRecordatorio;

	@Column(nullable=false)
	private boolean habilitado;	
	
	@Transient
	private int diasRestantesVtoRecordatorio;
	
	public Long getIdVehiculoRecordatorio() {
		return idVehiculoRecordatorio;
	}

	public void setIdVehiculoRecordatorio(Long idVehiculoRecordatorio) {
		this.idVehiculoRecordatorio = idVehiculoRecordatorio;
	}
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Recordatorio getRecordatorio() {
		return recordatorio;
	}

	public void setRecordatorio(Recordatorio recordatorio) {
		this.recordatorio = recordatorio;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getFechaRecordatorio() {
		return fechaRecordatorio;
	}

	public void setFechaRecordatorio(String fechaRecordatorio) {
		this.fechaRecordatorio = fechaRecordatorio;
	}

	public VehiculoRecordatorio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getDiasRestantesVtoRecordatorio() {
		return diasRestantesVtoRecordatorio;
	}
	
	public void setDiasRestantesVtoRecordatorio(int diasRestantesVtoRecordatorio) {
		this.diasRestantesVtoRecordatorio = diasRestantesVtoRecordatorio;
	}
	
	public VehiculoRecordatorio(Vehiculo vehiculo, Recordatorio recordatorio, boolean habilitado) {
		super();
		this.vehiculo = vehiculo;
		this.recordatorio = recordatorio;
		this.habilitado = habilitado;
	}
}
