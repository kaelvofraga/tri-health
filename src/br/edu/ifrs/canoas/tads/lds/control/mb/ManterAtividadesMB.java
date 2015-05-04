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
import org.primefaces.event.UnselectEvent;

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
	@Inject private AtividadeUsuario atividadeUsuario;	
	@Inject private Atividade atividade;
	@Inject private TipoAtividade tipoAtividade;
	
	private boolean emListagemAtvs;

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
		
		/** POJO **/
//		if(atividadeUsuario == null)
//			atividadeUsuario = new AtividadeUsuario();
//		if(atividade == null)
//			atividade = new Atividade();	
//		if(tipoAtividade == null)
//			tipoAtividade = new TipoAtividade();
		
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
		if(atividadeListFiltrada == null)
			atividadeListFiltrada = new ArrayList<>();
	}

	public void busca(){
		atividadeUsuarioList = atvUsuarioService.buscaGeral(criterioAtividadeUsuario, gerenciarLoginMB.getUsuario());
	}
	
	/*public List<Atividade> completeAtividades(String query){
		if (atividadeList == null) 
			atividadeList = atvUsuarioService.buscaDescricoesAtividades();

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
			tipoAtividadeList = atvUsuarioService.buscaNomeTipoAtividades();	
		
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
		if(atvUsuarioService.salvaAtividadeUsuario(atividadeUsuario))
			this.clear();
	}
	
	private void clear() {
		/** POJO **/
		atividadeUsuario = new AtividadeUsuario();
		atividade = new Atividade();	
		tipoAtividade = new TipoAtividade();

		/** Listas **/
		tipoAtividadeList = atvUsuarioService.buscaNomeTipoAtividades();
		atividadeList = atvUsuarioService.buscaDescricoesAtividades();
		atividadeListFiltrada = new ArrayList<>();		
	}
	
	public void clearTable(){
		/** POJO **/
		atividadeUsuario = new AtividadeUsuario();

		/** Zera crit�rio de filtro **/
		criterioAtividadeUsuario = "";

		/** Limpa filtro na lista atividadesUsuarioList **/
		this.busca();	
	}

	public String alteraAtividadeUsuario(){
		atvUsuarioService.alteraAtividadeUsario(atividadeUsuario);
		this.clear();
		return URL_LISTAR_ATIVIDADES;
	}
	
	public String excluiAtividadeUsuario(){
		atvUsuarioService.excluiAtividadeUsuario(atividadeUsuario);
		this.busca();
		return URL_LISTAR_ATIVIDADES;
	}
	
	public String editarAtividadeUsuario(){
		this.emListagemAtvs = false;
		this.tipoAtividade = this.atividadeUsuario.getAtividade().getTipoAtividade();
		this.atividade = this.atividadeUsuario.getAtividade();
		return URL_MANTER_ATIVIDADES;
	}
	
	public String novaAtividadeUsuario(){
		this.emListagemAtvs = false;
		this.clear();
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
	
	public void onSelectAtividade(){
		atividadeUsuario.setAtividade(atividade);
	}
	
	public void onDataChange(){
		if(atividadeUsuario.getDataFim() != null && atividadeUsuario.getDataInicio() != null){
			if(atvUsuarioService.validaData(this.atividadeUsuario) == false){
				this.atividadeUsuario.setDataInicio(null);
				this.atividadeUsuario.setDataFim(null);
			}
		}
	}
	
	public void calculaCalorias(){
		if (atividadeUsuario == null) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Atividade.cadastro.erro.calculaCaloria");
			return;
		}	
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
