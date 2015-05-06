package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Cateterismo;

@Stateless
public class CateterismosDAO extends BaseDAO<Cateterismo, Long>{

	private static final long serialVersionUID = -6344735380504214189L;
	
	@SuppressWarnings("unchecked")
	public List<Cateterismo> buscaPorCriterio(String criterio){
		return em.createQuery(
		         "SELECT u "
		         + "FROM Cateterismo c "
		         + "INNER JOIN MEDICO m "
		         + "ON m.ID_MEDICO = c.ID_MEDICO_SOLICITANTE "
		         + "INNER JOIN MEDICO m2 "
		         + "ON m2.ID_MEDICO = c.ID_MEDICO_RESPONSAVEL "
		         + "WHERE lower(m.nome) like '%:nome%' "
		         + " or lower (m2.nome) like '%:nome2%' "
		         + " or lower (c.laudo) like '%:laudo%' ")
		         .setParameter("nome", criterio.trim().toLowerCase())
		         .setParameter("nome2", criterio.trim().toLowerCase())
		         .setParameter("laudo", criterio.trim().toLowerCase())
		         .getResultList();
	} 

}
