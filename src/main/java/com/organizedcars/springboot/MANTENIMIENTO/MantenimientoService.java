package com.organizedcars.springboot.MANTENIMIENTO;

import java.util.List;

import com.organizedcars.springboot.USUARIO.Usuario;
import com.organizedcars.springboot.VEHICULO.Vehiculo;

public interface MantenimientoService {
	
	
	public Mantenimiento save(Mantenimiento mantenimiento);
	
	public List<Mantenimiento> findByVehiculo(Vehiculo vehiculo);
	
	public List<Mantenimiento> findByUsuario(Usuario usuario);
	
}
