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

import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Pais;
import br.edu.ifrs.canoas.tads.lds.bean.TipoMedida;
import br.edu.ifrs.canoas.tads.lds.bean.Udm;
import br.edu.ifrs.canoas.tads.lds.bean.ValorMedidaUsuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterDimensoesCorporaisService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterTipoMedidaService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUdmService;

@Named
@RequestScoped
public class ManterDimensoesCorporaisMB implements Serializable {

	private static final long serialVersionUID = 2356931811611360673L;

	private static final String URL_LISTAR_DIMENSOES_CORPORAIS = "/private/pages/listarDimensoesCorporais.jsf";
	private static final String URL_MANTER_DIMENSOES_CORPORAIS = "/private/pages/manterDimensoesCorporais.jsf";
	
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@Inject
	private ValorMedidaUsuario valorMedidaUsuario;

	@EJB
	private ManterDimensoesCorporaisService dimensoesService;
	
	@EJB
	private ManterTipoMedidaService manterTipoMedidaService;
	
	@EJB
	private ManterUdmService manterUdmService;
	

	//Lista Dimensões
	private List<ValorMedidaUsuario> medidas;
	private TipoMedida tipoMedida;
	
	private List<TipoMedida> tipoMedidasLista;
	private List<TipoMedida> tipoMedidasFiltrada;
	
	private Udm udm;
	private List<Udm> udmLista;
	private List<Udm> udmListaFiltrada;
	


	private String criterioTipoDimensao;
	
	//Form Alergia

	public ManterDimensoesCorporaisMB() {
	}
	
	@PostConstruct
	public void init(){
		valorMedidaUsuario = new ValorMedidaUsuario();
		criterioTipoDimensao = "";
		medidas = new ArrayList<>();
		tipoMedida = new TipoMedida();
		udm = new Udm();
	}

	
	public void onRowSelect(SelectEvent event) throws IOException {
		this.valorMedidaUsuario = (ValorMedidaUsuario)event.getObject();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../../private/pages/manterDimensoesCorporais.jsf");
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
		medidas = dimensoesService.busca(criterioTipoDimensao);
	}
	
	public void onTipoDimensoesChange(){
		tipoMedidasFiltrada.clear();

		if (tipoMedida != null && tipoMedida.getId() != null){
			for (int i = 0; i < tipoMedidasLista.size(); i++) {
				TipoMedida tm = tipoMedidasLista.get(i);
				if(tm.getId() == tipoMedida.getId()) {
					tipoMedidasFiltrada.add(tm);
				}
			}						
		}else{
			tipoMedidasFiltrada = new ArrayList<>();
		}
	}
	
	public void onUdmChange(){
		udmListaFiltrada.clear();

		if (udm != null && udm.getId() != null){
			for (int i = 0; i < udmLista.size(); i++) {
				Udm u = udmLista.get(i);
				if(u.getId() == udm.getId()) {
					udmListaFiltrada.add(u);
				}
			}						
		}else{
			udmListaFiltrada = new ArrayList<>();
		}
	}
		
	public void onSelectTipoMedida(){
		valorMedidaUsuario.setTipoMedida(tipoMedida);
	}
	
	public void onSelectUdm(){
		valorMedidaUsuario.setUdm(udm);
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

	public String getCriterioTipoDimensao() {
		return criterioTipoDimensao;
	}

	public void setCriterioTipoDimensao(String criterioTipoDimensao) {
		this.criterioTipoDimensao = criterioTipoDimensao;
	}

	public TipoMedida getTipoMedida() {
		return tipoMedida;
	}

	public void setTipoMedida(TipoMedida tipoMedida) {
		this.tipoMedida = tipoMedida;
	}

	public List<TipoMedida> getTipoMedidasFiltrada() {
		return tipoMedidasFiltrada;
	}

	public void setTipoMedidasFiltrada(List<TipoMedida> tipoMedidasFiltrada) {
		this.tipoMedidasFiltrada = tipoMedidasFiltrada;
	}

	public List<Udm> getUdmListaFiltrada() {
		return udmListaFiltrada;
	}

	public void setUdmListaFiltrada(List<Udm> udmListaFiltrada) {
		this.udmListaFiltrada = udmListaFiltrada;
	}

	public Udm getUdm() {
		return udm;
	}

	public void setUdm(Udm udm) {
		this.udm = udm;
	}
	
	

}