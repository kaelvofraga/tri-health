package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TemporalType;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.edu.ifrs.canoas.tads.lds.bean.ExameUrinaUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Refeicao;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.bean.UsuarioExame;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class UsuarioExameDAO extends BaseDAO<UsuarioExame, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3799927628862856300L;
	
	@SuppressWarnings("unchecked")
	public List<UsuarioExame> buscaPorCriterio(Date dataDe, Date dataAte) {
		try {
			return em.createQuery(
					"SELECT ue FROM usuarioexame ue "
					+ "WHERE ue.data "
					+ "between :dataDe and :dataAte "
					+ "ORDER BY ue.data")
					.setParameter("dataDe", dataDe,TemporalType.TIMESTAMP)
					.setParameter("dataAte", dataAte, TemporalType.TIMESTAMP)
					.getResultList();
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	
}


