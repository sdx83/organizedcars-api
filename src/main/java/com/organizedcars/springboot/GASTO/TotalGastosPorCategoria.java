package com.organizedcars.springboot.GASTO;

import java.math.BigDecimal;

public interface TotalGastosPorCategoria {

	String getCategoria();
	
	BigDecimal getTotal();
}
