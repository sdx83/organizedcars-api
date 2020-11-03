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
	
	@Transactional(readOnly = true)
	public Optional<Gasto> findById(Long id) {
		return gastoDAO.findById(id);
	}

	@Override
	public Gasto save(Gasto gasto) {
		
		return gastoDAO.save(gasto);
	}
	
	@Override
	public Gasto delete(Gasto gasto) {

		return gastoDAO.save(gasto);
	}

	@Override
	public List<Gasto> findByVehiculo(Vehiculo vehiculo) {
		return gastoDAO.findByVehiculo(vehiculo);
	}
}
