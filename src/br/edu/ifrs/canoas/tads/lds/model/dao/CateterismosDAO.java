package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Cateterismo;

/**
 * Classe responsável por persistir e buscar dados no banco
 * 
 * @author Luciano Acosta

 */

@Stateless
public class CateterismosDAO extends BaseDAO<Cateterismo, Long>{

	private static final long serialVersionUID = -6344735380504214189L;
	
	@SuppressWarnings("unchecked")
	public List<Cateterismo> buscaPorCriterio(String criterio){
		return em.createQuery(
		         "SELECT c "
		         + "FROM Cateterismo c "
		         + "WHERE lower(c.observacoes) LIKE :obs "
		         + " or lower (c.laudo) LIKE :laudo")
		         .setParameter("obs", "%"+criterio.trim().toLowerCase()+"%")
		         .setParameter("laudo", "%"+criterio.trim().toLowerCase()+"%")
		         .getResultList();
	} 

}
