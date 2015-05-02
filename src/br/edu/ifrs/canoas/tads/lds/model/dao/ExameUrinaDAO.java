package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.ifrs.canoas.tads.lds.bean.ExameUrina;
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

@Stateless
public class ExameUrinaDAO extends BaseDAO<ExameUrina, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2520975190400364647L;

	public List<ExameUrina> buscaExameUrinaPorUsuario(Usuario usuario) {
		
		return em.createQuery(
				"SELECT eu " 
		         + "FROM ExameUrina eu "
		         + "WHERE eu.id in "
		         + "(select euu.exameUrina.id "
		         + "	from ExameUrina eu "
		         + "	where euu.usuario.id = :usuario) ")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
	}
	
	public void insere(ExameUrina exameUrina) {
//		exameUrina.setTipoExameUrina(exameUrina);
//		public void insere(T t) {
			em.persist(exameUrina);
	}
		
}

