package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;

/** Classe responsável pela execução de comandos DML via SQL no Banco de dados da entidade TipoAtividade
* @author Kael Fraga
* @since 07/05/2015
* */
@Stateless
public class TipoAtividadeDAO extends BaseDAO<TipoAtividade, Long> implements
Serializable{

	private static final long serialVersionUID = 1289654162766030500L;
	
	/** 
	 * @brief Busca tipos de atividades baseadas em seus nomes.	 		  
	 * @param TipoAtividade tipoAtividade: tipo de atividade contendo o nome desejado
	 * @return List<TipoAtividade>: lista de tipos de atividades ou null se um erro ocorrer
	 * */
	@SuppressWarnings("unchecked")
	public List<TipoAtividade> buscaPorNome(TipoAtividade tipoAtividade) {
		return em.createQuery("SELECT ta FROM TipoAtividade ta WHERE lower(ta.nome) = lower(:nome) ")
		         .setParameter("nome", tipoAtividade.getNome())
		         .getResultList();
	}
	
	
}
