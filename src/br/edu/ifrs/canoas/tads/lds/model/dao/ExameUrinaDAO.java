package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.ifrs.canoas.tads.lds.bean.ExameUrina;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
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
	
	@SuppressWarnings("unchecked")
	public List<ExameUrina> buscaPorCriterio(String criterioTipoExameUrina) {
		try {
			return em.createQuery(
				"SELECT eu "
						+ "FROM ExameUrina eu WHERE"
//						+ "lower(eu.data) like '%" + criterioTipoExameUrina.trim().toLowerCase() + "%' "
						+ "lower(eu.tipoexameurina.tipo) like '%" + criterioTipoExameUrina.trim().toLowerCase() + "%' "
//						+ "OR lower(eu.resultado) like '%" + criterioTipoExameUrina.trim().toLowerCase() + "%' "
						+ "ORDER BY eu.tipoexameurina.tipo").getResultList();

		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
	


	




