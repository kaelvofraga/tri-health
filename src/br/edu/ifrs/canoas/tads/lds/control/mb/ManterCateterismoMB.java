package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.edu.ifrs.canoas.tads.lds.bean.Cateterismo;
import br.edu.ifrs.canoas.tads.lds.control.service.GerenciarCateterismosService;
import br.edu.ifrs.canoas.tads.lds.model.dao.CateterismosDAO;

@Named
@RequestScoped
public class ManterCateterismoMB implements Serializable {

	private static final long serialVersionUID = 7918766405702133530L;

	@Inject
	private Cateterismo exame;
		
	@EJB
	private GerenciarCateterismosService cateterismoService;
	
	private String criterio = "";
	private List<Cateterismo> listExames;

	@PostConstruct
    public void init() {
		listExames = new ArrayList<Cateterismo>();
	}
	
	public void busca() {
		listExames = cateterismoService.busca("");
		System.out.println(listExames.size());
	}

	public void salva() {
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

	public void setCateterismo(Cateterismo exame) {
		this.exame = exame;
	}

	public String getCriterio() {
		return criterio;
	}

	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Exame Selecionado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Exame Desmarcado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}