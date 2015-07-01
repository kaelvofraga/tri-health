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
import br.edu.ifrs.canoas.tads.lds.bean.AlergiaUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Consulta;
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlergia;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterAlergiaService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterConsultasService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUsoMedicamentoService;

/** 
 * @author RodrigoNoll 		  
 * ManageBean Implementations por AlergiaUsuario
 * @author Alisson Lorscheiter
 * @version 28/06/2015
 * Foram criados os metodos initListar,initManter,onSelectTipoAlergia.
 * Foi alterado o metodo de completeAlergia.
 * Foram alterados os metodos de salvar,alterar e excluir.
 * Foi adicionado comentarios aos metodos.
 * Foi alterado os Gets das listas de medicamentosUsuario e Consultas. 
 * */

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
	
	@Inject
	private ManterConsultasService consultasService;
	
	private AlergiaUsuario alergiaUsuario;
	
	private Consulta consultaMedica;
	
	private boolean emListagemAlergia;

	//Lista Alergia
	private List<AlergiaUsuario> alergias;
	private List<MedicamentoUsuario> medicamentosLista;
	private List<Consulta> consultasMedicas;
	private String criterioAlergia;
	
	//Form Alergia
	private List<Medicamento> medicamentos;
	private List<TipoAlergia> tipoAlergias;

		
	/** 
	 * @brief Metodo que inicializa as variaveis ao ser selecionada a opção de listar
	 * os pesos do usuário.	 		  
	 * @param void
	 * @return String
	 * */
	public String initListar() {
		alergiaUsuario = new AlergiaUsuario();
		alergiaUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		criterioAlergia = "";
		alergias = new ArrayList<AlergiaUsuario>();
		//medicamentosLista= this.getMedicamentosLista();
		//consultasMedicas=this.getConsultasMedicas();
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
	
	/** 
	 * @brief Metodo que realiza a busca da view de listagem de alergias do usuario.	 		  
	 * @param void
	 * @return void
	 * */
	public void buscaAlergias(){
		alergias = alergiaService.busca(criterioAlergia);
	}
		
	/** 
	 * @brief Metodo que salva o alergiaUsuario no banco de dados	 		  
	 * @param void
	 * @return void
	 * */
	public void salvaAlergia(){
		alergiaUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		alergiaService.salvaAlergiaUsuario(alergiaUsuario);
		this.initManter();
	}
	
	/** 
	 * @brief Metodo que altera no banco de dados o alergiaUsuario, retorna a URL 
	 * da pagina que sera redirecionado.	 		  
	 * @param void
	 * @return String
	 * */
	public String alteraAlergia(){
		if(alergiaService.alteraAlergiaUsuario(alergiaUsuario)==true){
			return URL_LISTAR_PERFIL_EMERGENCIA;
			}
			else{
				return URL_MANTER_PERFIL_EMERGENCIA;
			}
	}
	
	/** 
	 * @brief Metodo que exclui do banco de dados o alergiaUsuario, retorna a URL 
	 * da pagina que sera redirecionado.	 		  
	 * @param void
	 * @return String
	 * */
	public String excluiAlergia(){
		if (alergiaService.excluiAlergiaUsuario(alergiaUsuario)) {
			this.buscaAlergias();
			return URL_LISTAR_PERFIL_EMERGENCIA;
		}
		return URL_MANTER_PERFIL_EMERGENCIA;
	}
	
	/** 
	 * @brief Metodo que verifica se o alergiaUsuario está sendo atualizado
	 * ou é um novo cadastro. 		  
	 * @param void
	 * @return boolean
	 * */
	public boolean isAtualizacao(){
		return alergiaUsuario != null && alergiaUsuario.getId() != null;
	}
	
	/** 
	 * @brief Metodo que realiza o evento de seleção da linha da tabela que lista
	 *  as alergias do usuário.	 		  
	 * @param event (SelectEvent)
	 * @return void
	 * */
	public void onRowSelect(SelectEvent event) throws IOException {
		this.alergiaUsuario = (AlergiaUsuario) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext().redirect("../../private/pages/manterPerfilEmergencia.jsf");
    }

	
	/*
	 * GETTERS & SETTERS
	 */
	
	public AlergiaUsuario getAlergiaUsuario() {
		return alergiaUsuario;
	}

	public List<MedicamentoUsuario> getMedicamentosLista() {
		medicamentosLista= medicamentoService.buscaMedicamentoUsuarioporUsuario(alergiaUsuario.getUsuario());
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

	public List<Consulta> getConsultasMedicas() {
		consultasMedicas = consultasService.buscaConsultaporUsuario(alergiaUsuario.getUsuario());
		return consultasMedicas;
	}

	public void setConsultasMedicas(List<Consulta> consultasMedicas) {
		this.consultasMedicas = consultasMedicas;
	}

	public Consulta getConsultaMedica() {
		return consultaMedica;
	}

	public void setConsultaMedica(Consulta consultaMedica) {
		this.consultaMedica = consultaMedica;
	}
	
}	