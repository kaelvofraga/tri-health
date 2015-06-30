package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.ExameCardiologico;

/**
* @author Miromar J. Lima
 * Classe responsavel por persistir e buscar dados no banco
 * Data: 25/06/2015 

 */
@Stateless
public class ExameCardiologicoDAO extends BaseDAO<ExameCardiologico, Long>{
	private static final long serialVersionUID = -6344735380504214189L;
	
	@SuppressWarnings("unchecked")
	public List<ExameCardiologico> buscaPorCriterio(String criterio){
		return em.createQuery(
		         "SELECT e "
		         + "FROM ExameCardiologico e "
		         + "WHERE lower(e.nota) LIKE :nota "
		         + " or lower (e.arquivoLaudo) LIKE :arquivoLaudo")
		         .setParameter("nota", "%"+criterio.trim().toLowerCase()+"%")
		         .setParameter("arquivoLaudo", "%"+criterio.trim().toLowerCase()+"%")
		         .getResultList();
	} 


}
