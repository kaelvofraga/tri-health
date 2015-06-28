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
		
	public FacebookMB() {}
	
	@PostConstruct
	public void initFacebook(){			
		facebookService.initFacebook();		
	}
	
	/** 
	 * @brief Publica compartilhamento no Facebook do usuário da seção.	  	 		  
	 * @param String message: mensagem a ser publicada
	 * @return void
	 * */
	public void publicarAtividade(String message) {
		facebookService.publicarAtividade(gerenciarLoginMB.getUsuario(), message);
	}	
}
