package br.edu.ifrs.canoas.tads.lds.model.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.ifrs.canoas.tads.lds.bean.ExameUrina;

@Stateless
public class ExameUrinaDAO extends BaseDAO<ExameUrina, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2520975190400364647L;

	@PersistenceContext 
	protected EntityManager em;
	
	public void insere(ExameUrina exameUrina) {
//		exameUrina.setTipoExameUrina(exameUrina);
//		public void insere(T t) {
			em.persist(exameUrina);
	}
		
}

