package com.organizedcars.springboot.RECORDATORIO;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="recordatorios_custom")
@PrimaryKeyJoinColumn(name="id_recordatorio")
public class RecordatorioCustom extends Recordatorio {
	
	private static final long serialVersionUID = 8631778520243305829L;
}