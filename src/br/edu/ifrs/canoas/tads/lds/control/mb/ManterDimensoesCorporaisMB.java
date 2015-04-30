package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.Pais;
import br.edu.ifrs.canoas.tads.lds.bean.TipoMedida;
import br.edu.ifrs.canoas.tads.lds.bean.ValorMedida;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterDimensoesService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterTipoMedidaService;

@Named
@RequestScoped
public class ManterDimensoesCorporaisMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1887636678868732432L;
	private static final String URL_LISTAR_DIMENSOES_CORPORAIS = "/private/pages/listarDimensoesCorporais.jsf";
	private static final String URL_MANTER_DIMENSOES_CORPORAIS = "/private/pages/manterDimensoesCorporais.jsf";
	
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@Inject
	private ValorMedida valorMedida;

	@EJB
	private ManterDimensoesService dimensoesService;
	
	@EJB
	private ManterTipoMedidaService manterTipoMedidaService;
	
	

	//Lista Dimens�es
	private List<ValorMedida> medidas;
	private List<TipoMedida> tipoMedidas;
	
	private String criterioTipoDimensao;
	
	//Form Alergia

	public ManterDimensoesCorporaisMB() {
	}
	
	@PostConstruct
	public void init(){
		valorMedida = new ValorMedida();
		criterioTipoDimensao = "";
		medidas = new ArrayList<>();
	}


	public void busca(){
		medidas = dimensoesService.busca(criterioTipoDimensao);
	}
		
	/*
	 * GETTERS & SETTERS
	 */
	
	
	public List<TipoMedida> getTipoMedidas() {
		if(tipoMedidas == null)
			tipoMedidas = manterTipoMedidaService.buscaTipoMedida();
		
		return tipoMedidas;
	}
	
	public void setTipoMedidas(List<TipoMedida> tipoMedidas) {
		this.tipoMedidas = tipoMedidas;
	}
	
	
	public ManterDimensoesService getDimensoesService() {
		return dimensoesService;
	}

	public void setDimensoesService(ManterDimensoesService dimensoesService) {
		this.dimensoesService = dimensoesService;
	}

	public ValorMedida getValorMedida() {
		return valorMedida;
	}

	public void setValorMedida(ValorMedida valorMedida) {
		this.valorMedida = valorMedida;
	}

	public List<ValorMedida> getMedidas() {
		return medidas;
	}

	public void setMedidas(List<ValorMedida> medidas) {
		this.medidas = medidas;
	}

	public String getCriterioTipoDimensao() {
		return criterioTipoDimensao;
	}

	public void setCriterioTipoDimensao(String criterioTipoDimensao) {
		this.criterioTipoDimensao = criterioTipoDimensao;
	}
	
	

}