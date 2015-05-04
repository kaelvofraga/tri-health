package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.ExameUrina;
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.TipoExameUrina;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

@Stateless
public class TipoExameUrinaDAO extends BaseDAO<TipoExameUrina, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8705987299342646987L;
	
	@SuppressWarnings("unchecked")
	public List<TipoExameUrina> buscaPorTipo(String tipo) {
		return em.createQuery(
				"SELECT teu " 
		         + "FROM TipoExameUrina teu "
		         + "WHERE lower(teu.tipo) = lower(:tipo) ")
		         .setParameter("tipo", tipo)
		         .getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<TipoExameUrina> buscaTipoPorUsuario(Usuario usuario) {
		return em.createQuery(
				"SELECT teu " 
		         + "FROM TipoExameUrina teu "
		         + "WHERE teu.id in "
		         + "(select eu.tipoexameurina.id "
		         + "	from ExameUrina eu "
		         + "	where eu.usuario.id = :usuario) ")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
	}

}




