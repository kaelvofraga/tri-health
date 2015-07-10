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
	 * @brief Busca peso baseado no criterio informado.  	 		  
	 * @param String criterioPeso: criterio informado.
	 * @return List<PesoUsuario>: lista de pesos ou null se um erro ocorrer
	 * */ 
	@SuppressWarnings("unchecked")
	public List<CondicaoSaudeUsuario> buscaPorCriterio(String criterioDescricao, Usuario usuario) {
		try {	
			String str = "SELECT au FROM CondicaoSaudeUsuario cs WHERE lower(cs.descricao) like '%"; 
			str+= criterioDescricao.trim().toLowerCase(); 
			str+="%' OR lower(cs.statussaude.status) like '%"; 
			str+=criterioDescricao.trim().toLowerCase();
			str+="%' AND cs.usuario.id = :usuario ORDER BY au.dataInicio";
						
			return em.createQuery(str).setParameter("usuario", usuario.getId()).getResultList();
			
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

}
