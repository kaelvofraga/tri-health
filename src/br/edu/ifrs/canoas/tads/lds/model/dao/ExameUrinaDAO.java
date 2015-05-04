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

	@SuppressWarnings("unchecked")
	public List<ExameUrina> buscaExameUrinaPorUsuario(Usuario usuario) {
		
		return em.createQuery(
				"SELECT euu.tipoexameurina "
				+ "from ExameUrina euu where euu.usuario.id=:usuario")
				.setParameter("usuario",usuario.getId())
				.getResultList();
	}
}
	




