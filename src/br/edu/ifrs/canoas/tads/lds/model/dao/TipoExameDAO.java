package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Pais;
import br.edu.ifrs.canoas.tads.lds.bean.TipoExame;

/**
 * 
 * @author: Miromar J. Lima
 * Proposito da Classe: Intermediar a camada de serviço com o usuário e o banco de dados
 *         
 */
@Stateless
public class TipoExameDAO extends BaseDAO<TipoExame, Long> {
	private static final long serialVersionUID = -126746156147500109L;

	@SuppressWarnings("unchecked")
	public List<TipoExame> buscaPorNome(String nome) {
		return em	
				.createQuery(
						"SELECT au FROM TipoExame au "
								+ "WHERE "
								+ "lower(au.TipoExame.nome) like '%" + nome.toLowerCase() + "%' "
								+ "ORDER BY au.TipoExame.nome")
				.getResultList();
	}

}
