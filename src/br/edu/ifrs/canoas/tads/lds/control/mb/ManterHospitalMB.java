package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.lang.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.edu.ifrs.canoas.tads.lds.bean.Endereco;
import br.edu.ifrs.canoas.tads.lds.bean.Hospital;
import br.edu.ifrs.canoas.tads.lds.bean.TipoMedida;
import br.edu.ifrs.canoas.tads.lds.bean.Udm;
import br.edu.ifrs.canoas.tads.lds.bean.ValorMedidaUsuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterEnderecoService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterHospitalService;

/**
 * @author Juarez Monteiro
 */

@Named
@SessionScoped
public class ManterHospitalMB implements Serializable {

	private static final long serialVersionUID = -6152898785356095230L;
	
	private static final String URL_LISTAR_HOSPITAIS = "/private/pages/mapaHospital.jsf";

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@EJB
	private ManterHospitalService manterHospitalService;
	
	@EJB
	private ManterEnderecoService manterEnderecoService;
	
	private MapModel local;
	
	private Hospital hospital;
	private Endereco endereco;
	
	private List<Hospital> listaHospital;
	private List<Endereco> listaEndereco;
	
	@PostConstruct
	public void init(){
		
		local = new DefaultMapModel();

		//Testando Markers
		//LatLng coord1 = new LatLng(-30.0195467, -51.170071);
		//local.addOverlay(new Marker(coord1, "Hospital"));
		
		endereco = new Endereco();
		hospital = new Hospital();
		
		listaHospital = this.getListaHospital();
		listaEndereco = this.getListaEndereco();
	}
	
	

	public String initListar() {
		return URL_LISTAR_HOSPITAIS;
	}
	
	public void onSelectHospital(){
		endereco = hospital.getEndereco();
	}
	

	public void atualizaMapa(){
		System.out.println("ID do Endereco" + endereco.getId());
		System.out.println("ENDERECO:"+hospital.getEndereco());
		
		LatLng coord1 = new LatLng(Double.parseDouble(endereco.getLatitude()), Double.parseDouble(endereco.getLongitude()));
		local.addOverlay(new Marker(coord1, hospital.getNome()));
		//return local;
	}
	
	//Getters and Setters
	
	public List<Endereco> getListaEndereco() {
		if(listaEndereco == null)
			listaEndereco = manterEnderecoService.buscaTodosEnderecos();
		return listaEndereco;
	}
	
	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}


	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}


	public ManterHospitalService getHospitalService() {
		return manterHospitalService;
	}


	public void setHospitalService(ManterHospitalService hospitalService) {
		this.manterHospitalService = hospitalService;
	}


	public Hospital getHospital() {
		return hospital;
	}


	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}


	public List<Hospital> getListaHospital() {
		if(listaHospital == null)
			listaHospital = manterHospitalService.buscaTodosHospitais();
			//System.out.println("TAMANHOOOOOO DA LISTAAAAAA:"+listaHospital.size());
		return listaHospital;
	}


	public void setListaHospital(List<Hospital> listaHospital) {
		this.listaHospital = listaHospital;
	}

	public MapModel getLocal() {
		return local;
	}

	public void setLocal(MapModel local) {
		this.local = local;
	}
	
	
	
}