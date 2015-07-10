package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterVacinacaoService;

@Named
@SessionScoped
public class ManterVacinacaoMB implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7920229190550118233L;


	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	//Services
	@EJB
	private ManterVacinacaoService manterVacinacaoService;

	
	public ManterVacinacaoMB() {
	}	
}
