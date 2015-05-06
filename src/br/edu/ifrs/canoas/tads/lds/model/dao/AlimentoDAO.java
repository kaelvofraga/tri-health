package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import br.edu.ifrs.canoas.tads.lds.bean.Alimento;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlimento;

@Stateless
public class AlimentoDAO extends BaseDAO<Alimento, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5014335249510417493L;
	
	@SuppressWarnings("unchecked")
	public List<Alimento> buscaAlimentosPorTipoAlimento(TipoAlimento tipoAlimento) {
		
		if(tipoAlimento == null) return new ArrayList<Alimento>();

		return em.createQuery(
		         "SELECT ali " 
		         + "FROM Alimento as ali "
		         + "WHERE ali.tipoAlimento.id = :tipoAlimento ")
		         .setParameter("tipoAlimento", tipoAlimento.getId())
		         .getResultList();		
	}
}
