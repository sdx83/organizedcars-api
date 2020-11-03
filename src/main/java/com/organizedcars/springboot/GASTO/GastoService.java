package com.organizedcars.springboot.GASTO;

import java.util.List;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

public interface GastoService {
	
	public Gasto save(Gasto gasto);
	
	public Gasto delete(Gasto gasto);
	
	public List<Gasto> findByVehiculo(Vehiculo vehiculo);
	
	
}
