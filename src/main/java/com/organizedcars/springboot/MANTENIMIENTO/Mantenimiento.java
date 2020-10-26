package com.organizedcars.springboot.MANTENIMIENTO;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.organizedcars.springboot.SERVICIO.Servicio;
import com.organizedcars.springboot.VEHICULO.Vehiculo;


@Entity
@Table(name="mantenimientos")
public class Mantenimiento implements Serializable {


	private static final long serialVersionUID = -6556582692975518845L;

	@Id
	@Column(name="id_mantenimiento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMantenimiento;
	
	@ManyToOne
	@JoinColumn(name="id_vehiculo", nullable=false)
	private Vehiculo vehiculo;
	
	@Column(nullable=true,length=15)
	private String ordenDeTrabajo;
	
	@Column(nullable=false,length=8)
	private String fecha;
	
	@Column(nullable=true,length=5)
	private String hora;
	
	@Column(nullable=false,length=50)
	private String lugar;
	
	@Column(nullable=true,length=20)
	private String nombreTaller;
	
	@Column(nullable=false,length=50)
	private String servicio;
	
	@Column(nullable=false,length=255)
	private String detalleServicio;
	
	@Column(nullable=true,length=20)
	private String garantia;
	
	@Column(nullable=false,scale = 2, precision = 8)
	private BigDecimal presupuesto;
	
	@Column(nullable=true)
	@Lob
	private byte[] adjunto;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "mantenimientos_servicios",
            joinColumns = {
                    @JoinColumn(name = "id_mantenimiento", referencedColumnName = "id_mantenimiento",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "id_servicio", referencedColumnName = "id_servicio",
                            nullable = false, updatable = false)})
	
    private List<Servicio> servicios;
	

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getOrdenDeTrabajo() {
		return ordenDeTrabajo;
	}

	public void setOrdenDeTrabajo(String ordenDeTrabajo) {
		this.ordenDeTrabajo = ordenDeTrabajo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getDetalleServicio() {
		return detalleServicio;
	}

	public void setDetalleServicio(String detalleServicio) {
		this.detalleServicio = detalleServicio;
	}

	public String getGarantia() {
		return garantia;
	}

	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}

	public BigDecimal getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(BigDecimal presupuesto) {
		this.presupuesto = presupuesto;
	}

	public byte[] getAdjunto() {
		return adjunto;
	}

	public void setAdjunto(byte[] adjunto) {
		this.adjunto = adjunto;
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Long getIdMantenimiento() {
		return idMantenimiento;
	}

	public void setIdMantenimiento(Long idMantenimiento) {
		this.idMantenimiento = idMantenimiento;
	}

	public String getNombreTaller() {
		return nombreTaller;
	}

	public void setNombreTaller(String nombreTaller) {
		this.nombreTaller = nombreTaller;
	}
}
