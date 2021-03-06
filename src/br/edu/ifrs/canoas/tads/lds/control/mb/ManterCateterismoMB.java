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

import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Cateterismo;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;
import br.edu.ifrs.canoas.tads.lds.control.service.GerenciarCateterismosService;

/**
 * Classe para representar a View manterCateterismos.jsf.
 * 
 * Está responder as ações da view.

 * @author Luciano Acosta

 */

@Named
@SessionScoped
public class ManterCateterismoMB implements Serializable {

	private static final long serialVersionUID = 7918766405702133530L;
	private static final String URL_LISTAR_EXAMES = "/private/pages/listarCateterismos.jsf";
	private static final String URL_MANTER_EXAMES = "/private/pages/manterCateterismos.jsf";
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@EJB
	private GerenciarCateterismosService cateterismoService;
	

	@Inject
	private Cateterismo exame;
		
	
	private String criterio = "";
	
	private List<Cateterismo> listExames;
	private List<Medico> listMedico;
	

	@PostConstruct
    public String initListar() {
		listExames = new ArrayList<Cateterismo>();
		listMedico = new ArrayList<Medico>();
		return URL_LISTAR_EXAMES;
	}
	
    public String initManter() {
		exame = new Cateterismo();
		return URL_MANTER_EXAMES;
	}
	
	public void busca() {
		listExames = cateterismoService.busca(this.criterio);
	}
	
	public void clearTable(){
		exame = new Cateterismo();
		this.criterio = "";
		this.busca();
	}

	public String novo(){
		exame = new Cateterismo();
		return URL_MANTER_EXAMES;
	}
	
	public String voltaPraLista(){
		return URL_LISTAR_EXAMES;
	}
	
	public String excluiExame(){
		cateterismoService.excluiExame(exame);
		return URL_LISTAR_EXAMES;
	}
	
	public void alteraExame(){
		cateterismoService.alteraExame(exame);
	}
	
	public void salvaExame() {
		exame.setUsuario(gerenciarLoginMB.getUsuario());
		cateterismoService.salvaCateterismo(exame);
	
	}
	public boolean isEmEdicao(){
		return exame != null && exame.getId() != null;
	}

	public List<Cateterismo> getListExames() {
		this.busca();
		return listExames;
	}
	
	public Cateterismo getExame() {
		return exame;
	}

	public void setExame(Cateterismo exame) {
		this.exame = exame;
	}
	
	public void setCateterismo(Cateterismo exame) {
		this.exame = exame;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	public boolean isAtualizacao(){
		return exame != null && exame.getId() != null;
	}
	
    public void onRowSelect(SelectEvent event) throws IOException {
    	this.exame = (Cateterismo) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext().redirect("manterCateterismos.jsf");
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Exame Desmarcado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    

	public List<Medico> getListMedico() {
		listMedico = cateterismoService.buscaMedicos("");
		return listMedico;
	}

	public void setListMedico(List<Medico> listMedico) {
		this.listMedico = listMedico;
	}
}