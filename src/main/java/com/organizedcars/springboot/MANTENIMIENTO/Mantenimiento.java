package com.organizedcars.springboot.MANTENIMIENTO;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.organizedcars.springboot.TIPOSMANTENIMIENTO.TiposMantenimiento;
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
	@JoinColumn(name="id_vehiculo",nullable=false)
	private Vehiculo vehiculo;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_Mantenimiento",nullable=false)
	private TiposMantenimiento tipoMantenimiento;

	@Column(nullable=false,length=15)
	private String ordenDeTrabajo;
	
	@Column(nullable=true,length=8)
	private String fecha;
	
	@Column(nullable=true,length=5)
	private String hora;
	
	@Column(nullable=false,length=50)
	private String lugar;
	
	@Column(nullable=false,length=50)
	private String servicio;
	
	@Column(nullable=false,length=255)
	private String detalleServicio;
	
	@Column(nullable=false,length=20)
	private String garantia;
	
	@Column(nullable=false,scale = 2, precision = 8)
	private BigDecimal presupuesto;
	
	@Column(nullable=false)
	@Lob
	private byte[] adjunto;
	
}
