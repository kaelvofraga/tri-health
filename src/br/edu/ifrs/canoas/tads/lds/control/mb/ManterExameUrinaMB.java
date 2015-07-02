package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.primefaces.event.SelectEvent;
import br.edu.ifrs.canoas.tads.lds.bean.ExameUrinaUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.ItemExameUrina;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAnalise;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterExameUrinaService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterTipoAnaliseService;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

@Named
@SessionScoped
public class ManterExameUrinaMB implements Serializable{

	private static final long serialVersionUID = 7113326743475818284L;
	private static final String URL_LISTAR_EXAMEURINA = "/private/pages/listarExameUrina.jsf";
	private static final String URL_MANTER_EXAMEURINA = "/private/pages/manterExameUrina.jsf";
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@Inject
	private ExameUrinaUsuario exameUrina;
	
	@Inject
	private ItemExameUrina itemExameUrina;
	
	@EJB
	private ManterExameUrinaService exameUrinaService;
	
	@EJB
	private ManterTipoAnaliseService tipoAnaliseService;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataDe;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAte;
	
	private String criterioExameUrina;
	
	private List<ExameUrinaUsuario> listaExamesUsuario;
	
	private List<TipoAnalise> tiposAnalise;
	
	
	/** 
	 * @brief Metodo que inicializa as variaveis ao ser selecionada a opção de listar
	 * os exames de urina do usuário.	 		  
	 * @param void
	 * @return String
	 * */
	public String initListar() {
		exameUrina = new ExameUrinaUsuario();
		listaExamesUsuario= new ArrayList<>();
		criterioExameUrina="";
		dataDe= null;
		dataAte= null;
		return URL_LISTAR_EXAMEURINA;
	}
	
	/** 
	 * @brief Metodo que inicializa as variaveis ao ser selecionada a opção de cadastrar
	 * os exames de urina do usuário.	 		  
	 * @param void
	 * @return String
	 * */
	public String initManter(){
		exameUrina = new ExameUrinaUsuario();
		exameUrina.setData(null);
		exameUrina.setItensExame(new ArrayList<ItemExameUrina>());
		itemExameUrina.setTipoAnalise(new TipoAnalise());
		return URL_MANTER_EXAMEURINA;
	}
	
	/** 
	 * @brief Metodo que seta o tipo de exame selecionado. 		  
	 * @param void
	 * @return void
	 * */
	public void onSelectTipoAnalise(){
		itemExameUrina.getTipoAnalise();
	}
	
	/** 
	 * @brief Metodo que realiza o evento de seleção da linha da tabela que lista
	 *  os exames de urina do usuário.	 		  
	 * @param event (SelectEvent)
	 * @return void
	 * */
	public void onRowSelect(SelectEvent event) throws IOException {
		this.exameUrina = (ExameUrinaUsuario) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("../../private/pages/manterExameUrina.jsf");
	}
	
	/** 
	 * @brief Metodo que adiciona a sublista do exame de urina o item selecionado. 		  
	 * @param void
	 * @return void
	 * */
	public void adicionarExameAnalisado(){
		if (itemExameUrina != null && !this.exameUrina.getItensExame().contains(itemExameUrina)){
			this.exameUrina.getItensExame().add(itemExameUrina);
		}
		else{
			Mensagens.define(FacesMessage.SEVERITY_ERROR,"manterExameUrina.insere.erro");
		}
		itemExameUrina= new ItemExameUrina();	
	}

	/** 
	 * @brief Metodo que verifica se o exameUrinaUsuario está sendo atualizado
	 * ou é um novo cadastro. 		  
	 * @param void
	 * @return boolean
	 * */
	public boolean isAtualizacao(){
		return exameUrina != null && exameUrina.getId() != null;
	}
	
	/** 
	 * @brief Metodo que verifica se o itemExameUrina está sendo atualizado
	 * ou é um novo cadastro. 		  
	 * @param void
	 * @return boolean  
	 * */
	public boolean isAtualizandoItem(){
		return itemExameUrina != null && itemExameUrina.getId()!= null;
	} 
	
	public void alteraItemExame(){
		exameUrinaService.alteraItemExame(itemExameUrina);
		itemExameUrina = new ItemExameUrina();
	}
	
	/** 
	 * @brief Metodo que salva o exameUrinaUsuario no banco de dados	 		  
	 * @param void
	 * @return void
	 * */
	public void salvaExame(){
		exameUrina.setUsuario(gerenciarLoginMB.getUsuario());
		if(exameUrinaService.salvaExameUrinaUsuario(exameUrina)==true){
			this.initManter();
		}
	}
	
	/** 
	 * @brief Metodo que faz alterações no banco de dados das informações de exameUrinaUsuario.
	 * @param void
	 * @return String
	 * */
	public String alteraExame() {
		if(exameUrinaService.alteraExameUrinaUsuario(exameUrina)==true){
		return URL_LISTAR_EXAMEURINA;
		}
		else
			return URL_MANTER_EXAMEURINA;	
	}	
		
	/** 
	 * @brief Metodo que exclui do banco de dados o exameUrinaUsuario, retorna a URL 
	 * da pagina que sera redirecionado.	 		  
	 * @param void
	 * @return String
	 * */
	public String excluiExame(){
		if (exameUrinaService.excluiExameUrinaUsuario(exameUrina)){
			this.busca();
			return URL_LISTAR_EXAMEURINA;
		}
		return URL_MANTER_EXAMEURINA;
	}
	
	/** 
	 * @brief Metodo que realiza a busca da view de listagem de exames de urina do usuario.	 		  
	 * @param void
	 * @return void
	 * */
	public void busca(){
	listaExamesUsuario = exameUrinaService.busca(this.getDataDe(),this.getDataAte(),criterioExameUrina);
	}
	
	/** 
	 * @brief Metodo que exclui da sublista de items do exame urina o item selecionado.	 		  
	 * @param void
	 * @return void
	 * */
	public void excluiItem() {
		if (itemExameUrina != null && this.exameUrina.getItensExame().contains(itemExameUrina))
			if(isAtualizandoItem()){
			exameUrinaService.excluiItemExame(itemExameUrina);
			}
			this.exameUrina.getItensExame().remove(itemExameUrina);
		itemExameUrina= new ItemExameUrina();	
	}

	/*GETTERS & SETTERS*/
	
	public ExameUrinaUsuario getExameUrina() {
		return exameUrina;
	}

	public void setExameUrina(ExameUrinaUsuario exameUrina) {
		this.exameUrina = exameUrina;
	}
	
	public List<ExameUrinaUsuario> getListaExamesUsuario() {
		return listaExamesUsuario;
	}

	public void setListaExamesUsuario(List<ExameUrinaUsuario> listaExamesUsuario) {
		this.listaExamesUsuario = listaExamesUsuario;
	}

	public List<TipoAnalise> getTiposAnalise() {
		tiposAnalise = tipoAnaliseService.buscaTipoAnalise();
		return tiposAnalise;
	}

	public void setTiposAnalise(List<TipoAnalise> tiposAnalise) {
		this.tiposAnalise = tiposAnalise;
	}

	public String getCriterioExameUrina() {
		return criterioExameUrina;
	}

	public void setCriterioExameUrina(String criterioExameUrina) {
		this.criterioExameUrina = criterioExameUrina;
	}

	public ManterExameUrinaService getExameUrinaService() {
		return exameUrinaService;
	}

	public void setExameUrinaService(ManterExameUrinaService exameUrinaService) {
		this.exameUrinaService = exameUrinaService;
	}

	public ItemExameUrina getItemExameUrina() {
		return itemExameUrina;
	}

	public void setItemExameUrina(ItemExameUrina itemExameUrina) {
		this.itemExameUrina = itemExameUrina;
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
}