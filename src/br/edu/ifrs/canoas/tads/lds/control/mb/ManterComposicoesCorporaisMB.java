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

import br.edu.ifrs.canoas.tads.lds.bean.Composicao;
import br.edu.ifrs.canoas.tads.lds.bean.ComposicaoUsuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterComposicaoService;

/**
 * @brief ManageBean de Listagem e Manutenção de Composições Corporais
 * @author Pablo Diehl
 * @version 24/06/2015
 *
 */
@Named
@SessionScoped
public class ManterComposicoesCorporaisMB implements Serializable {

	private static final long serialVersionUID = 6061701751807684892L;
	private static final String URL_LISTAR_COMPOSICOES_CORPORAIS = "/private/pages/listarComposicoesCorporais.jsf";
	private static final String URL_MANTER_COMPOSICOES_CORPORAIS = "/private/pages/manterComposicoesCorporais.jsf";

	
	@EJB
	private ManterComposicaoService composicaoService;
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@Inject
	private ComposicaoUsuario composicaoUsuario;
	
	private List<ComposicaoUsuario> listaComposicaoUsuario;

	public String initListar() {
		return URL_LISTAR_COMPOSICOES_CORPORAIS;
	}

	public String initManter() {
		composicaoUsuario = new ComposicaoUsuario();
		
		return URL_MANTER_COMPOSICOES_CORPORAIS;
	}
	
	public void salvaComposicao(){
		composicaoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		if(composicaoService.salvaComposicao(composicaoUsuario)){
			this.initManter();
		}
	}

	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}

	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}

	public ManterComposicaoService getComposicaoService() {
		return composicaoService;
	}

	public void setComposicaoService(ManterComposicaoService composicaoService) {
		this.composicaoService = composicaoService;
	}

	public ComposicaoUsuario getComposicaoUsuario() {
		return composicaoUsuario;
	}

	public void setComposicaoUsuario(ComposicaoUsuario composicaoUsuario) {
		this.composicaoUsuario = composicaoUsuario;
	}

	public List<ComposicaoUsuario> getListaComposicaoUsuario() {
		return listaComposicaoUsuario;
	}

	public void setListaComposicaoUsuario(
			List<ComposicaoUsuario> listaComposicaoUsuario) {
		this.listaComposicaoUsuario = listaComposicaoUsuario;
	}

}
