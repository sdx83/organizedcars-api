package com.organizedcars.springboot.GASTO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TotalCategoriasDAO extends JpaRepository<Gasto, Long> { 
	
	@Query(value = "SELECT c.nombre as categoria, sum(g.precio) as total from gastos as g inner join "
			+ "categorias as c on c.id_categoria=g.id_categoria where g.id_vehiculo =?1 group by c.nombre", nativeQuery = true)
	List<TotalGastosPorCategoria> obtenerGastosAgrupadosPorCategoria(Long idVehiculo);
	
	
	@Query(value = "SELECT c.nombre as categoria, sum(g.precio) as total from gastos as g inner join "
			+ "categorias as c on c.id_categoria=g.id_categoria where g.id_vehiculo =?1 and "
			+ "CAST (SUBSTRING(g.fecha,1,4) AS int )=EXTRACT(year FROM now()) group by c.nombre", nativeQuery = true)
	List<TotalGastosPorCategoria> obtenerGastosAgrupadosPorCategoriaDelUltimoAnio(Long idVehiculo);

}