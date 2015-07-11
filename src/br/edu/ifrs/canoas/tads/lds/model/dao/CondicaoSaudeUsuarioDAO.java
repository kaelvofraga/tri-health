package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.commons.lang3.math.NumberUtils;

import br.edu.ifrs.canoas.tads.lds.bean.CondicaoSaudeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.StatusSaude;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

/**
 * @author Luana
 */

@Stateless
public class CondicaoSaudeUsuarioDAO extends BaseDAO<CondicaoSaudeUsuario, Long> implements Serializable{

	private static final long serialVersionUID = 3538887461219854472L;

	/** 
	 * @brief Busca condicao de saude baseado no criterio informado, podendo ser descricao ou status.  	 		  
	 * @param String criterioPeso: criterio informado.
	 * @return List<PesoUsuario>: lista de condições de saude
	 * */ 
	@SuppressWarnings("unchecked")
	public List<CondicaoSaudeUsuario> buscaPorCriterio(String criterioDescricao) {		
			return em
					.createQuery(
							"SELECT cs FROM CondicaoSaudeUsuario cs "
							+ "WHERE "
							+ "lower(cs.descricao) like '%" + criterioDescricao.trim().toLowerCase() + "%' "
							+ "OR lower(cs.status.nome) like '%" + criterioDescricao.trim().toLowerCase() + "%' "
							).getResultList();
		
	}

}
