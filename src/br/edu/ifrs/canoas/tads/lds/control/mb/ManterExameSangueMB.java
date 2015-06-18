package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.primefaces.event.SelectEvent;

import br.edu.ifrs.canoas.tads.lds.bean.ItemExameSangue;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAnalise;
import br.edu.ifrs.canoas.tads.lds.bean.UsuarioExame;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterExameSangueService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterTipoAnaliseService;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

@Named
@SessionScoped
public class ManterExameSangueMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7429065292403103386L;
	private static final String URL_LISTAR_EXAMESANGUE = "/private/pages/listarExameSangue.jsf";
	private static final String URL_MANTER_EXAMESANGUE = "/private/pages/manterExameSangue.jsf";
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private UsuarioExame usuarioExame;
	
	@SuppressWarnings("cdi-ambiguous-dependency")
	@Inject
	private ItemExameSangue itemExameSangue;
	
	@EJB
	private ManterExameSangueService exameSangueService;
	
	@EJB
	private ManterTipoAnaliseService tipoAnaliseService;
	
	private String criterioExameSangue;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDe;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAte;
	
	
//listas exames e tipos de análises
	private List<UsuarioExame> listaExames;	
	private List<TipoAnalise> tiposAnalise;
	
//getters and setters
	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}
	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}
	public UsuarioExame getUsuarioExame() {
		return usuarioExame;
	}
	public void setUsuarioExame(UsuarioExame usuarioExame) {
		this.usuarioExame = usuarioExame;
	}
	public ItemExameSangue getItemExameSangue() {
		return itemExameSangue;
	}
	public void setItemExameSangue(ItemExameSangue itemExameSangue) {
		this.itemExameSangue = itemExameSangue;
	}
	public ManterExameSangueService getExameSangueService() {
		return exameSangueService;
	}
	public void setExameSangueService(ManterExameSangueService exameSangueService) {
		this.exameSangueService = exameSangueService;
	}
	public ManterTipoAnaliseService getTipoAnaliseService() {
		return tipoAnaliseService;
	}
	public void setTipoAnaliseService(ManterTipoAnaliseService tipoAnaliseService) {
		this.tipoAnaliseService = tipoAnaliseService;
	}
	public String getCriterioExameSangue() {
		return criterioExameSangue;
	}
	public void setCriterioExameSangue(String criterioExameSangue) {
		this.criterioExameSangue = criterioExameSangue;
	}
	public List<UsuarioExame> getListaExames() {
		return listaExames;
	}
	public void setListaExames(List<UsuarioExame> listaExames) {
		this.listaExames = listaExames;
	}
	public List<TipoAnalise> getTiposAnalise() {
		return tiposAnalise;
	}
	public void setTiposAnalise(List<TipoAnalise> tiposAnalise) {
		this.tiposAnalise = tiposAnalise;
	}
	
	public Date getDataDe() {
		return dataDe;
	}
	public void setDataDe(Date dataDe) {
		this.dataDe = dataDe;
	}
	public Date getDataAte() {
		return dataAte;
	}
	public void setDataAte(Date dataAte) {
		this.dataAte = dataAte;
	}
	
//métodos
	@PostConstruct
	public String initManter(){
		gerenciarLoginMB.getUsuario().getNome().toUpperCase();
		usuarioExame.setItensExame(new ArrayList<ItemExameSangue>());
		itemExameSangue.setTipoAnalise(new TipoAnalise());
		return URL_MANTER_EXAMESANGUE;		
	}
	
	public String initListar() {
		usuarioExame = new UsuarioExame();
		listaExames= new ArrayList<>();
		return URL_LISTAR_EXAMESANGUE;
	}
	
	public void onSelectTipoAnalise(){
		itemExameSangue.getTipoAnalise();
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		this.itemExameSangue = (ItemExameSangue) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("../../private/pages/manterExameSangue.jsf");
	}
	
	public void exclui() {
		if (itemExameSangue != null && this.usuarioExame.getItensExame().contains(itemExameSangue))
			this.usuarioExame.getItensExame().remove(itemExameSangue);
		itemExameSangue= new ItemExameSangue();	

	}
	
	public void salvaExame(){
		usuarioExame.setUsuario(gerenciarLoginMB.getUsuario());
		exameSangueService.salvaExameSangueUsuario(usuarioExame);
		this.clear();
	}
	
	public void adicionarExameAnalisado(){
		if (itemExameSangue != null && !this.usuarioExame.getItensExame().contains(itemExameSangue)){
			this.usuarioExame.getItensExame().add(itemExameSangue);
		}
		else{
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterExameSangue.insere.erro");
		}
		itemExameSangue= new ItemExameSangue();	
	}
	
	public boolean isAtualizacao(){
		return usuarioExame != null && usuarioExame.getId() != null;
	}
	
	public void busca(){
		listaExames = exameSangueService.busca(this.getDataDe(),this.getDataAte(),criterioExameSangue);
		}
		
	public void clear(){
		usuarioExame = new UsuarioExame();
		//usuarioExame.setItensExame(new ArrayList<ItemExameSangue>());
		//itemExameSangue.setTipoAnalise(new TipoAnalise());
	}
	

	
	

}
