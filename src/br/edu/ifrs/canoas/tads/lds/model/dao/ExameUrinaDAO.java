package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TemporalType;

import br.edu.ifrs.canoas.tads.lds.bean.ExameUrinaUsuario;

@Stateless
public class ExameUrinaDAO extends BaseDAO<ExameUrinaUsuario, Long>{

	private static final long serialVersionUID = 2520975190400364647L;

	@SuppressWarnings("unchecked")
	public List<ExameUrinaUsuario> buscaPorCriterio(Date dataDe, Date dataAte,String criterioExameUrina) {
		try {
			String query="SELECT eu FROM ExameUrinaUsuario eu WHERE lower (eu.observacao) like ? OR eu.data between ? and ? ORDER BY eu.data";
			return em.createQuery(query).setParameter(1, criterioExameUrina)
					.setParameter(2, dataDe,TemporalType.TIMESTAMP).
					setParameter(3, dataAte, TemporalType.TIMESTAMP)
					.getResultList();
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}