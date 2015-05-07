package br.edu.ifrs.canoas.tads.lds.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @brief Classe respons�vel pelos m�todos com fun��es matem�ticas para uso comum.
 * @author Kael Fraga
 * @since 07/05/2015
 * **/
public class MathUtil {
	
	/**
	 * @brief M�todo respons�vel pelo arredondamento de um valor double
	 * @param double value: valor a ser arredondado 
	 * @param int places: n�mero de casas decimais desejado
	 * @return double: valor arredondado 
	 * @throw IllegalArgumentException caso valores passados como par�metros sejam inv�lidos.
	 * **/
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}	
}
