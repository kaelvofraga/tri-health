package br.edu.ifrs.canoas.tads.lds.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @brief Classe responsável pelos métodos com funções matemáticas para uso comum.
 * @author Kael Fraga
 * @since 07/05/2015
 * **/
public class MathUtil {
	
	/**
	 * @brief Método responsável pelo arredondamento de um valor double
	 * @param double value: valor a ser arredondado 
	 * @param int places: número de casas decimais desejado
	 * @return double: valor arredondado 
	 * @throw IllegalArgumentException caso valores passados como parâmetros sejam inválidos.
	 * **/
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}	
}
