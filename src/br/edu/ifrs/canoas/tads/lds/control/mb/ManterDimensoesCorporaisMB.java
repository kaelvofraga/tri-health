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
import br.edu.ifrs.canoas.tads.lds.bean.TipoMedida;
import br.edu.ifrs.canoas.tads.lds.bean.Udm;
import br.edu.ifrs.canoas.tads.lds.bean.ValorMedidaUsuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterDimensoesCorporaisService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterTipoMedidaService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUdmService;

/**
 * 
 * @author JuarezMonteiro;
 *
 */

@Named
@SessionScoped
public class ManterDimensoesCorporaisMB implements Serializable {

	private static final long serialVersionUID = 2356931811611360673L;

	private static final String URL_LISTAR_DIMENSOES_CORPORAIS = "/private/pages/listarDimensoesCorporais.jsf";
	private static final String URL_MANTER_DIMENSOES_CORPORAIS = "/private/pages/manterDimensoesCorporais.jsf";
	
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@EJB
	private ManterDimensoesCorporaisService dimensoesService;
	
	@EJB
	private ManterTipoMedidaService manterTipoMedidaService;
	
	@EJB
	private ManterUdmService manterUdmService;
	
	//Variables
	private ValorMedidaUsuario valorMedidaUsuario;
	private String criterioMedida;
	private TipoMedida tipoMedida;
	private Udm udm;
	
	//Lists
	private List<ValorMedidaUsuario> medidas;
	private List<TipoMedida> tipoMedidasLista;
	private List<Udm> udmLista;
	
	
	public ManterDimensoesCorporaisMB() {}
	
	/**
	 * Metodo que inicializa as variáveis para views de listar e manter Dimensoes Corporais.
	 * */
	@PostConstruct
	public void init(){
		valorMedidaUsuario = new ValorMedidaUsuario();
		tipoMedida = new TipoMedida();
		udm = new Udm();
		criterioMedida = "";
		medidas = new ArrayList<>();
		udmLista = this.getUdmLista();
		tipoMedidasLista = this.getTipoMedidasLista();
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		this.valorMedidaUsuario = (ValorMedidaUsuario)event.getObject();
		FacesContext.getCurrentInstance().getExternalContext().redirect("../../private/pages/manterDimensoesCorporais.jsf");
    }
	
	
	private void clear() {
		valorMedidaUsuario = new ValorMedidaUsuario();
	}
	
	public boolean isAtualizacao(){
		return valorMedidaUsuario != null && valorMedidaUsuario.getId() != null;
	}
	
	public void salvaDimensoes(){
		valorMedidaUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		dimensoesService.salvaMedidaUsuario(valorMedidaUsuario);
		this.init();
	}
	
	
	public void busca(){
		medidas = dimensoesService.busca(criterioMedida);
	}
	
	public String alteraMedida() {
		dimensoesService.alteraMedida(valorMedidaUsuario);
		return URL_LISTAR_DIMENSOES_CORPORAIS;
	}
	
	public String excluiMedida(){
		dimensoesService.excluiMedida(valorMedidaUsuario);
		this.busca();
		return URL_LISTAR_DIMENSOES_CORPORAIS;
	}
			
	public void onSelectTipoMedida(){
		valorMedidaUsuario.setTipoMedida(tipoMedida);
	}
	
	public void onSelectUdm(){
		valorMedidaUsuario.setUdm(udm);
	}
	
	
	public String initListar(){
		valorMedidaUsuario = new ValorMedidaUsuario();
		criterioMedida="";
		medidas = new ArrayList<ValorMedidaUsuario>();
		return URL_LISTAR_DIMENSOES_CORPORAIS;
	}
	
	public String initManter(){
		valorMedidaUsuario = new ValorMedidaUsuario();
		valorMedidaUsuario.setTipoMedida(new TipoMedida());
		valorMedidaUsuario.setUdm(new Udm());
		return URL_MANTER_DIMENSOES_CORPORAIS;
	}
	
	public String novaMedidaUsuario(){
		this.clear();
		return URL_MANTER_DIMENSOES_CORPORAIS;
	}
	
	
	/*
	 * GETTERS & SETTERS
	 */
	
	public List<Udm> getUdmLista() {
		if(udmLista == null)
			udmLista = manterUdmService.buscaUdm();
		return udmLista;
	}

	public void setUdmLista(List<Udm> udmLista) {
		this.udmLista = udmLista;
	}
	
	public List<TipoMedida> getTipoMedidasLista() {
		if(tipoMedidasLista == null)
			tipoMedidasLista = manterTipoMedidaService.buscaTipoMedida();
		
		return tipoMedidasLista;
	}
	
	public void setTipoMedidasLista(List<TipoMedida> tipoMedidasLista) {
		this.tipoMedidasLista = tipoMedidasLista;
	}
	
	
	public ManterDimensoesCorporaisService getDimensoesService() {
		return dimensoesService;
	}

	public void setDimensoesService(ManterDimensoesCorporaisService dimensoesService) {
		this.dimensoesService = dimensoesService;
	}

	public ValorMedidaUsuario getValorMedidaUsuario() {
		return valorMedidaUsuario;
	}

	public void setValorMedidaUsuario(ValorMedidaUsuario valorMedidaUsuario) {
		this.valorMedidaUsuario = valorMedidaUsuario;
	}

	public List<ValorMedidaUsuario> getMedidas() {
		return medidas;
	}

	public void setMedidas(List<ValorMedidaUsuario> medidas) {
		this.medidas = medidas;
	}

	public String getCriterioMedida() {
		return criterioMedida;
	}

	public void setCriterioMedida(String criterioMedida) {
		this.criterioMedida = criterioMedida;
	}

	public TipoMedida getTipoMedida() {
		return tipoMedida;
	}

	public void setTipoMedida(TipoMedida tipoMedida) {
		this.tipoMedida = tipoMedida;
	}

	public Udm getUdm() {
		return udm;
	}

	public void setUdm(Udm udm) {
		this.udm = udm;
	}
	
	

}