package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TemporalType;

import br.edu.ifrs.canoas.tads.lds.bean.UsuarioExame;

@Stateless
public class UsuarioExameDAO extends BaseDAO<UsuarioExame, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3799927628862856300L;
	
	@SuppressWarnings("unchecked")
	public List<UsuarioExame> buscaPorCriterio(Date dataDe, Date dataAte) {
		try {
			String query="SELECT eu FROM UsuarioExame eu WHERE eu.data between ? and ? ORDER BY eu.data";
			return em.createQuery(query).setParameter(1, dataDe,TemporalType.TIMESTAMP).setParameter(2, dataAte, TemporalType.TIMESTAMP).getResultList();
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

}

/*
@SuppressWarnings("unchecked")
	public List<UsuarioExame> buscaPorCriterio(Date dataDe, Date dataAte,String criterioExameSangue) {
		try {
			String query="SELECT eu FROM UsuarioExame eu WHERE lower (eu.observacao) like ? OR eu.data between ? and ? ORDER BY eu.data";
			return em.createQuery(query).setParameter(1, criterioExameSangue).setParameter(2, dataDe,TemporalType.TIMESTAMP).setParameter(3, dataAte, TemporalType.TIMESTAMP).getResultList();
		} catch (IllegalArgumentException e) {
			return null;
		}
	}*/
