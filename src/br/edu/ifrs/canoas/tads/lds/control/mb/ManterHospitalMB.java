package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import br.edu.ifrs.canoas.tads.lds.bean.Hospital;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterHospitalService;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

/**
 * ManageBean relacionado a view mapaHospital e aos services manterHospitalService e manterEnderecoService
 * @author Juarez Monteiro
 */

@Named
@SessionScoped
public class ManterHospitalMB implements Serializable {

	private static final long serialVersionUID = -6152898785356095230L;

	private static final String URL_LISTAR_HOSPITAIS = "/private/pages/mapaHospital.jsf";

	//Controllers
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	//Services
	@EJB
	private ManterHospitalService manterHospitalService;
	
	private Hospital hospital;
	
	/*
	 * local (MapModel)
	 * Objeto relacionado a extens�o Gmap do PrimeFaces
	 * utilizada para visualizar o mapa do google-maps dentro da aplica��o.
	 * 
	 * centroLat e centroLng (String)
	 * vari�veis criadas para utilizar em m�todos do objeto local (MapModel)
	 * ir�o possuir os dados da centraliza��o do mapa.
	 * como default (init) ir�o centralizar em Porto Alegre-RS: "-30.03508670894354"/"-51.21894867968746";
	 */
	private MapModel local;
	private String centroLat;
	private String centroLng;

	//Listas de hospitais no BD
	private List<Hospital> listaHospital;

	@PostConstruct
	public void init() {

		local = new DefaultMapModel();
		hospital = new Hospital();

		// Lat e Long iniciadas em Porto Alegre, Rio Grande do Sul.
		centroLat = "-30.03508670894354";
		centroLng = "-51.21894867968746";
		
		// Lista de Hospitais
		listaHospital = this.getListaHospital();
	}

	/*
	 * Inicia a listagem na view, inicializando os objetos local e hospital.	  	 		  
	 * @return void
	 * */
	public String initListar() {
		local = new DefaultMapModel();
		hospital = new Hospital();
		return URL_LISTAR_HOSPITAIS;
	}

	/* 
	 * Ir� atualizar o mapa disposto na view.
	 * 
	 * Primeiramente ir� verificar se o hospital selecionado possui algum dado do endere�o
	 * que esteja faltando. Se houver, o sistema ir� retornar uma mensagem ao usu�rio e consequentemente
	 * n�o ir� adicionar marcador no mapa para o devido hospital.
	 * 
	 * Caso o hospital selecionado na view n�o possua latitude e longitude salvas no banco 
	 * O sistema ir� retornar uma mensagem ao usuario e n�o ser� inserido marcador no mapa 
	 * para o hospital selecionado.
	 * 
	 * Caso o hospital selecionado possua os dados de endere�o, juntamente dos dados de latitude e longitude
	 * as vari�veis de latitude central e longitude central que est�o sendo passadas para view ir�o receber 
	 * respectivamente a lat. e a lng. do hospital selecionado. Ap�s isso ser� instanciado um objeto coordenada (lat./Lng.)
	 * que ser� utilizado para criar um marcador e uma descri��o para ele dentro do mapa, atrav�s do objeto local. 	  	 		  
	 * 
	 * @return void
	 * */
	public void atualizaMapa() {
		if (hospital.getEndereco().getLogradouro().isEmpty()
				|| hospital.getEndereco().getNumero() == null
				|| hospital.getEndereco().getTelefone().getNumero().isEmpty()){
			hospital = new Hospital();
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterHospital.busca.vazio");
		}
		if (hospital.getEndereco().getLatitude().isEmpty()
				|| hospital.getEndereco().getLongitude().isEmpty()) {
			local = new DefaultMapModel();
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterHospital.busca.mapa.erro");
		} else {
			local = new DefaultMapModel();
			centroLat = hospital.getEndereco().getLatitude();
			centroLng = hospital.getEndereco().getLongitude();

			LatLng coord1 = new LatLng(Double.parseDouble(centroLat),
					Double.parseDouble(centroLng));
			local.addOverlay(new Marker(coord1, hospital.getNome()));
		}
	}

	
	
	// Getters and Setters

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
		if (listaHospital == null)
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