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
	
	private AtividadeUsuario userAtv;
	
	private boolean emListagemAtvs;

	//Lista Atividades do Usuario
	private List<AtividadeUsuario> userAtvList;
	private String criterioAtv;
	
	//Form Atividades do Usuario
	private List<Atividade> atividades;
	private List<TipoAtividade> tipoAtvs;

	public ManterAtividadesMB() {
	}
	
	@PostConstruct
	public void init(){
		userAtv = new AtividadeUsuario();
		criterioAtv = "";
		userAtvList = new ArrayList<>();
	}

	public void busca(){
		userAtvList = atvsService.buscaGeral(criterioAtv, gerenciarLoginMB.getUsuario());
	}
	
	public List<Atividade> completeAtividades(String query){
		if (atividades == null) 
			atividades = atvsService.buscaDescricoesAtividades(query);

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
		userAtv.setUsuario(gerenciarLoginMB.getUsuario());
		atvsService.salvaAtividadeUsuario(userAtv);
		this.init();
	}
	
	public String alteraAtividadeUsuario(){
		atvsService.alteraAtividadeUsario(userAtv);
		return URL_LISTAR_ATIVIDADES;
	}
	
	public String excluiAtividadeUsuario(){
		atvsService.excluiAtividadeUsuario(userAtv);
		this.busca();
		return URL_LISTAR_ATIVIDADES;
	}
	
	public String editarAtividadeUsuario(AtividadeUsuario atividadeUsuario){
		this.userAtv = atividadeUsuario;
		this.emListagemAtvs = false;
		return URL_MANTER_ATIVIDADES;
	}
	
	public boolean isAtualizacao(){
		return userAtv != null && userAtv.getId() != null;
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	
	public AtividadeUsuario getAtividadeUsuario() {
		return userAtv;
	}

	public void setAtividadeUsuario(AtividadeUsuario userAtv) {
		this.userAtv = userAtv;
	}

	public String getCriterioAtividadeUsuario() {
		return criterioAtv;
	}

	public void setCriterioAtividadeUsuario(String criterioAtv) {
		this.criterioAtv = criterioAtv;
	}

	public List<AtividadeUsuario> getAtividadeUsuarios() {
		return userAtvList;
	}

	public void setAtividadeUsuarios(List<AtividadeUsuario> userAtvList) {
		this.userAtvList = userAtvList;
	}

	public List<Atividade> getAtividades() {
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
