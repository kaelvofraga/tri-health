package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;
import javax.ejb.Stateless;
import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

@Stateless
public class PesoUsuarioDAO extends BaseDAO<PesoUsuario, Long> {

	private static final long serialVersionUID = 9099610181810420242L;

	/**
	 * Classe que busca dados no banco
	 * 
	 * @author Luana
	 * @version 06/05/2015
	 * @author Alisson
	 * @version 18/06/2015
	 * Criação do metodo buscaPorCriterio.
	 */

	
	/** 
	 * @brief Busca peso baseado no usuario.  	 		  
	 * @param Usuario usuario: usuario logado.
	 * @return List<PesoUsuario>: lista de pesos ou null se um erro ocorrer
	 * */
	@SuppressWarnings("unchecked")
	public List<PesoUsuario> buscaPesoUsuario(Usuario usuario) {
		try {
		return em.createQuery(
				"SELECT pu "
			  + "FROM PesoUsuario pu "
			  + "WHERE pu.usuario.id = :usuario ")
			  .setParameter("usuario", usuario.getId())
			  .getResultList();
		}catch (IllegalArgumentException e) {
			return null;
		}
	}

	/** 
	 * @brief Busca peso baseado no criterio informado.  	 		  
	 * @param String criterioPeso: criterio informado.
	 * @return List<PesoUsuario>: lista de pesos ou null se um erro ocorrer
	 * */ 
	@SuppressWarnings("unchecked")
	public List<PesoUsuario> buscaPorCriterio(String criterioPeso) {
		String criterio = "";
		
		try { criterio = (new Double(criterioPeso) instanceof Double) ? "pu.valor = " + criterioPeso : " OR ";
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
		}
	}

}