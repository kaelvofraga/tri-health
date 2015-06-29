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
	
	private String centroLat;
	private String centroLng;
	
	private List<Hospital> listaHospital;
	private List<Endereco> listaEndereco;
	
	@PostConstruct
	public void init(){
		
		local = new DefaultMapModel();
		
		endereco = new Endereco();
		hospital = new Hospital();
		
		/*
		 * centroLat = "-30.1088701";
			centroLng = "-51.1769046";
		 *
		 * */

		centroLat = "-30.03508670894354";
		centroLng = "-51.21894867968746";
		
		listaHospital = this.getListaHospital();
		listaEndereco = this.getListaEndereco();
	}
	
	

	public String initListar() {
		local = new DefaultMapModel();
		return URL_LISTAR_HOSPITAIS;
	}
	
	public void onSelectHospital(){
		endereco = hospital.getEndereco();
	}
	

	public void atualizaMapa(){
		local = new DefaultMapModel();
		centroLat = endereco.getLatitude();
		centroLng = endereco.getLongitude();
		LatLng coord1 = new LatLng(Double.parseDouble(centroLat), Double.parseDouble(centroLng));
		local.addOverlay(new Marker(coord1, hospital.getNome()));
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



	public String getCentroLat() {
		return centroLat;
	}



	public void setCentroLat(String centroLat) {
		this.centroLat = centroLat;
	}



	public String getCentroLng() {
		return centroLng;
	}



	public void setCentroLng(String centroLng) {
		this.centroLng = centroLng;
	}
	
	
	
	
}