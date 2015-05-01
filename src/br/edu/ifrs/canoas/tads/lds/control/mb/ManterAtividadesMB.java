package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterAtividadesService;

@Named
@SessionScoped
public class ManterAtividadesMB implements Serializable {

	private static final long serialVersionUID = -8771171961658521800L;
	private static final String URL_LISTAR_ATIVIDADES = "/private/pages/listarAtividadesFisicas.jsf";
	private static final String URL_MANTER_ATIVIDADES = "/private/pages/manterAtividadesFisicas.jsf";
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@EJB
	private ManterAtividadesService atvsService; //< atv = atividade 
	
	private AtividadeUsuario atividadeUsuario;
	
	private boolean emListagemAtvs;

	//Lista Atividades do Usuario
	private List<AtividadeUsuario> atividadeUsuarioList;
	private String criterioAtv;
	
	//Form Atividades do Usuario
	private List<Atividade> atividades;
	private List<TipoAtividade> tipoAtvs;

	public ManterAtividadesMB() {
	}
	
	@PostConstruct
	public void init(){
		atividadeUsuario = new AtividadeUsuario();
		criterioAtv = "";
		atividadeUsuarioList = new ArrayList<>();
	}

	public void busca(){
		atividadeUsuarioList = atvsService.buscaGeral(criterioAtv, gerenciarLoginMB.getUsuario());
	}
	
	public List<Atividade> completeAtividades(String query){
		if (atividades == null) 
			atividades = atvsService.buscaDescricoesAtividades();

		List<Atividade> atividadesFiltradas = new ArrayList<>();
         
        for (int i = 0; i < atividades.size(); i++) {
            Atividade atv = atividades.get(i);
            if(atv.getDescricao().trim().toLowerCase().startsWith(query)) {
            	atividadesFiltradas.add(atv);
            }
        }
        return atividadesFiltradas;
	}
	
	public List<TipoAtividade> completeTipoAtividade(String query){
		if (tipoAtvs == null)
			tipoAtvs = atvsService.buscaNomeTipoAtividades();	
		
		List<TipoAtividade> tiposFiltradas = new ArrayList<>();
        
        for (int i = 0; i < tipoAtvs.size(); i++) {
            TipoAtividade tipo = tipoAtvs.get(i);
            if(tipo.getNome().trim().toLowerCase().startsWith(query)) {
            	tiposFiltradas.add(tipo);
            }
        }		
		return tiposFiltradas;
	}
	
	public void salvaAtividadeUsuario(){
		atividadeUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		atvsService.salvaAtividadeUsuario(atividadeUsuario);
		this.init();
	}
	
	public String alteraAtividadeUsuario(){
		atvsService.alteraAtividadeUsario(atividadeUsuario);
		return URL_LISTAR_ATIVIDADES;
	}
	
	public String excluiAtividadeUsuario(){
		atvsService.excluiAtividadeUsuario(atividadeUsuario);
		this.busca();
		return URL_LISTAR_ATIVIDADES;
	}
	
	public String editarAtividadeUsuario(AtividadeUsuario atividadeUsuario){
		this.atividadeUsuario = atividadeUsuario;
		this.emListagemAtvs = false;
		return URL_MANTER_ATIVIDADES;
	}
	
	public boolean isAtualizacao(){
		return atividadeUsuario != null && atividadeUsuario.getId() != null;
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
		return criterioAtv;
	}

	public void setCriterioAtividadeUsuario(String criterioAtv) {
		this.criterioAtv = criterioAtv;
	}

	public List<AtividadeUsuario> setAtividadeUsuarioList() {
		return atividadeUsuarioList;
	}

	public void setAtividadeUsuarioList(List<AtividadeUsuario> atividadeUsuarioList) {
		this.atividadeUsuarioList = atividadeUsuarioList;
	}

	public List<Atividade> getAtividades() {
		if (atividades == null) 
			atividades = atvsService.buscaDescricoesAtividades();
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public List<TipoAtividade> getTipoAtividades() {
		return tipoAtvs;
	}

	public void setTipoAtividades(List<TipoAtividade> tipoAtvs) {
		this.tipoAtvs = tipoAtvs;
	}

	public boolean isEmListagemAtividadeUsuario() {
		return emListagemAtvs;
	}

	public void setEmListagemAtividadeUsuario(boolean emListagemAtvs) {
		this.emListagemAtvs = emListagemAtvs;
	}	

}
