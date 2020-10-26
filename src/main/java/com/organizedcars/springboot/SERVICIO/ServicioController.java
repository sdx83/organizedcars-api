package com.organizedcars.springboot.SERVICIO;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Servicios")
@CrossOrigin(origins = "*")
public class ServicioController {	
	
    @Autowired
	private ServicioServiceImpl servicioService;
	
    // GET: http://localhost:8080/Servicios
    @GetMapping
	public ResponseEntity<List<Servicio>> getServicios() throws Exception{	
    	
    	try {
    		List<Servicio> medicos = servicioService.findAll();
    		return ResponseEntity.ok(medicos);
    	} catch (Exception e) {
			throw new Exception("Error al obtener los servicios");
		}
		
	}
    
}
