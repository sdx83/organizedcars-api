package com.organizedcars.springboot.USUARIOVEHICULOS;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.VEHICULO.Vehiculo;


@Entity
@Table(name="usuario_vehiculos")
public class UsuarioVehiculos implements Serializable {

	private static final long serialVersionUID = -1589703704023149532L;

	@Id
	@Column(name = "id_usuario_vehiculo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuarioVehiculo;
	
	
	@OneToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "id_vehiculo", nullable = false)
	private Vehiculo vehiculo;
	
}
