package br.edu.ifrs.canoas.tads.lds.model.dao;
import java.util.List;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlimento;
public class TipoAlimentoDAO extends BaseDAO<TipoAlimento, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5014335249510417493L;

	
	public List<TipoAlimento> getTesteJinqStream(){
		return super.getJinqStream().toList();
	}
	
}
