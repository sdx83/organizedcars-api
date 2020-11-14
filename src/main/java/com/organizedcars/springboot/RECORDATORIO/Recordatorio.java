package com.organizedcars.springboot.RECORDATORIO;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="recordatorios")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Recordatorio implements Serializable  {
	
	private static final long serialVersionUID = -5089927312293978906L;

	@Id
	@Column(name="id_recordatorio")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRecordatorio;
	
	@Column(nullable=false,length=20)
	private String nombre;
	
	@Column(nullable=false,length=30)
	private String descripcion;


}