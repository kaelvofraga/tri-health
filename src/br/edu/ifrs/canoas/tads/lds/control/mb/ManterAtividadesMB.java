package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterAtividadesService;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

@Named
@SessionScoped
public class ManterAtividadesMB implements Serializable {

	private static final long serialVersionUID = -8771171961658521800L;
	private static final String URL_LISTAR_ATIVIDADES = "/private/pages/listarAtividadesFisicas.jsf";
	private static final String URL_MANTER_ATIVIDADES = "/private/pages/manterAtividadesFisicas.jsf";
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@EJB
	private ManterAtividadesService atvUsuarioService;
	
	//Beans
	@Inject 
	private AtividadeUsuario atividadeUsuario;	
	
	//Lista Atividades do Usuario
	private List<AtividadeUsuario> atividadeUsuarioList;
	private String criterioAtividadeUsuario;

	//Form Atividades do Usuario
	private List<Atividade> atividadeList;
	private List<Atividade> atividadeListFiltrada;
	private List<TipoAtividade> tipoAtividadeList;

	public ManterAtividadesMB() {
	}
	
	@PostConstruct
	public void init(){
		
		/** Busca **/
		if(criterioAtividadeUsuario == null || criterioAtividadeUsuario.length() == 0)
			criterioAtividadeUsuario = "";
		
		/** Listas **/
		if(atividadeUsuarioList == null)
			atividadeUsuarioList = new ArrayList<>();	
		if(tipoAtividadeList == null)
			tipoAtividadeList = atvUsuarioService.buscaNomeTipoAtividades();
		if(atividadeList == null)
			atividadeList = atvUsuarioService.buscaDescricoesAtividades();
		if(atividadeListFiltrada == null){
			atividadeListFiltrada = new ArrayList<>();
			this.filtrarAtividades();
		}	
	}

	public void busca(){
		atividadeUsuarioList = atvUsuarioService.buscaGeral(criterioAtividadeUsuario, gerenciarLoginMB.getUsuario());
	}
	
	private void clear() {
		/** POJO **/
		atividadeUsuario = new AtividadeUsuario();
		
		/** Listas **/
		tipoAtividadeList = atvUsuarioService.buscaNomeTipoAtividades();
		atividadeList = atvUsuarioService.buscaDescricoesAtividades();
		this.filtrarAtividades();		
	}
	
	public void clearTable(){
		/** POJO **/
		atividadeUsuario = new AtividadeUsuario();

		/** Zera critério de filtro **/
		criterioAtividadeUsuario = "";

		/** Limpa filtro na lista atividadesUsuarioList **/
		this.busca();	
	}
	
	public void salvaAtividadeUsuario(){
		atividadeUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		if(atvUsuarioService.salvaAtividadeUsuario(atividadeUsuario))
			this.clear();
	}
	
	public String alteraAtividadeUsuario(){
		atvUsuarioService.alteraAtividadeUsario(atividadeUsuario);
		this.clear();
		return URL_LISTAR_ATIVIDADES;
	}
	
	public String voltarParaListar(){
		this.clear();
		return URL_LISTAR_ATIVIDADES;
	}
	
	public String excluiAtividadeUsuario(){
		atvUsuarioService.excluiAtividadeUsuario(atividadeUsuario);
		this.busca();
		return URL_LISTAR_ATIVIDADES;
	}
	
	public String novaAtividadeUsuario(){
		this.clear();
		return URL_MANTER_ATIVIDADES;
	}
	
	public boolean isAtualizacao(){
		return atividadeUsuario != null && atividadeUsuario.getId() != null;
	}
	
	public void filtrarAtividades(){
		this.atividadeListFiltrada.clear();
		TipoAtividade ta = atividadeUsuario.getAtividade().getTipoAtividade();
		if ( ta != null && ta.getId() != null ){			
			for (int i = 0; i < atividadeList.size(); i++) {
				Atividade atv = atividadeList.get(i);
				if(atv.getTipoAtividade().getId() == ta.getId()) {
					atividadeListFiltrada.add(atv);
				}
			}

		}else{
			atividadeListFiltrada = new ArrayList<>();
		}
	}
	
	public void calculaCalorias(){
		if (atividadeUsuario == null) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Atividade.cadastro.erro.calculaCaloria");
			return;
		}
		
		if(atvUsuarioService.validaDatas(this.atividadeUsuario) == false){
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Atividade.cadastro.erro.dataFinalMenor");
			return;
		}
		
		atividadeUsuario.setDuracao(atvUsuarioService.calculaDuracao(atividadeUsuario));
		
		atividadeUsuario.setCalorias(atvUsuarioService.calculaCaloriasQueimadas(atividadeUsuario));			
	}	
		
	public void onRowSelect(SelectEvent event) throws IOException {
		this.atividadeUsuario = (AtividadeUsuario) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext().redirect("manterAtividadesFisicas.jsf");
    }
	
	
	/*
	 * GETTERS & SETTERS
	 */	
	public AtividadeUsuario getAtividadeUsuario() {
		return atividadeUsuario;
	}

	public void setAtividadeUsuario(AtividadeUsuario atividadeUsuario) {
		this.atividadeUsuario = atividadeUsuario;
	}

	public String getCriterioAtividadeUsuario() {
		return criterioAtividadeUsuario;
	}

	public void setCriterioAtividadeUsuario(String criterioAtividadeUsuario) {
		this.criterioAtividadeUsuario = criterioAtividadeUsuario;
	}

	public List<AtividadeUsuario> getAtividadeUsuarioList() {	
		if(atividadeUsuarioList == null || atividadeUsuarioList.size() == 0)
			this.busca();
		return atividadeUsuarioList;
	}

	public void setAtividadeUsuarioList(List<AtividadeUsuario> atividadeUsuarioList) {
		this.atividadeUsuarioList = atividadeUsuarioList;
	}

	public List<Atividade> getAtividadeList() {
		if (atividadeList == null) 
			atividadeList = atvUsuarioService.buscaDescricoesAtividades();
		return atividadeList;
	}

	public void setAtividadeList(List<Atividade> atividadeList) {
		this.atividadeList = atividadeList;
	}

	public List<TipoAtividade> getTipoAtividadeList() {
		if (tipoAtividadeList == null) 
			tipoAtividadeList = atvUsuarioService.buscaNomeTipoAtividades();
		return tipoAtividadeList;
	}

	public void setTipoAtividadeList(List<TipoAtividade> tipoAtividadeList) {
		this.tipoAtividadeList = tipoAtividadeList;
	}
			
	public List<Atividade> getAtividadeListFiltrada() {
		return atividadeListFiltrada;
	}

	public void setAtividadeListFiltrada(List<Atividade> atividadeListFiltrada) {
		this.atividadeListFiltrada = atividadeListFiltrada;
	}
}
