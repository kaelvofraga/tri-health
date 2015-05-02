package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.PressaoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.PressaoArterial;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterPressaoService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUsoMedicamentoService;

@Named
@SessionScoped
public class ManterPressaoMB implements Serializable {

	private static final long serialVersionUID = 1695998945721419655L;
	private static final String URL_LISTAR_PRESSAO = "/private/pages/listarPressaoArterial.jsf";
	private static final String URL_MANTER_PRESSAO = "/private/pages/manterPressaoArterial.jsf";
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@Inject 
	private PressaoUsuario pressaoUsuario;
	
	@EJB
	private ManterPressaoService pressaoService;
	
	
	private List<PressaoUsuario> listaDePressao;
	
	private List<PressaoArterial> pressoes;
	
	public void inicializa() {
		pressaoUsuario = new PressaoUsuario();
		listaDePressao = new ArrayList<>();
	}
	
	
	public String alteraPressao() {
		return "manterPressaoService";
	}
	
	
	public void salvaPressao(){
		pressaoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		pressaoService.salvaPressaoUsuario(pressaoUsuario);
		this.inicializa();
	}
	
	
	public boolean isAtualizacao(){
		return pressaoUsuario != null && pressaoUsuario.getId() != null;
	}


	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}


	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}


	public PressaoUsuario getPressaoUsuario() {
		return pressaoUsuario;
	}


	public void setPressaoUsuario(PressaoUsuario pressaoUsuario) {
		this.pressaoUsuario = pressaoUsuario;
	}


	public ManterPressaoService getPressaoService() {
		return pressaoService;
	}


	public void setPressaoService(ManterPressaoService pressaoService) {
		this.pressaoService = pressaoService;
	}


	public List<PressaoUsuario> getListaDePressao() {
		return listaDePressao;
	}


	public void setListaDePressao(List<PressaoUsuario> listaDePressao) {
		this.listaDePressao = listaDePressao;
	}


	public List<PressaoArterial> getPressoes() {
		return pressoes;
	}


	public void setPressoes(List<PressaoArterial> pressoes) {
		this.pressoes = pressoes;
	}
	

}
