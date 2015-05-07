package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

/** Classe responsável pela execução de comandos DML via SQL no Banco de dados da entidade Atividade
* @author Kael Fraga
* @since 07/05/2015
* */
@Stateless
public class AtividadeDAO extends BaseDAO<Atividade, Long> implements Serializable {
	
	private static final long serialVersionUID = 7111756116176127749L;
	
	/** 
	 * @brief Busca atividades baseadas em suas descrições.  	 		  
	 * @param String descricao: descrição da atividade
	 * @return List<Atividade>: lista de atividades ou null se um erro ocorrer
	 * */
	@SuppressWarnings("unchecked")
	public List<Atividade> buscaPorDescricao(String descricao) {
		return em.createQuery("SELECT a FROM Atividade a WHERE lower(a.descricao) like '%"
				+ descricao.toLowerCase() + "%'").getResultList();
	}
	
	/** 
	 * @brief Busca atividades baseadas em um relacionamento com certo usuário.	  	 		  
	 * @param Usuario usuario: usuário relacionado com as atividades
	 * @return List<Atividade>: lista de atividades ou null se um erro ocorrer
	 * */
	@SuppressWarnings("unchecked")
	public List<Atividade> buscaAtividadePorUsuario(Usuario usuario) {	
		return em.createQuery(
				"SELECT a " 
		         + "FROM Atividade a "
		         + "WHERE a.id in "
		         + "(select au.atividade.id "
		         + "	from AtividadeUsuario au "
		         + "	where au.usuario.id = :usuario) ")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
	}
}	

