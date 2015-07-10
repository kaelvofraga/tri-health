

package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.edu.ifrs.canoas.tads.lds.bean.CondicaoSaudeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.StatusSaude;
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
	private CondicaoSaudeUsuario condicaoSaude;
	
	//listas
	private List<CondicaoSaudeUsuario> condicaoSaudeList;
	private List<StatusSaude> statusSaudeList;
	
	public String initListar(){
		return URL_LISTAR_CONDICAO_SAUDE;
	}
	
	public String initManter(){
		return URL_MANTER_CONDICAO_SAUDE;
	}

	/** 
	 * @brief Vincula usuï¿½rio logado ï¿½ atividade e inseri a nova atividade no BD, apï¿½s limpa formulï¿½rio
	 * @param void
	 * @return void
	 * **/
	public void salvaCondicaoUsuario(){
		condicaoSaude.setUsuario(gerenciarLoginMB.getUsuario());
		if(condSaudeService.salvaCondicaoUsuario(condicaoSaude)){		
			this.initManter();
		}
	}
	
	/** 
	 * @brief Verifica se a registro atual está sendo inserida nova ou atualizada uma antiga.	  	 		  
	 * @param void
	 * @return true se está atualizando registro ou false se não.
	 * */
	public boolean isAtualizacao(){
		return condicaoSaude != null && condicaoSaude.getId() != null;
	}
	
	/*GETTERS E SETTERS*/
	
	public ManterCondicaoSaudeService getCondSaudeService() {
		return condSaudeService;
	}

	public void setCondSaudeService(ManterCondicaoSaudeService condSaudeService) {
		this.condSaudeService = condSaudeService;
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

	public List<CondicaoSaudeUsuario> getCondicaoSaudeList() {
		return condicaoSaudeList;
	}

	public void setCondicaoSaudeList(List<CondicaoSaudeUsuario> condicaoSaudeList) {
		this.condicaoSaudeList = condicaoSaudeList;
	}

	public List<StatusSaude> getStatusSaudeList() {
		if(statusSaudeList == null)
			statusSaudeList = condSaudeService.buscaStatus();
		return statusSaudeList;
	}

	public void setStatusSaudeList(List<StatusSaude> statusSaudeList) {
		this.statusSaudeList = statusSaudeList;
	}

}

