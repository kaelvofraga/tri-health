package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.edu.ifrs.canoas.tads.lds.bean.Peso;
import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Udm;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterPesoService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUdmService;

/** 
 * @author Luana
 * @version 06/05/2015
 * @author Alisson Lorscheiter
 * @version 10/06/2015
 * Adição dos metodos,initManter e initListar.
 * Criação metodo busca.
 * Alterações metodo de salvar.
 *
 */

@Named 
@SessionScoped 
public class ManterPesoMB implements Serializable {
	
	private static final long serialVersionUID = 8840982087710515671L;
	private static final String URL_MANTER_PESO = "/private/pages/manterPeso.jsf";
	private static final String URL_LISTAR_PESO = "/private/pages/listarPeso.jsf";

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@Inject
	private PesoUsuario pesoUsuario;
	
	@EJB
	private ManterUdmService manterUdmService;
	
	@EJB
	private ManterPesoService pesoService;
	
	private List<PesoUsuario> pesoUsuarioList;
	private List<Udm> udmLista;
	
	private String criterioPeso;
	
	private Udm udm;	
	
	public String initListar() {
		pesoUsuario = new PesoUsuario();
		criterioPeso = "";
		pesoUsuarioList = new ArrayList<PesoUsuario>();
		return URL_LISTAR_PESO;
	}

	public String initManter() {
		udm = new Udm();
		udmLista= this.getUdmLista();
		pesoUsuario = new PesoUsuario();
		pesoUsuario.setPeso(new Peso());
		return URL_MANTER_PESO;
	}
	
	public boolean isAtualizacao() {
		return pesoUsuario != null && pesoUsuario.getId() != null;
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		this.pesoUsuario = (PesoUsuario)event.getObject();
		FacesContext.getCurrentInstance().getExternalContext().redirect("manterPeso.jsf");
    }	
	
	/* Metodo de busca da view Listar */
	public void busca() {
		pesoUsuarioList = pesoService.busca(criterioPeso);
	}

	public void salvaPeso(){
		pesoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		pesoService.salvaPesoUsuario(pesoUsuario);
		this.initManter();
	}
	
	public String alteraPeso() {
		pesoService.alteraPesoUsuario(pesoUsuario);
		return URL_LISTAR_PESO;
	}
	
	public String excluiPeso() {
		if (pesoService.excluiPesoUsuario(pesoUsuario)) {
			this.busca();
			return URL_LISTAR_PESO;
		}
		return URL_MANTER_PESO;
	}

	public void onSelectUdm(){
		pesoUsuario.setUdm(udm);
	}
	
	
	//Getters e setters
	
	public List<Udm> getUdmLista() {
		if(udmLista == null)
			udmLista = manterUdmService.buscaUdm();
		return udmLista;
	}
	
	public void setUdmLista(List<Udm> udmLista) {
		this.udmLista = udmLista;
	}
	
	public String getCriterioPeso() {
		return criterioPeso;
	}

	public void setCriterioPeso(String criterioPeso) {
		this.criterioPeso = criterioPeso;
	}
	
	public PesoUsuario getPesoUsuario() {
		return pesoUsuario;
	}

	public void setPesoUsuario(PesoUsuario pesoUsuario) {
		this.pesoUsuario = pesoUsuario;
	}

	public List<PesoUsuario> getPesoUsuarioList() {
		if(pesoUsuarioList == null || pesoUsuarioList.size() == 0) {
				 pesoUsuarioList = new ArrayList<PesoUsuario>();
		}
		return pesoUsuarioList;
	}
	
	public void setPesoUsuarioList(List<PesoUsuario> pesoUsuarioList) {
		this.pesoUsuarioList = pesoUsuarioList;
	}

	public Udm getUdm() {
		return udm;
	}

	public void setUdm(Udm udm) {
		this.udm = udm;
	}	
}