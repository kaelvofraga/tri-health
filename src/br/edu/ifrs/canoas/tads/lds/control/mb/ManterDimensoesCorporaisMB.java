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
import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Pais;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;
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
	

	//Lista Dimens�es
	private List<ValorMedidaUsuario> medidas;
	private TipoMedida tipoMedida;
	
	private List<TipoMedida> tipoMedidasLista;
	private List<TipoMedida> tipoMedidasFiltrada;
	
	private Udm udm;
	private List<Udm> udmLista;
	private List<Udm> udmListaFiltrada;
	


	private String criterioMedida;
	
	//Form Alergia

	public ManterDimensoesCorporaisMB() {
	}
	
	@PostConstruct
	public void init(){
		valorMedidaUsuario = new ValorMedidaUsuario();
		tipoMedida = new TipoMedida();
		udm = new Udm();
		criterioMedida = "";
		medidas = new ArrayList<>();
		udmLista = this.getUdmLista();
		tipoMedidasLista = this.getTipoMedidasLista();
		udmListaFiltrada = new ArrayList<>();
		tipoMedidasFiltrada = new ArrayList<>();
		
		
	}
	
	
	public void filtrarUdm(){
		this.udmListaFiltrada.clear();
		Udm u = valorMedidaUsuario.getUdm();
		if ( u != null && u.getId() != null ){			
			for (int i = 0; i < udmLista.size(); i++) {
				Udm udm = udmLista.get(i);
				if(u.getId() == udm.getId()) {
					udmListaFiltrada.add(udm);
				}
			}

		}else{
			udmListaFiltrada = new ArrayList<>();
		}
	}
	
	public void filtrarTipoMedida(){
		this.tipoMedidasFiltrada.clear();
		TipoMedida tm = valorMedidaUsuario.getTipoMedida();
		if ( tm != null && tm.getId() != null ){			
			for (int i = 0; i < tipoMedidasLista.size(); i++) {
				TipoMedida t = tipoMedidasLista.get(i);
				if(tm.getId() == t.getId()) {
					tipoMedidasFiltrada.add(t);
				}
			}

		}else{
			udmListaFiltrada = new ArrayList<>();
		}
	}
	
	
	public void onRowSelect(SelectEvent event) throws IOException {
		this.valorMedidaUsuario = (ValorMedidaUsuario)event.getObject();
		this.filtrarUdm();
		this.filtrarTipoMedida();
		FacesContext.getCurrentInstance().getExternalContext().redirect("manterDimensoesCorporais.jsf");
    }
	
	
	private void clear() {
		/** POJO **/
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
	
	
	public void initListar(){
		valorMedidaUsuario = new ValorMedidaUsuario();
		criterioMedida="";
		medidas = new ArrayList<ValorMedidaUsuario>();
	}
	
	public void initManter(){
		valorMedidaUsuario = new ValorMedidaUsuario();
		valorMedidaUsuario.setTipoMedida(new TipoMedida());
		valorMedidaUsuario.setUdm(new Udm());
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