package br.edu.ifrs.canoas.tads.lds.social;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.control.mb.GerenciarLoginMB;

/** ManageBean da US Compartilhar Atividades nas Redes Sociais
* @author Kael Fraga
* @since 23/06/2015
* */
@Named
@SessionScoped
public class FacebookMB implements Serializable {

	private static final long serialVersionUID = 3013015467592228960L;

	@EJB
	private FacebookService facebookService;
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	//Beans
	@Inject 
	private LogFacebook logFacebook;	
	
	public FacebookMB() {}
	
	@PostConstruct
	public void initFacebook(){			
		
		/*TODO algoritmo de inicialização do facebook*/
		
	}
	
	/** 
	 * @brief Autentica conta do Facebook do usuário da seção.	  	 		  
	 * @param void
	 * @return void
	 * */
	public void autenticarUsuario() {
		facebookService.autenticarUsuario(gerenciarLoginMB.getUsuario());
	}
	
	/** 
	 * @brief Publica compartilhamento no Facebook do usuário da seção.	  	 		  
	 * @param void
	 * @return void
	 * */
	public void publicarAtividade() {
		facebookService.publicarAtividade(this.logFacebook);
	}
	
	/** 
	 * @brief Salva registro de compartilhamento no BD.	  	 		  
	 * @param void
	 * @return void
	 * */
	public void salvarLog(LogFacebook logFacebook) {
		facebookService.salvarLog(this.logFacebook);
	}	
}
