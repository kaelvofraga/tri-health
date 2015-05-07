package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

/** Classe responsável pela execução de comandos DML via SQL no Banco de dados da entidade AtividadeUsuarioDAO
* @author Kael Fraga
* @since 07/05/2015
* */
@Stateless
public class AtividadeUsuarioDAO extends BaseDAO<AtividadeUsuario, Long>
		implements Serializable {

	private static final long serialVersionUID = -3684765988161245661L;
	
	/** 
	 * @brief Busca todas as atividades relacionadas a um usuário.  	 		  
	 * @param Usuario usuario: usuário relacionado as atividades
	 * @return ArrayList<AtividadeUsuario>: lista de atividades do usuario ou null se um erro ocorrer
	 * */
	@SuppressWarnings("unchecked")
	public List<AtividadeUsuario> buscaAtividadesDoUsuario(Usuario usuario) {
		return em
				.createQuery(
						"SELECT au FROM AtividadeUsuario au "
								+ "WHERE au.usuario.id = :usuario ORDER BY au.dataInicio")
				.setParameter("usuario", usuario.getId()).getResultList();

	}
	
	/** 
	 * @brief Busca atividades relacionadas a um usuário e a um critério de pesquisa.	  	 		  
	 * @param String criterioAtividadeUsuario: critério de pesquisa, que será conferido na descrição, nas notas e no tipo da atividade
	 * @param Usuario usuario: usuário relacionado as atividades
	 * @return List<AtividadeUsuario>: lista de atividades do usuario ou null se um erro ocorrer
	 * */
	@SuppressWarnings("unchecked")
	public List<AtividadeUsuario> buscaPorCriterio(String criterio, Usuario usuario) {
		try {	
			String str = "SELECT au FROM AtividadeUsuario au WHERE lower(au.notas) like '%"; 
			str+= criterio.trim().toLowerCase(); 
			str+="%' OR lower(au.atividade.descricao) like '%" ;
			str+=criterio.trim().toLowerCase();
			str+="%' OR lower(au.atividade.tipoAtividade.nome) like '%"; 
			str+=criterio.trim().toLowerCase();
			str+="%' AND au.usuario.id = :usuario ORDER BY au.dataInicio";
						
			return em.createQuery(str).setParameter("usuario", usuario.getId()).getResultList();
			
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
