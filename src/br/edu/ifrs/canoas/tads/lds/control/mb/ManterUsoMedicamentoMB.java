package br.edu.ifrs.canoas.tads.lds.control.mb;

/**
 * ManageBean Implementation for class ManterUsoMedicamentosMB
 * @author Alisson Lorscheiter
 *
 */
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
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUsoMedicamentoService;

@Named
@SessionScoped
public class ManterUsoMedicamentoMB implements Serializable {

	private static final long serialVersionUID = 6240201264929366814L;
	private static final String URL_LISTAR_USO_MEDICAMENTOS = "/private/pages/listarUsoMedicamentos.jsf";
	private static final String URL_MANTER_USO_MEDICAMENTOS = "/private/pages/manterUsoMedicamentos.jsf";

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@EJB
	private ManterUsoMedicamentoService medicamentoService;

	private MedicamentoUsuario medicamentoUsuario;

	private String criterioMedicamento;

	// Lista MedicamentosUsuario
	private List<MedicamentoUsuario> medicamentosLista;

	// Form medicamentos.
	private List<Medicamento> medicamentos;

	/*
	 * Metodo que limpa MedicamentoUsuario,criterio de busca, lista de
	 * medicamnetousuario ao entrar na view de Listar
	 */
	public String initListar() {
		medicamentoUsuario = new MedicamentoUsuario();
		criterioMedicamento = "";
		medicamentosLista = new ArrayList<>();
		return URL_LISTAR_USO_MEDICAMENTOS;
	}

	/*
	 * Metodo que limpa MedicamentoUsuario, Medicamento, e lista de medicamentos
	 * do autocomplete da view de manter
	 */
	public String initManter() {
		medicamentoUsuario = new MedicamentoUsuario();
		medicamentoUsuario.setMedicamento(new Medicamento());
		medicamentos = new ArrayList<>();
		return URL_MANTER_USO_MEDICAMENTOS;
	}

	/* Metodo que inicializa as variaveis apos Salvar */
	private void clear() {
		medicamentoUsuario = new MedicamentoUsuario();
		medicamentoUsuario.setMedicamento(new Medicamento());
		medicamentos = new ArrayList<>();
	}

	/*
	 * Metodo que chama o service para salvar o Medicamento para o Usuario que
	 * esta logado
	 */
	public void salvaMedicamento() {
		medicamentoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		medicamentoService.salvaMedicamentoUsuario(medicamentoUsuario);
		this.clear();
	}

	/*
	 * Metodo que chama o service para alterar o MedicamentoUsuario passando ele
	 * como parametro retorna a url de listagem.
	 */
	public String alteraMedicamento() {
		medicamentoService.alteraMedicamentoUsario(medicamentoUsuario);
		return URL_LISTAR_USO_MEDICAMENTOS;
	}

	/*
	 * Metodo que chama o service para excluir o MedicamentoUsuario e retorna
	 * URL listar ou manter
	 */
	public String excluiMedicamento() {
		if (medicamentoService.excluiMedicamento(medicamentoUsuario)) {
			this.busca();
			return URL_LISTAR_USO_MEDICAMENTOS;
		}
		return URL_MANTER_USO_MEDICAMENTOS;
	}

	/*
	 * Metodo que pega o evento de selec�o da linha da tabela por parametro e
	 * carrega na view manter o objeto Medicamentousuario selecionado.
	 */
	public void onRowSelect(SelectEvent event) throws IOException {
		this.medicamentoUsuario = (MedicamentoUsuario) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("../../private/pages/manterUsoMedicamentos.jsf");
	}

	/*
	 * Metodo boolean para verificar se o medicamentoUsuario existe para
	 * renderizar ou n�o os command buttons na view
	 */
	public boolean isAtualizacao() {
		return medicamentoUsuario != null && medicamentoUsuario.getId() != null;
	}

	/*
	 * Metodo que realiza o autocomplete do input de medicamento na view de
	 * manterUsoMedicamentos
	 */
	public List<Medicamento> completeMedicamento(String query) {
		if (medicamentos.isEmpty())
			medicamentos = medicamentoService.buscaMedicamentoUsuario(query,gerenciarLoginMB.getUsuario());

		List<Medicamento> medicamentosBusca = new ArrayList<Medicamento>();

		for (int i = 0; i < medicamentos.size(); i++) {
			Medicamento medicamento = medicamentos.get(i);
			if (medicamento.getNome().trim().toLowerCase().startsWith(query)) {
				medicamentosBusca.add(medicamento);
			}
		}
		return medicamentosBusca;
	}

	/* Metodo de busca da view Listar */
	public void busca() {
		medicamentosLista = medicamentoService.busca(criterioMedicamento);
	}

	/* GETTERS & SETTERS */
	public List<MedicamentoUsuario> getMedicamentosLista() {
		return medicamentosLista;
	}

	public void setMedicamentosLista(List<MedicamentoUsuario> medicamentosLista) {
		this.medicamentosLista = medicamentosLista;
	}

	public String getCriterioMedicamento() {
		return criterioMedicamento;
	}

	public void setCriterioMedicamento(String criterioMedicamento) {
		this.criterioMedicamento = criterioMedicamento;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public MedicamentoUsuario getMedicamentoUsuario() {
		return medicamentoUsuario;
	}

	public void setMedicamentoUsuario(MedicamentoUsuario medicamentoUsuario) {
		this.medicamentoUsuario = medicamentoUsuario;
	}

}