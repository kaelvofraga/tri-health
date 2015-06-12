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
	
	
	//listas exames e tipos de exame
	private List<ExameUrinaUsuario> listaExamesUsuario;	
	private List<TipoAnalise> tiposAnalise;
	
	
	public String initListar() {
		exameUrina = new ExameUrinaUsuario();
		listaExamesUsuario= new ArrayList<>();
		return URL_LISTAR_EXAMEURINA;
	}
	
	public String initManter(){
		exameUrina.setItensExame(new ArrayList<ItemExameUrina>());
		itemExameUrina.setTipoAnalise(new TipoAnalise());
		return URL_MANTER_EXAMEURINA;
	}
	
	public void onSelectTipoAnalise(){
		itemExameUrina.getTipoAnalise();
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		this.itemExameUrina = (ItemExameUrina) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("../../private/pages/manterExameUrina.jsf");
	}
	
	public void adicionarExameAnalisado(){
		if (itemExameUrina != null && !this.exameUrina.getItensExame().contains(itemExameUrina)){
			this.exameUrina.getItensExame().add(itemExameUrina);
		}
		else{
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterExameUrina.insere.erro");
		}
		itemExameUrina= new ItemExameUrina();	
	}

	public boolean isAtualizacao(){
		return exameUrina != null && exameUrina.getId() != null;
	}
	
	public void salvaExame(){
		exameUrina.setUsuario(gerenciarLoginMB.getUsuario());
		exameUrinaService.salvaExameUrinaUsuario(exameUrina);
	}
	
//	public String alteraExameUrina() {
//		exameUrinaService.alteraExameUrina(exameUrina);
//		return URL_LISTAR_USO_TIPOSEXAMEURINA;
//	}	
	
	/*public String excluiExameUrina(){
		if (exameUrinaService.excluiExameUrina(exameUrina)){
			this.busca();
			return URL_LISTAR_USO_TIPOSEXAMEURINA;
		}
		return URL_MANTER_USO_EXAMEURINA;
	}*/
	
	public void busca(){
	listaExamesUsuario = exameUrinaService.busca(this.getDataDe(),this.getDataAte(),criterioExameUrina);
	}
	
	public void clear(){
		exameUrina = new ExameUrinaUsuario();
//		exameUrina.setTipoExameUrina(new TipoExameUrina());
//		examesLista = new ArrayList<>();
	}
	
	
	/*public String novoExameUrina(){
//		this.emListagemAtvs = false;
//		this.clear();
		return URL_MANTER_NOVO_EXAMEURINA;
	}*/
	
	public void exclui() {
		if (itemExameUrina != null && this.exameUrina.getItensExame().contains(itemExameUrina))
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