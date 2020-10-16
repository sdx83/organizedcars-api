package com.organizedcars.springboot.TIPOSMANTENIMIENTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipos_mantenimiento")
public class TiposMantenimiento implements Serializable  {
	
	private static final long serialVersionUID = 4822987216150150600L;

	@Id
	@Column(name="id_tipo_Mantenimiento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTipoMantenimiento;
	
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