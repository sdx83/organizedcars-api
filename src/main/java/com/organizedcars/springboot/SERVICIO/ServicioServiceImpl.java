package com.organizedcars.springboot.SERVICIO;

import java.util.Collections;
import java.util.Comparator;
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
		
		List<Servicio> servicios = servicioDAO.findAll();
		
		//ordeno los mantenimientos por dominio y fecha
 		Comparator<Servicio> comparadorServicios = (Servicio s1, Servicio s2) -> {
 			return (s1.getNombre()).compareTo((s2.getNombre()));
 		};
 		
 		Collections.sort(servicios, comparadorServicios);
		 
		return servicios ;
	}
}
