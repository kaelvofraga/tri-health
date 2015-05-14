package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Udm;
import br.edu.ifrs.canoas.tads.lds.bean.ValorMedidaUsuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterPesoService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUdmService;

/** 
 * @author Luana
 * @version 06/05/2015
 */

@Named 
@RequestScoped 
public class ManterPesoMB implements Serializable {
	
	private static final long serialVersionUID = 8840982087710515671L;
	private static final String URL_NOVO_PESO = "/private/pages/manterPeso.jsf";

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@Inject
	private PesoUsuario pesoUsuario;
	
	@EJB
	private ManterUdmService manterUdmService;
	@EJB
	private ManterPesoService pesoService;
	
	private List<PesoUsuario> pesoUSuarioList;
	private List<Udm> udmLista;
	
	private Udm udm;	
	private String buscaPeso;
	
	@PostConstruct
	public void inicializa() {
		pesoUsuario = new PesoUsuario();
		udm = new Udm();
		udmLista = this.getUdmLista();	
		pesoUSuarioList = pesoService.listaPeso(); 
		
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		this.pesoUsuario = (PesoUsuario)event.getObject();
		FacesContext.getCurrentInstance().getExternalContext().redirect("manterPeso.jsf");
    }
	
	public String novoPesoUsuario(){
		this.inicializa();
		return URL_NOVO_PESO;
	}	

	public void setUdmLista(List<Udm> udmLista) {
		this.udmLista = udmLista;
	}

	public void salvaPeso(){
		pesoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		pesoService.salvaPesoUsuario(pesoUsuario);
		this.inicializa();
	}

	public void onSelectUdm(){
		pesoUsuario.setUdm(udm);
	}
	
	public List<Udm> getUdmLista() {
		if(udmLista == null)
			udmLista = manterUdmService.buscaUdm();
		return udmLista;
	}
	
	
	//Getters e setters
	public ManterPesoService getPesoService() {
		return pesoService;
	}

	public void setPesoService(ManterPesoService pesoService) {
		this.pesoService = pesoService;
	}
	
	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}

	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}

	public PesoUsuario getPesoUsuario() {
		return pesoUsuario;
	}

	public void setPesoUsuario(PesoUsuario pesoUsuario) {
		this.pesoUsuario = pesoUsuario;
	}


	public ManterUdmService getManterUdmService() {
		return manterUdmService;
	}

	public void setManterUdmService(ManterUdmService manterUdmService) {
		this.manterUdmService = manterUdmService;
	}

	public List<PesoUsuario> getPesoUSuarioList() {
		if(pesoUSuarioList == null || pesoUSuarioList.size() == 0) {
				 pesoUSuarioList = new ArrayList<PesoUsuario>();
		}
		return pesoUSuarioList;
	}
	

	public void setPesoUSuarioList(List<PesoUsuario> pesoUSuarioList) {
		this.pesoUSuarioList = pesoUSuarioList;
	}

	public Udm getUdm() {
		return udm;
	}

	public void setUdm(Udm udm) {
		this.udm = udm;
	}

	public static String getUrlNovoPeso() {
		return URL_NOVO_PESO;
	}

	public String getBuscaPeso() {
		return buscaPeso;
	}

	public void setBuscaPeso(String buscaPeso) {
		this.buscaPeso = buscaPeso;
	}
	
}