package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.edu.ifrs.canoas.tads.lds.bean.Consulta;
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterConsultasService;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;



@Named
@SessionScoped
public class ManterConsultasMB implements Serializable {
	private static final long serialVersionUID = -6833487765093285579L;
	private static final String URL_LISTAR = "/private/pages/listarConsulta.jsf";
	private static final String URL_MANTER = "/private/pages/manterConsulta.jsf";
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@EJB
	private ManterConsultasService consultaService;
	
	@Inject 
	private Consulta consultaMedica;	

	// Lista da listagem
		private List<Consulta> consultasMedicas;
		
	// Lista do autoComplete
	private List<Medico> medicos;
		
	//string de busca
	private String criterioConsulta;
	
	public ManterConsultasMB() {
	}
	
	/*
	 * Metodo que limpa Consulta,criterio de busca, lista de
	 * Consulta ao entrar na view de Listar
	 */
	public String initListar() {
		criterioConsulta = "";
		clear();
		return URL_LISTAR;
	}

	/*
	 * Metodo que limpaConsulta, e lista de Consulta
	 * do autocomplete da view de manter
	 */
	public String initManter() {
		clear();
		return URL_MANTER;
	}
	
	/* Metodo que inicializa as variaveis apos Salvar */
	private void clear() {
		consultaMedica = new Consulta();
		consultasMedicas = new ArrayList<>();
	}
	
	/*
	 * Metodo que chama o service para salvar a Consulta para o Usuario que
	 * esta logado
	 */
	public void salvaConsulta(){
		Usuario user = gerenciarLoginMB.getUsuario();
		consultaService.insere(consultaMedica, user);
		this.clear();
	}

	/*
	 * Metodo que chama o service para alterar a Consulta passando ela
	 * como parametro retorna a url de listagem.
	 */
	public String alteraConsulta(){
		consultaService.salva(consultaMedica);
		return URL_LISTAR;
	}
	
	/*
	 * Metodo que chama o service para excluir a Consulta e retorna
	 * URL listar ou manter
	 */
	public String exclui(){
		if (consultaService.exclui(consultaMedica)) {
			this.busca();
			return URL_LISTAR;
		}
		return URL_MANTER;
	}
	
	/*
	 * Metodo que pega o evento de selecão da linha da tabela por parametro e
	 * carrega na view manter o objeto Medicamentousuario selecionado.
	 */
	public void onRowSelect(SelectEvent event) throws IOException {
		this.consultaMedica = (Consulta) event.getObject();
        FacesContext.getCurrentInstance().getExternalContext().redirect("manterConsulta.xhtml");
    }

	public boolean isAtualizacao(){
		return consultaMedica != null && consultaMedica.getId() != null;
	}
	
	/*
	 * Metodo que realiza o autocomplete do input de Medico na view de
	 * manterUsoMedicamentos
	 */
	public List<Medico> completeMedico(String query) {
		if (medicos.isEmpty())
			medicos = consultaService.buscaMedico(query);

		List<Medico> medicosBusca = new ArrayList<Medico>();

		for (int i = 0; i < medicos.size(); i++) {
			Medico medico = medicos.get(i);
			if (medico.getNome().trim().toLowerCase().startsWith(query)) {
				medicosBusca.add(medico);
			}
		}
		return medicosBusca;
	}
	
	/* Metodo de busca da view Listar */
	public void busca() {
		consultasMedicas = consultaService.busca(criterioConsulta);
	}
	
	public String voltarParaListar(){
		return URL_LISTAR;
	}
	
	public String novaConsulta(){
		consultaMedica = new Consulta();
		return URL_MANTER;
	}
	
	/*
	 * Gets e Sets
	 */	

	public List<Medico> getMedicos() {
		return medicos;
	}

	public String getCriterioConsulta() {
		return criterioConsulta;
	}

	public void setCriterioConsulta(String criterioConsulta) {
		this.criterioConsulta = criterioConsulta;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public Consulta getConsultaMedica() {
		return consultaMedica;
	}

	public void setConsultaMedica(Consulta consultaMedica) {
		this.consultaMedica = consultaMedica;
	}

	public List<Consulta> getConsultasMedicas() {
		return consultasMedicas;
	}

	public void setConsultasMedicas(List<Consulta> consultasMedicas) {
		this.consultasMedicas = consultasMedicas;
	}
}
