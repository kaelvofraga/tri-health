package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.edu.ifrs.canoas.tads.lds.bean.Exame;
import br.edu.ifrs.canoas.tads.lds.bean.ExameCampos;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterExamesService;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Named
@SessionScoped
public class ManterExamesMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5731686734009147647L;
	private static final String URL_LISTAR_EXAMES = "/private/pages/listarExames.jsf";
	private static final String URL_MANTER_EXAMES = "/private/pages/manterExames.jsf";

	@EJB
	private ManterExamesService manterExameService;

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	private List<Exame> examesLista = new ArrayList<Exame>();

	private Exame exame;

	private Exame exameEditar;

	public ManterExamesMB() {

	}

	public String initListar() {
		this.clear();
		atualizarExames();
		return URL_LISTAR_EXAMES;
	}

	public String initManter() {
		this.clear();
		this.setExameEditar(new Exame());
		return URL_MANTER_EXAMES;
	}

	public void onExameSelect(SelectEvent event) throws IOException {

	}

	public void atualizarExames() {
		this.setExamesLista(manterExameService.buscaExames());
		if (this.getExamesLista().size() > 0) {
			this.setExame(this.getExamesLista().get(0));
		}
	}

	public String novoExame() throws IOException {
		Exame exameEditarNovo = new Exame();
		return this.beginEditExame(exameEditarNovo);
	}

	public String editarExame() throws IOException {
		if (this.getExame() != null) {
			return this.beginEditExame(this.getExame());
		}

		return "";
	}

	public void removeExameCampos(ExameCampos exameCamposRemover) {
		if (this.getExameEditar() != null && exameCamposRemover != null) {
			this.getExameEditar().removeExameCampos(exameCamposRemover);
		}
	}

	private String beginEditExame(Exame exameParaEdicao) throws IOException {
		this.clear();
		this.setExameEditar(exameParaEdicao);
		return URL_MANTER_EXAMES;
	}

	public void adicionarExameCampos(String nomeCampo, String tipoCampo) {
		if (StrUtil.isNotBlank(nomeCampo)) {

			ExameCampos novoCampo = new ExameCampos();
			novoCampo.setNomeCampo(nomeCampo);
			novoCampo.setTipoCampo(0);
			novoCampo.setExame(this.getExameEditar());
			

			this.getExameEditar().getExamesCampos().add(novoCampo);
		}
	}

	public String salvarExamesComCampos() {
		String retorno = "";
		
		if (this.getExameEditar() != null) {
			Exame exameSalvar = this.getExameEditar();

			if (StrUtil.isNotBlank(exameSalvar.getNome()) == false) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"O nome exame não pode estar vázio.",
								"O nome exame não pode estar vázio."));
			}
			else if (exameSalvar.getExamesCampos().size() == 0) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"O exame deve conter ao menos um campo.",
								"O exame deve conter ao menos um campo."));
			} else {
				String mensagemRetorno = this.manterExameService.salvarExames(this.getExameEditar());
				if (StrUtil.isNotBlank(mensagemRetorno) == false) {
					FacesContext.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_INFO,
											"Salvo com sucesso.",
											"Salvo com sucesso."));
					retorno = this.initListar();
				
				} else {
					FacesContext
							.getCurrentInstance()
							.addMessage(
									null,
									new FacesMessage(
											FacesMessage.SEVERITY_INFO,
											"Ocorreu um erro desconhecido durante a transação." + mensagemRetorno,
											"Ocorreu um erro desconhecido durante a transação." + mensagemRetorno));
				}
			}			
		}
		
		return retorno;
	}

	public String cancelarEdicao(){
		
		return this.initListar();
	}
	
	private void clear() {
		this.setExameEditar(null);
		this.setExame(null);
		this.setExamesLista(new ArrayList<Exame>());
	}

	public List<Exame> getExamesLista() {
		return examesLista;
	}

	public void setExamesLista(List<Exame> examesLista) {
		this.examesLista = examesLista;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exameSelecionado) {
		this.exame = exameSelecionado;
	}

	public List<ExameCampos> getExameCampos() {
		if (this.getExame() == null)
			return new ArrayList<ExameCampos>();

		return this.getExame().getExamesCampos();
	}

	public List<ExameCampos> getExameCamposEditar() {
		if (this.getExameEditar() == null)
			return new ArrayList<ExameCampos>();

		return this.getExameEditar().getExamesCampos();
	}

	public Exame getExameEditar() {
		return exameEditar;
	}

	public void setExameEditar(Exame exameEditar) {
		this.exameEditar = exameEditar;
	}
}
