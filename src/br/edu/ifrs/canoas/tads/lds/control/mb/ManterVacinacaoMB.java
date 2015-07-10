package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.edu.ifrs.canoas.tads.lds.bean.Vacinacao;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterVacinacaoService;

@Named
@SessionScoped
public class ManterVacinacaoMB implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7920229190550118233L;
	private static final String URL_LISTAR_VACINACOES = "/private/pages/listarVacinacoes.jsf";
	private static final String URL_MANTER_VACINACOES = "/private/pages/manterVacinacoes.jsf";

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	//Services
	@EJB
	private ManterVacinacaoService manterVacinacaoService;

	private String criterioVacinacao;
	
	private Vacinacao vacinacao;
	
	private List<Vacinacao> vacinacaoList;
	
	public String initListar(){	
		criterioVacinacao = "";
		
		vacinacaoList = null;
		this.busca();
		
		return URL_LISTAR_VACINACOES;
	}
	
	@PostConstruct
	public String initManter(){	
		vacinacao = new Vacinacao();		
		
		return URL_MANTER_VACINACOES;
	}
	
	public ManterVacinacaoMB() {
		
		
	}

	
	public void busca(){
		vacinacaoList = manterVacinacaoService.buscaGeral(this.criterioVacinacao, gerenciarLoginMB.getUsuario());
	}
	
	public void clearTable(){
		vacinacao = new Vacinacao();

		criterioVacinacao = "";

		this.busca();
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		this.vacinacao = (Vacinacao) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext().redirect("../.."+URL_MANTER_VACINACOES);
    }
	
	public void salvaVacinacao(){
		vacinacao.setUsuario(gerenciarLoginMB.getUsuario());
		
		if(manterVacinacaoService.salvaVacinacao(vacinacao)==true){
			this.initManter();
		}
	}

	public String alteraVacinacao() {
		if(manterVacinacaoService.alteraVacinacao(vacinacao)==true){
			return URL_LISTAR_VACINACOES;
		}
		else{
			return URL_MANTER_VACINACOES;
		}
	}

	public String excluiVacinacao() {
		if (manterVacinacaoService.excluiVacinacao(vacinacao)) {
			this.busca();
			return URL_LISTAR_VACINACOES;
		}
		return URL_MANTER_VACINACOES;
	}
	
	public boolean isAtualizacao(){
		return this.vacinacao != null && vacinacao.getId() != null && vacinacao.getId() > 0;
	}	
	

	public String getCriterioVacinacao() {
		return criterioVacinacao;
	}


	public void setCriterioVacinacao(String criterioVacinacao) {
		this.criterioVacinacao = criterioVacinacao;
	}


	public Vacinacao getVacinacao() {
		return vacinacao;
	}


	public void setVacinacao(Vacinacao vacinacao) {
		this.vacinacao = vacinacao;
	}


	public List<Vacinacao> getVacinacaoList() {
		return vacinacaoList;
	}


	public void setVacinacaoList(List<Vacinacao> vacinacaoList) {
		this.vacinacaoList = vacinacaoList;
	}	
}
