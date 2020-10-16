package com.organizedcars.springboot.RECORDATORIOCUSTOM;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.organizedcars.springboot.RECORDATORIO.Recordatorio;

@Entity
@Table(name="recordatorios_custom")
@PrimaryKeyJoinColumn(name="id_recordatorio")
public class RecordatorioCustom extends Recordatorio {
	
	private static final long serialVersionUID = 8631778520243305829L;
}