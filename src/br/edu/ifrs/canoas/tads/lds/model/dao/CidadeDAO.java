package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Cidade;
import br.edu.ifrs.canoas.tads.lds.bean.UnidadeFederativa;


/**
 * 
 * @author: Miromar J. Lima
 * Proposito da Classe: Intermediar a camada de serviço com o usuário e o banco de dados
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
								+ "ORDER BY au.Cidade.nome", Cidade.class)
				.getResultList();
	}

	public List<Cidade> buscaPorUf(UnidadeFederativa unidadeFederativa) {
		return em
				.createQuery(
						"SELECT au FROM Cidade au "
								+ "WHERE "
								+ "au.uf.id = " + unidadeFederativa.getId()
								+ "ORDER BY au.nome", Cidade.class)
				.getResultList();
	}


}
