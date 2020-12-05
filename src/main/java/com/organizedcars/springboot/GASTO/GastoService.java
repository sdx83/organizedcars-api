package com.organizedcars.springboot.GASTO;

import java.util.List;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

public interface GastoService {
	
	public Gasto save(Gasto gasto);
	
	public Void delete(Gasto gasto);
	
	public List<Gasto> findByVehiculo(Vehiculo vehiculo);
	
	public List<TotalGastosPorCategoria> obtenerGastosAgrupadosPorCategoria(Long idVehiculo);
	
	public List<TotalGastosPorCategoria> obtenerGastosAgrupadosPorCategoriaDelUltimoAnio(Long idVehiculo);
	
}
