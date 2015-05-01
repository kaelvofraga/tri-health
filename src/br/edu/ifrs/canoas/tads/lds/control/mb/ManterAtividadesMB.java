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
	
	//Beans
	private AtividadeUsuario atividadeUsuario;	
	private Atividade atividade;
	private TipoAtividade tipoAtividade;
	
	private boolean emListagemAtvs;

	//Lista Atividades do Usuario
	private List<AtividadeUsuario> atividadeUsuarioList;
	private String criterioAtv;

	//Form Atividades do Usuario
	private List<Atividade> atividadeList;
	private List<Atividade> atividadeListFiltrada;
	private List<TipoAtividade> tipoAtividadeList;

	public ManterAtividadesMB() {
	}
	
	@PostConstruct
	public void init(){
		atividadeUsuario = new AtividadeUsuario();
		atividade = new Atividade();	
		tipoAtividade = new TipoAtividade();
		
		criterioAtv = "";
		
		atividadeUsuarioList = new ArrayList<>();	
		tipoAtividadeList = atvsService.buscaNomeTipoAtividades();
		atividadeList = atvsService.buscaDescricoesAtividades();
		atividadeListFiltrada = new ArrayList<>();
	}

	public void busca(){
		atividadeUsuarioList = atvsService.buscaGeral(criterioAtv, gerenciarLoginMB.getUsuario());
	}
	
	/*public List<Atividade> completeAtividades(String query){
		if (atividadeList == null) 
			atividadeList = atvsService.buscaDescricoesAtividades();

		List<Atividade> atividadeListFiltradas = new ArrayList<>();
         
        for (int i = 0; i < atividadeList.size(); i++) {
            Atividade atv = atividadeList.get(i);
            if(atv.getDescricao().trim().toLowerCase().startsWith(query)) {
            	atividadeListFiltradas.add(atv);
            }
        }
        return atividadeListFiltradas;
	}
	
	public List<TipoAtividade> completeTipoAtividade(String query){
		if (tipoAtividadeList == null)
			tipoAtividadeList = atvsService.buscaNomeTipoAtividades();	
		
		List<TipoAtividade> tiposFiltradas = new ArrayList<>();
        
        for (int i = 0; i < tipoAtividadeList.size(); i++) {
            TipoAtividade tipo = tipoAtividadeList.get(i);
            if(tipo.getNome().trim().toLowerCase().startsWith(query)) {
            	tiposFiltradas.add(tipo);
            }
        }		
		return tiposFiltradas;
	}*/
	
	public void salvaAtividadeUsuario(){
		atividadeUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		atividadeUsuario.setAtividade(atividade);
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
	
	public void onTipoAtividadeChange(){
		
		atividadeListFiltrada.clear();
		
		if (tipoAtividade != null && tipoAtividade.getId() != null){
			for (int i = 0; i < atividadeList.size(); i++) {
				Atividade atv = atividadeList.get(i);
				if(atv.getTipoAtividade().getId() == tipoAtividade.getId()) {
					atividadeListFiltrada.add(atv);
				}
			}						
		}else{
			atividadeListFiltrada = new ArrayList<>();
		}		
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

	public List<Atividade> getAtividadeList() {
		if (atividadeList == null) 
			atividadeList = atvsService.buscaDescricoesAtividades();
		return atividadeList;
	}

	public void setAtividadeList(List<Atividade> atividadeList) {
		this.atividadeList = atividadeList;
	}

	public List<TipoAtividade> getTipoAtividadeList() {
		if (tipoAtividadeList == null) 
			tipoAtividadeList = atvsService.buscaNomeTipoAtividades();
		return tipoAtividadeList;
	}

	public void setTipoAtividadeList(List<TipoAtividade> tipoAtividadeList) {
		this.tipoAtividadeList = tipoAtividadeList;
	}

	public boolean isEmListagemAtividadeUsuario() {
		return emListagemAtvs;
	}

	public void setEmListagemAtividadeUsuario(boolean emListagemAtvs) {
		this.emListagemAtvs = emListagemAtvs;
	}
	

	public Atividade getAtividade() {
		return atividade;
	}
	

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
		
	public List<Atividade> getAtividadeListFiltrada() {
		return atividadeListFiltrada;
	}

	public void setAtividadeListFiltrada(List<Atividade> atividadeListFiltrada) {
		this.atividadeListFiltrada = atividadeListFiltrada;
	}
	
	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}	

}
