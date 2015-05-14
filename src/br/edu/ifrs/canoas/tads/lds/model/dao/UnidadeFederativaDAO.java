package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Pais;
import br.edu.ifrs.canoas.tads.lds.bean.UnidadeFederativa;

/**
 * 
 * @author: Miromar J. Lima
 * Proposito da Classe: Intermediar a camada de serviço com o usuário e o banco de dados
 *         
 */
@Stateless
public class UnidadeFederativaDAO extends BaseDAO<UnidadeFederativa, Long> {
	private static final long serialVersionUID = -126746156147500109L;

	public List<UnidadeFederativa> buscaPorCriterio(String criterioUF) {
		return em	
				.createQuery(
						"SELECT au FROM UnidadeFederativa au "
								+ "WHERE "
								+ "lower(au.UnidadeFederativa.nome) like '%" + criterioUF.toLowerCase() + "%' "
								+ "ORDER BY au.UnidadeFederativa.nome", UnidadeFederativa.class)
				.getResultList();
	}

	public List<UnidadeFederativa> buscaPorPais(Pais pais) {
		return em	
				.createQuery(
						"SELECT au FROM UnidadeFederativa au "
								+ "WHERE "
								+ "au.pais.id = " + pais.getId()
								+ "ORDER BY au.nome", UnidadeFederativa.class)
				.getResultList();
	}


}
