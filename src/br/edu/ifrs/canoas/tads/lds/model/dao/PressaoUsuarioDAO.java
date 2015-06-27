package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.apache.commons.lang3.math.NumberUtils;

import br.edu.ifrs.canoas.tads.lds.bean.PressaoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

/**
 * Classe que busca dados do objeto "pressaoUsuario" no banco de dados.
 * 
 * @author Pablo Diehl da Silva
 * @version 06/05/2015
 * @author Alisson
 * @version 22/06/2015
 * Criação do metodo buscaPorCriterio.
 */
@Stateless
public class PressaoUsuarioDAO extends BaseDAO< PressaoUsuario, Long>{
	private static final long serialVersionUID = -3230082070333146918L;
	
	/** 
	 * @brief Busca pressao baseado no usuario.  	 		  
	 * @param Usuario usuario: usuario logado.
	 * @return List<PressaoUsuario>: lista de pressões ou null se um erro ocorrer
	 * */
	@SuppressWarnings("unchecked")
	public List<PressaoUsuario> buscaPressaoPorUsuario(Usuario usuario){
		try {
		return em.createQuery(
		         "SELECT pu.pressaoUsuario " 
		         + "FROM PressaoUsuario pu "
		         + "WHERE pu.usuario.id = :usuario ")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
		}catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	/** 
	 * @brief Busca pressao baseado no criterio informado.  	 		  
	 * @param String criterioPressao: criterio informado.
	 * @return List<PressaoUsuario>: lista de pressões ou null se um erro ocorrer
	 * */ 
	@SuppressWarnings("unchecked")
	public List<PressaoUsuario> buscaPorCriterio(String criterioPressao) {
		String criterio = "";
		
		criterio += (NumberUtils.isNumber(criterioPressao)) ? "pu.paDiastolica = "
				+ new Double(criterioPressao) + "OR pu.paSistolica = " + new Double(criterioPressao) : " lower(pu.notas) like '%" + criterioPressao.toLowerCase() + "%' ";	
				      
		
		
		try {
			return em.createQuery(
					"SELECT pu FROM PressaoUsuario pu " 
				  + "WHERE "
				  +  criterio 
				  + "ORDER BY pu.data").getResultList();
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	
	
}
