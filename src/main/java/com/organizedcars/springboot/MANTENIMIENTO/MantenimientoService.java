package com.organizedcars.springboot.MANTENIMIENTO;

import java.util.List;
import java.util.Optional;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

public interface MantenimientoService {
	
	
	public Mantenimiento save(Mantenimiento mantenimiento);
	
	public Optional<Mantenimiento> findByOrdeDeTrabajo(String ot);
	
	public List<Mantenimiento> findByVehiculo(Vehiculo vehiculo);

	
}
