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

import br.edu.ifrs.canoas.tads.lds.bean.Alimento;
import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Refeicao;
import br.edu.ifrs.canoas.tads.lds.bean.RefeicaoAlimento;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlimento;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;
import br.edu.ifrs.canoas.tads.lds.bean.Udm;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterCategoriaService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterPesoService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUdmService;

/** 
 * @author Luana
 * @version 21/06/2015
 *
 */

@Named 
@SessionScoped 
public class ManterCategoriaMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static final String URL_MANTER_CATEGORIA = "/private/pages/manterCategoria2.jsf";
	private static final String URL_LISTAR_PESO = "/private/pages/listarPeso.jsf";

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@Inject
	private TipoAtividade tipoAtividade;
	
	@Inject
	private Atividade atividade;
	
	@EJB
	private ManterCategoriaService categoriaService;
		
	/*@Inject
	private PesoUsuario pesoUsuario;
	
	@EJB
	private ManterUdmService manterUdmService;
	
	@EJB
	private ManterPesoService pesoService;
	
	private List<PesoUsuario> pesoUsuarioList;
	private List<Udm> udmLista;
	
	private String criterioPeso;
	
	private Udm udm;	*/
	
	
	/*
	public String initListar() {
		pesoUsuario = new PesoUsuario();
		criterioPeso = "";
		pesoUsuarioList = new ArrayList<PesoUsuario>();
		return URL_LISTAR_PESO;
	}

	public String initManter() {
		udm = new Udm();
		udmLista= this.getUdmLista();
		pesoUsuario = new PesoUsuario();
		pesoUsuario.setPeso(new Peso());
		return URL_MANTER_PESO;
	}
	*/
	
	public String initManter() {
		tipoAtividade = new TipoAtividade();
		atividade = new Atividade();
		atividade.setTipoAtividade(tipoAtividade);
		return URL_MANTER_CATEGORIA;
	}
	
	public void salvaATividade(){			
		if(categoriaService.salvaCategoria(atividade, tipoAtividade))
			this.initManter();
	}
			
	/*GETTERS AND SETTERS*/
	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}


	public ManterCategoriaService getCategoriaService() {
		return categoriaService;
	}

	public void setCategoriaService(ManterCategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}

}