package br.edu.ifrs.canoas.tads.lds.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getDataAtual() {
		return Calendar.getInstance().getTime();
	}
	
	public static Date getDataAtualIncrementa(int dias) {
		long timeadj = 24*60*60*1000 * dias;		
		return  new Date (DateUtil.getDataAtual().getTime() + timeadj);
	}
	
	public static Date getDataParametroComIncremento(Date data, int dias){
		if(data == null) return null;
		
		long timeadj = 24*60*60*1000 * dias;		
		return  new Date (data.getTime() + timeadj);
	}
	
}
