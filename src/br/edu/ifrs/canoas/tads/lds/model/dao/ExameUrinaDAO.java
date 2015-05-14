package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.edu.ifrs.canoas.tads.lds.bean.ExameUrinaUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

@Stateless
public class ExameUrinaDAO extends BaseDAO<ExameUrinaUsuario, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2520975190400364647L;

	@SuppressWarnings("unchecked")
	public List<ExameUrinaUsuario> buscaExameUrinaPorUsuario(Usuario usuario) {
		
		return em.createQuery(
				"SELECT euu.tipoexameurina "
				+ "from ExameUrina euu where euu.usuario.id=:usuario")
				.setParameter("usuario",usuario.getId())
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<ExameUrinaUsuario> buscaPorCriterio(String criterioExameUrina) {
		try {
			return em.createQuery(
				"SELECT eu "
						+ "FROM ExameUrina eu WHERE"
//						+ "lower(eu.data) like '%" + criterioTipoExameUrina.trim().toLowerCase() + "%' "
						+ "lower(eu.tipoexameurina.tipo) like '%" + criterioExameUrina.trim().toLowerCase() + "%' "
//						+ "OR lower(eu.resultado) like '%" + criterioTipoExameUrina.trim().toLowerCase() + "%' "
						+ "ORDER BY eu.tipoexameurina.tipo").getResultList();

		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> buscaExameUrinaPorUsuario(ExameUrinaUsuario exameUrina){
		try{
			return em.createQuery(
			         "SELECT eu.id " 
			         + "FROM ExameUrina eu "
			         + "WHERE eu.id = :tipoExameUrina")
			         .setParameter("tipoExameUrina",exameUrina.getId()).getResultList();
			}catch (IllegalArgumentException e) {
				return null;	
		}
	}
}


	


	




