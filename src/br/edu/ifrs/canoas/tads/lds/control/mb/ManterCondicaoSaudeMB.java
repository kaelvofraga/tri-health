

package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.edu.ifrs.canoas.tads.lds.bean.CondicaoSaudeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
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
	
	private String criterioDescricao;
		
	public String initListar(){
		condicaoSaude = new CondicaoSaudeUsuario();
		criterioDescricao = "";
		condicaoSaudeList = new ArrayList<CondicaoSaudeUsuario>();
		return URL_LISTAR_CONDICAO_SAUDE;
	}
	
	public String initManter(){
		condicaoSaude = new CondicaoSaudeUsuario();
		statusSaudeList = this.getStatusSaudeList();
		condicaoSaude.setStatus(new StatusSaude());
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
	 * @brief Metodo que busca condições de saúde do usuário
	 * @param void
	 * @return void
	 * */
	public void busca(){
		condicaoSaudeList = condSaudeService.busca(criterioDescricao, gerenciarLoginMB.getUsuario());
	}
	
	/** 
	 * @brief Verifica se a registro atual está sendo inserida nova ou atualizada uma antiga.	  	 		  
	 * @param void
	 * @return true se está atualizando registro ou false se não.
	 * */
	public boolean isAtualizacao(){
		return condicaoSaude != null && condicaoSaude.getId() != null;
	}
	
	/** 
	 * @brief Metodo que realiza o evento de seleção da linha da tabela que lista
	 *  as condicoes de saude do usuário.	 		  
	 * @param event (SelectEvent)
	 * @return void
	 * */
	public void onRowSelect(SelectEvent event) throws IOException {
		this.condicaoSaude = (CondicaoSaudeUsuario) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext().redirect("../../private/pages/manterCondicaoSaude.jsf");
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

	public String getCriterioDescricao() {
		return criterioDescricao;
	}

	public void setCriterioDescricao(String criterioDescricao) {
		this.criterioDescricao = criterioDescricao;
	}

}

