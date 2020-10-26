package com.organizedcars.springboot.SERVICIO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class ServicioServiceImpl implements ServicioService {
	
	@Autowired
	ServicioDAO servicioDAO;
	
	@Transactional(readOnly = true)
	public List<Servicio> findAll() {
		return servicioDAO.findAll();
	}
}
