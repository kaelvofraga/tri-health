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

import br.edu.ifrs.canoas.tads.lds.bean.ComposicaoUsuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterAtividadesService;
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

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@EJB
	private ManterComposicaoService composicaoService;

	@Inject
	private ComposicaoUsuario adiposa;

	@Inject
	private ComposicaoUsuario residual;

	@Inject
	private ComposicaoUsuario muscular;

	@Inject
	private ComposicaoUsuario ossea;

	public String initListar() {
		return URL_LISTAR_COMPOSICOES_CORPORAIS;
	}

	public String initManter() {
		adiposa = new ComposicaoUsuario();
		residual = new ComposicaoUsuario();
		muscular = new ComposicaoUsuario();
		ossea = new ComposicaoUsuario();
		
		return URL_MANTER_COMPOSICOES_CORPORAIS;
	}
	
	public void salvaComposicao(){
		adiposa.setUsuario(gerenciarLoginMB.getUsuario());
		residual.setUsuario(gerenciarLoginMB.getUsuario());
		muscular.setUsuario(gerenciarLoginMB.getUsuario());
		ossea.setUsuario(gerenciarLoginMB.getUsuario());
		if(composicaoService.salvaComposicao(adiposa, residual, muscular, ossea)){
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

	public ComposicaoUsuario getAdiposa() {
		return adiposa;
	}

	public void setAdiposa(ComposicaoUsuario adiposa) {
		this.adiposa = adiposa;
	}

	public ComposicaoUsuario getResidual() {
		return residual;
	}

	public void setResidual(ComposicaoUsuario residual) {
		this.residual = residual;
	}

	public ComposicaoUsuario getMuscular() {
		return muscular;
	}

	public void setMuscular(ComposicaoUsuario muscular) {
		this.muscular = muscular;
	}

	public ComposicaoUsuario getOssea() {
		return ossea;
	}

	public void setOssea(ComposicaoUsuario ossea) {
		this.ossea = ossea;
	}
	
}
