package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Pais;


/**
 * 
 * @author: Miromar J. Lima
 * Proposito da Classe: Intermediar a camada de servi�o com o usu�rio e o banco de dados
 *         
 */
@Stateless
public class PaisDAO   extends BaseDAO<Pais, Long> {
	private static final long serialVersionUID = -126746156147500109L;

	public List<Pais> buscaPorNome(String nome) {
		return em	
				.createQuery(
						"SELECT au FROM Pais au "
								+ "WHERE "
								+ "lower(au.Pais.nome) like '%" + nome.toLowerCase() + "%' "
								+ "ORDER BY au.Pais.nome")
				.getResultList();
	}


}
