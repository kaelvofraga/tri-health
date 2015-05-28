package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAnalise;

@Stateless
public class TipoAnaliseDAO extends BaseDAO<TipoAnalise, Long> {

	/**
	 * @author Alisson Lorscheiter
	 * Classe que realiza a consulta ao banco para retornar os Tipos de Exame de Analise.
	 * 
	 */
	private static final long serialVersionUID = 8705987299342646987L;
	
	@SuppressWarnings("unchecked")
	public List<TipoAnalise> buscaPorTipo(String tipo) {
		return em.createQuery(
				"SELECT ta " 
		         + "FROM TipoAnalise ta "
		         + "WHERE lower(ta.tipo) = lower(:tipo) ")
		         .setParameter("tipo", tipo)
		         .getResultList();
	}
}




