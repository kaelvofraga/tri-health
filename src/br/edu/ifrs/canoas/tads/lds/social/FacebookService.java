package br.edu.ifrs.canoas.tads.lds.social;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

/** Service da US Compartilhar Atividades nas Redes Sociais
* @author Kael Fraga
* @since 23/06/2015
* */
@Stateless
public class FacebookService {
	
	@Inject
	private LogFacebookDAO logFacebookDAO;
	
	public FacebookService() {}
	
	/** 
	 * @brief Autentica conta do Facebook do usuário da seção.	  	 		  
	 * @param Usuario usuario: usuário da seção
	 * @return true se atenticou com sucesso, false se um erro ocorreu
	 * */
	public boolean autenticarUsuario(Usuario usuario) {
		if (usuario != null) {

			/*TODO algoritimo de autenticação*/
			
			return true;
		}	
		
		return false;
	}
	
	/** 
	 * @brief Publica compartilhamento no Facebook do usuário da seção.	  	 		  
	 * @param LogFacebook logFacebook: registro de compartilhamento
	 * @return true se publicou com sucesso, false se um erro ocorreu
	 * */
	public boolean publicarAtividade(LogFacebook logFacebook) {
		if (logFacebook != null && 
			logFacebook.getData() != null && 
			logFacebook.getUsuario() != null) {

			/*TODO algoritimo de compartilhamento*/
			
			return true;
		}		
		
		return false;
	}
	
	/** 
	 * @brief Salva registro de compartilhamento no BD.	  	 		  
	 * @param LogFacebook logFacebook: registro de compartilhamento
	 * @return true se salvou com sucesso, false se um erro ocorreu
	 * */
	public boolean salvarLog(LogFacebook logFacebook) {
		if (logFacebook != null && 
			logFacebook.getData() != null && 
			logFacebook.getUsuario() != null) {

			logFacebookDAO.insere(logFacebook);
			
			return true;
		}
		
		return false;
	}
	
}
