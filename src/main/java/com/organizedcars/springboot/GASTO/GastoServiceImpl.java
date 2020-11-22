package com.organizedcars.springboot.GASTO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.organizedcars.springboot.VEHICULO.Vehiculo;

@Service
@Transactional(readOnly = false)
public class GastoServiceImpl implements GastoService {
	
	@Autowired
	GastoDAO gastoDAO;
	
	@Autowired
	TotalCategoriasDAO tcDAO;
	
	@Transactional(readOnly = true)
	public Optional<Gasto> findById(Long id) {
		return gastoDAO.findById(id);
	}

	@Override
	public Gasto save(Gasto gasto) {
		
		return gastoDAO.save(gasto);
	}
	
	@Override
	public Void delete(Gasto gasto) {
		gastoDAO.delete(gasto);
		return null;
	}

	@Override
	public List<Gasto> findByVehiculo(Vehiculo vehiculo) {
		return gastoDAO.findByVehiculo(vehiculo);
	}

	@Override
	public List<TotalGastosPorCategoria> obtenerGastosAgrupadosPorCategoria(Long idVehiculo) {
		return tcDAO.obtenerGastosAgrupadosPorCategoria(idVehiculo);
	}
	
	@Override
	public List<TotalGastosPorCategoria> obtenerGastosAgrupadosPorCategoriaDelUltimoAnio(Long idVehiculo) {
		return tcDAO.obtenerGastosAgrupadosPorCategoriaDelUltimoAnio(idVehiculo);
	}
}
