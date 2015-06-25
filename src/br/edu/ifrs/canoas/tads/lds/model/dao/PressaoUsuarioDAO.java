package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;
import javax.ejb.Stateless;
import br.edu.ifrs.canoas.tads.lds.bean.PressaoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

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
	/*	String criterio = "";
		
		try { 
			criterio = (new Double(criterioPressao) instanceof Double) ? "pu.paSistolica = " + criterioPressao : " OR ";
			criterio = (new Double(criterioPressao) instanceof Double) ? "paDiastolica = " + criterioPressao : " OR ";
		}catch(Exception e) {}
		
		if (criterio == ""){
			criterio =  " lower(pu.nota) like '%" + criterioPeso.toLowerCase() + "%' "
				      + "OR lower(pu.udm.descricao) like '%" + criterioPeso.toLowerCase() + "%' ";
		}
		
		try {
			return em.createQuery(
					"SELECT pu FROM PesoUsuario pu " 
				  + "WHERE "
				  +  criterio 
				  + "ORDER BY pu.data").getResultList();
		} catch (IllegalArgumentException e) {
			return null;
		}*/
		return null;
	}

	
	
}
