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
 * 
 *
 */

@Named 
@RequestScoped 
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
	
	private Udm udm;	
	private String buscaPeso;
	
	/*@PostConstruct
	public void inicializa() {
		pesoUsuario = new PesoUsuario();
		udm = new Udm();
		udmLista = this.getUdmLista();	
		pesoUSuarioList = pesoService.listaPeso(); 
		
	}*/
	
	public String initListar() {
		pesoUsuario = new PesoUsuario();
		buscaPeso = "";
		pesoUsuarioList = new ArrayList<>();
		return URL_LISTAR_PESO;
	}

	public String initManter() {
		udm = new Udm();
		udmLista= this.getUdmLista();
		pesoUsuario = new PesoUsuario();
		pesoUsuario.setPeso(new Peso());
		return URL_MANTER_PESO;
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		this.pesoUsuario = (PesoUsuario)event.getObject();
		FacesContext.getCurrentInstance().getExternalContext().redirect("manterPeso.jsf");
    }
	
	public String novoPesoUsuario(){
		//this.clear();
		return URL_MANTER_PESO;
	}	

	public void setUdmLista(List<Udm> udmLista) {
		this.udmLista = udmLista;
	}

	public void salvaPeso(){
		pesoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		pesoService.salvaPesoUsuario(pesoUsuario);
		this.initManter();
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
		if(pesoUsuarioList == null || pesoUsuarioList.size() == 0) {
				 pesoUsuarioList = new ArrayList<PesoUsuario>();
		}
		return pesoUsuarioList;
	}
	
	public void setPesoUSuarioList(List<PesoUsuario> pesoUSuarioList) {
		this.pesoUsuarioList = pesoUSuarioList;
	}

	public Udm getUdm() {
		return udm;
	}

	public void setUdm(Udm udm) {
		this.udm = udm;
	}

	public String getBuscaPeso() {
		return buscaPeso;
	}

	public void setBuscaPeso(String buscaPeso) {
		this.buscaPeso = buscaPeso;
	}
	
}