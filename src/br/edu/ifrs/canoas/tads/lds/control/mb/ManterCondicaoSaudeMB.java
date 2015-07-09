

package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.CondicaoSaudeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.StatusSaude;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterAtividadesService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterCondicaoSaudeService;

/**
 * @brief ManageBean de condições de saúde
 * @author Luana Gomes
 * @version 07/07/2015
 */

@Named
@SessionScoped
public class ManterCondicaoSaudeMB implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final String URL_LISTAR_CONDICAO_SAUDE = "/private/pages/listarCondicaoSaude.jsf";
	private static final String URL_MANTER_CONDICAO_SAUDE = "/private/pages/manterCondicaoSaude.jsf";
	
	//service
	@EJB
	private ManterCondicaoSaudeService condSaudeService;
	
	//Control
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	//Beans
	@Inject
	private StatusSaude status;
	
	@Inject
	private CondicaoSaudeUsuario condicaoSaude;
	
	public String initListar(){
		return URL_LISTAR_CONDICAO_SAUDE;
	}
	
	public String initManter(){
		return URL_MANTER_CONDICAO_SAUDE;
	}

	/*GETTERS E SETTERS*/
	
	public ManterCondicaoSaudeService getCondSaudeService() {
		return condSaudeService;
	}

	public void setCondSaudeService(ManterCondicaoSaudeService condSaudeService) {
		this.condSaudeService = condSaudeService;
	}

	public StatusSaude getStatus() {
		return status;
	}

	public void setStatus(StatusSaude status) {
		this.status = status;
	}

	public CondicaoSaudeUsuario getCondicaoSaude() {
		return condicaoSaude;
	}

	public void setCondicaoSaude(CondicaoSaudeUsuario condicaoSaude) {
		this.condicaoSaude = condicaoSaude;
	}

	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}

	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}

}

