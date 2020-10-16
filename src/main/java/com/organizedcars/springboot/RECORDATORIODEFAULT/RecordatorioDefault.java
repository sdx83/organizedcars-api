package com.organizedcars.springboot.RECORDATORIODEFAULT;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.organizedcars.springboot.RECORDATORIO.Recordatorio;

@Entity
@Table(name="recordatorios_default")
@PrimaryKeyJoinColumn(name="id_recordatorio")
public class RecordatorioDefault extends Recordatorio  {

	private static final long serialVersionUID = 6122725576890098215L;
	
}