package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;



@Named
@SessionScoped
public class ManterExamesMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5731686734009147647L;
	private static final String URL_LISTAR_EXAMES = "/private/pages/listarExames.jsf";
	private static final String URL_MANTER_EXAMES = "/private/pages/manterExames.jsf";
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	public ManterExamesMB() {	
		
	}

	@PostConstruct
	public String initListar() {
		return URL_LISTAR_EXAMES;
	}
	
	@PostConstruct
	public String initManter() {
		return URL_MANTER_EXAMES;
	}
}
