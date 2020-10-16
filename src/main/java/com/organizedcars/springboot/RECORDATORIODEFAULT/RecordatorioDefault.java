package com.organizedcars.springboot.RECORDATORIODEFAULT;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="recordatorios_default")
@PrimaryKeyJoinColumn(name="id_recordatorio")
public class RecordatorioDefault implements Serializable  {
	
	private static final long serialVersionUID = -5089927312293978906L;
}