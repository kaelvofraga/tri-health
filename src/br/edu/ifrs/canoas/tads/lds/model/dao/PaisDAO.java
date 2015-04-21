package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Pais;


@Stateless
public class PaisDAO   extends BaseDAO<Pais, Long> {
	private static final long serialVersionUID = -126746156147500109L;

	public List<Pais> buscaPorCriterio(String criterioPais) {
		return em	
				.createQuery(
						"SELECT au FROM Pais au "
								+ "WHERE "
								+ "lower(au.Pais.nomePais) like '%" + criterioPais.toLowerCase() + "%' "
								+ "ORDER BY au.Pais.nomePais")
				.getResultList();
	}


}
