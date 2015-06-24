package br.edu.ifrs.canoas.tads.lds.social;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.BaseDAO;

/** Classe responsável pela execução de comandos DML via SQL no Banco de dados da entidade LogFacebook
* @author Kael Fraga
* @since 23/06/2015
* */
@Stateless
public class LogFacebookDAO extends BaseDAO<LogFacebook, Long> 
							implements Serializable  {

	private static final long serialVersionUID = -1277017370300553400L;

	/** 
	 * @brief Busca quantidade de vezes que um usuário compartilhou atividades no Facebook.  	 		  
	 * @param Usuario usuario: usuário relacionado as atividades
	 * @return long: quantidade de compartilhamentos no Facebook
	 * */
	public long buscaQuantidadeCompartilhamentos(Usuario usuario) {
		
		 Query query = em.createQuery(	"SELECT COUNT(lf) FROM LogFacebook lf "
		 								+ "WHERE lf.usuario.id = :usuario").
		 								setParameter("usuario", usuario.getId());	 
		 
		 return (Long) query.getSingleResult();
	}
	
}
