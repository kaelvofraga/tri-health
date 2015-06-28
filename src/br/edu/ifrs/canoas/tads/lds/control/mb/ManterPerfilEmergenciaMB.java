package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.AlergiaUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlergia;
import br.edu.ifrs.canoas.tads.lds.bean.Udm;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterAlergiaService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUsoMedicamentoService;

@Named
@SessionScoped
public class ManterPerfilEmergenciaMB implements Serializable {

	private static final String URL_LISTAR_PERFIL_EMERGENCIA = "/private/pages/listarPerfilEmergencia.jsf";
	private static final String URL_MANTER_PERFIL_EMERGENCIA = "/private/pages/manterPerfilEmergencia.jsf";
	private static final long serialVersionUID = -6762932920422815855L;
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@EJB
	private ManterAlergiaService alergiaService;
	
	@Inject
	private ManterUsoMedicamentoService medicamentoService;
	
	private AlergiaUsuario alergiaUsuario;
	
	private boolean emListagemAlergia;

	//Lista Alergia
	private List<AlergiaUsuario> alergias;
	private List<MedicamentoUsuario> medicamentosLista;
	private String criterioAlergia;
	
	//Form Alergia
	private List<Medicamento> medicamentos;
	private List<TipoAlergia> tipoAlergias;

	public ManterPerfilEmergenciaMB() {
	}
	
	
	/** 
	 * @brief Metodo que inicializa as variaveis ao ser selecionada a opção de listar
	 * os pesos do usuário.	 		  
	 * @param void
	 * @return String
	 * */
	public String initListar() {
		alergiaUsuario = new AlergiaUsuario();
		criterioAlergia = "";
		alergias = new ArrayList<AlergiaUsuario>();
		medicamentosLista= new ArrayList<MedicamentoUsuario>();
		return URL_LISTAR_PERFIL_EMERGENCIA;
	}

	/** 
	 * @brief Metodo que inicializa as variaveis ao ser selecionada a opção de cadastrar
	 * os pesos do usuário.	 		  
	 * @param void
	 * @return String
	 * */
	public String initManter() {
		alergiaUsuario = new AlergiaUsuario();	
		alergiaUsuario.setMedicamentoUsuario(new MedicamentoUsuario());
		alergiaUsuario.getMedicamentoUsuario().setMedicamento(new Medicamento());
		alergiaUsuario.setTipoAlergia(new TipoAlergia());
		medicamentos= new ArrayList<Medicamento>();
		return URL_MANTER_PERFIL_EMERGENCIA;
	}
	
	/** 
	 * @brief Metodo que busca os medicamentos ja existentes no banco de dados	 		  
	 * @param query(String) input de texto do usuario na view cadastro de alergia.
	 * @return List<Medicamento>
	 * */
	public List<Medicamento> completeAlergia(String query) {
		if (medicamentos.isEmpty())
			medicamentos = medicamentoService.buscaMedicamentoUsuario(query,
					gerenciarLoginMB.getUsuario());

		List<Medicamento> medicamentosBusca = new ArrayList<Medicamento>();

		for (int i = 0; i < medicamentos.size(); i++) {
			Medicamento medicamento = medicamentos.get(i);
			if (medicamento.getNome().trim().toLowerCase().startsWith(query)) {
				medicamentosBusca.add(medicamento);
			}
		}
		return medicamentosBusca;
	}	
	
	/** 
	 * @brief Metodo que seta o tipo de alergia selecionado. 		  
	 * @param void
	 * @return void
	 * */
	public void onSelectTipoAlergia(){
		alergiaUsuario.getTipoAlergia();
	}
	
	
	public void busca(){
		alergias = alergiaService.busca(criterioAlergia);
	}
		
	
	public void salvaAlergia(){
		alergiaUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		alergiaService.salvaAlergiaUsuario(alergiaUsuario);
		this.initManter();
	}
	
	public String alteraAlergia(){
		alergiaService.alteraAlergiaUsuario(alergiaUsuario);
		return "listarPerfilEmergencia";
	}
	
	public String excluiAlergia(){
		alergiaService.excluiAlergiaUsuario(alergiaUsuario);
		this.busca();
		return "listarPerfilEmergencia";
	}
	
	public String editarAlergiaUsuario(AlergiaUsuario alergia){
		this.alergiaUsuario = alergia;
		this.emListagemAlergia = false;
		return URL_MANTER_PERFIL_EMERGENCIA;
	}
	
	public boolean isAtualizacao(){
		return alergiaUsuario != null && alergiaUsuario.getId() != null;
	}

	
	/*
	 * GETTERS & SETTERS
	 */
	
	public AlergiaUsuario getAlergiaUsuario() {
		return alergiaUsuario;
	}

	public List<MedicamentoUsuario> getMedicamentosLista() {
		return medicamentosLista;
	}

	public void setMedicamentosLista(List<MedicamentoUsuario> medicamentosLista) {
		this.medicamentosLista = medicamentosLista;
	}

	public void setAlergiaUsuario(AlergiaUsuario alergiaUsuario) {
		this.alergiaUsuario = alergiaUsuario;
	}

	public String getCriterioAlergia() {
		return criterioAlergia;
	}

	public void setCriterioAlergia(String criterioAlergia) {
		this.criterioAlergia = criterioAlergia;
	}

	public List<AlergiaUsuario> getAlergias() {
		return alergias;
	}

	public void setAlergias(List<AlergiaUsuario> alergias) {
		this.alergias = alergias;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public List<TipoAlergia> getTipoAlergias() {
		if (tipoAlergias == null)
			tipoAlergias = alergiaService.buscaDescricoesTipoAlergias();
		return tipoAlergias;
	}

	public void setTipoAlergias(List<TipoAlergia> tipoAlergias) {
		this.tipoAlergias = tipoAlergias;
	}

	public boolean isEmListagemAlergia() {
		return emListagemAlergia;
	}

	public void setEmListagemAlergia(boolean emListagemAlergia) {
		this.emListagemAlergia = emListagemAlergia;
	}
}	