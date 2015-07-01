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
	 * @author André
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
		tiposAnalise = tipoAnaliseService.buscaTipoAnalise();
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
	/**
	 * {@code} Método para iniciar a interface de manter exame de sangue
	 * @return interface de manter exame de sangue
	 */
	@PostConstruct
	public String initManter(){
		usuarioExame=new UsuarioExame();
		usuarioExame.setUsuario(gerenciarLoginMB.getUsuario());
		usuarioExame.setData(null);
		usuarioExame.setObservacao(null);
		usuarioExame.setItensExame(new ArrayList<ItemExameSangue>());
		itemExameSangue.setTipoAnalise(new TipoAnalise());
		return URL_MANTER_EXAMESANGUE;		
	}
	/**
	 * {@code} Método para iniciar a interface de listar exame de sangue
	 * @return interface de listagem dos exames de sangue
	 */
	public String initListar() {
		usuarioExame = new UsuarioExame();
		usuarioExame.setUsuario(gerenciarLoginMB.getUsuario());
		listaExames = new ArrayList<>();
		dataDe = null;
		dataAte = new Date();
		return URL_LISTAR_EXAMESANGUE;
	}
	/**
	 * {@code} Método para obter o tipo de análise
	 * @return void
	 */
	public void onSelectTipoAnalise(){
		itemExameSangue.getTipoAnalise();
	}
	/**
	 * {@code} Método utilizado na seleção do exame realizado quando este está na listagem de exames de sangue
	 * @return void
	 * @param event
	 * @throws IOException
	 */
	public void onRowSelect(SelectEvent event) throws IOException {
		this.usuarioExame = (UsuarioExame) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("../../private/pages/manterExameSangue.jsf");
	}	
	/**
	 * {@code} Método para adicionar tipo de análise e seu resultado ao exame de sangue 
	 * @return void
	 */
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
	/**
	 * {@code} Método que busca os exames realizados num intervalo de data
	 * @return void
	 */
	public void busca(){
		listaExames = exameSangueService.busca(this.getDataDe(),this.getDataAte());
	}
	/**
	 * {@code} Método que salva o exame de sangue com os tipos de análise realizados
	 * @return void
	 */
	public void salvaExame(){
		usuarioExame.setUsuario(gerenciarLoginMB.getUsuario());
		exameSangueService.salvaExameSangueUsuario(usuarioExame);
		initManter();
	}
	
	public String alteraExame() {
		if (exameSangueService.alteraExameSangue(usuarioExame)==true){
			return URL_LISTAR_EXAMESANGUE;
		}else
			return URL_MANTER_EXAMESANGUE;
	}
	
	public String excluiExame(){
		if (exameSangueService.excluiExameSangueUsuario(usuarioExame)){
			this.busca();
			return URL_LISTAR_EXAMESANGUE;
		}
		return URL_MANTER_EXAMESANGUE;
	}
	/**
	 * {@code} Método que exclui o tipo de análise incluso na lista antes do exame ser salvo
	 * @return void
	 */
	public void exclui() {
		if (itemExameSangue != null && this.usuarioExame.getItensExame().contains(itemExameSangue))
			this.usuarioExame.getItensExame().remove(itemExameSangue);		
		itemExameSangue= new ItemExameSangue();
	}
	
	public boolean isAtualizandoItem(){
		return itemExameSangue != null && usuarioExame.getItensExame().contains(itemExameSangue) && itemExameSangue.getId()!= null;
	}
	
	public boolean isAtualizacao(){
		return usuarioExame != null && usuarioExame.getId() != null;
	}
	
	
	/*public void altera() {
	if (itemExameSangue != null && this.usuarioExame.getItensExame().contains(itemExameSangue))
		this.usuarioExame.getData();
		this.usuarioExame.getItensExame().remove(itemExameSangue);
	itemExameSangue= new ItemExameSangue();
}*/
	
	

}
