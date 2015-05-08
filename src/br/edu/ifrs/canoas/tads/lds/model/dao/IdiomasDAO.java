package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Idioma;

/**
 * 
 * @author: Miromar J. Lima
 * Proposito da Classe: Intermediar a camada de serviço com o usuário e o banco de dados
 *         
 */
@Stateless
public class IdiomasDAO extends BaseDAO<Idioma, Long> {
	private static final long serialVersionUID = 1L;
	public List<Idioma> buscaPorCriterio(String criterioIdioma) {
		return em	
				.createQuery(
						"SELECT au FROM Idioma"
								+ "WHERE "
								+ "lower(au.Idioma.nome) like '%" + criterioIdioma.toLowerCase() + "%' "
								+ "ORDER BY Idioma.nome")
				.getResultList();
	}


}
