package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Cidade;


/**
 * 
 * @author: Miromar J. Lima
 * Proposito da Classe: Intermediar a camada de servi�o com o usu�rio e o banco de dados
 *         
 */
@Stateless
public class CidadeDAO extends BaseDAO<Cidade, Long> {
	private static final long serialVersionUID = -126746156147500109L;

	public List<Cidade> buscaPorCriterio(String criterioCidade) {
		return em
				.createQuery(
						"SELECT au FROM Cidade au "
								+ "WHERE "
								+ "lower(au.Cidade.nome) like '%" + criterioCidade.toLowerCase() + "%' "
								+ "ORDER BY au.Cidade.nome")
				.getResultList();
	}


}
