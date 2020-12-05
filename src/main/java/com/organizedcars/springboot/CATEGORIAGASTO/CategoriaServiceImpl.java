package com.organizedcars.springboot.CATEGORIAGASTO;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false)
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired
	CategoriaDAO categoriaDAO;
	
	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		
		List<Categoria> categorias = categoriaDAO.findAll();
		
		//ordeno las categorias alfabeticamente
 		Comparator<Categoria> comparadorCategorias = (Categoria c1, Categoria c2) -> {
 			return (c1.getNombre()).compareTo((c2.getNombre()));
 		};
 		
 		Collections.sort(categorias, comparadorCategorias);
		 
		return categorias ;
	}
}
