package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.Exame;
import br.edu.ifrs.canoas.tads.lds.bean.ExameCampos;
import br.edu.ifrs.canoas.tads.lds.bean.Resultado;
import br.edu.ifrs.canoas.tads.lds.bean.ResultadoCampos;
import br.edu.ifrs.canoas.tads.lds.control.service.ListarResultadoService;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Named
@SessionScoped
public class ListarResultadoMB implements Serializable {

	private static final String URL_LISTAR_RESULTADOS = "/private/pages/listarResultado.jsf";
	private static final String URL_MANTER_RESULTADOS = "/private/pages/manterResultados.jsf";

	/**
	 * 
	 */
	private static final long serialVersionUID = 7171528304429784985L;

	private List<Resultado> resultados = new ArrayList<Resultado>();

	@EJB
	private ListarResultadoService listarResultadoService;

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	private Resultado resultado;

	private Resultado resultadoEditar;

	private Exame exameParaNovoRes;

	private List<Exame> examesLista;

	public ListarResultadoMB() {
	}

	public String initListar() {

		this.clear();
		setExamesLista(listarResultadoService.buscarTodosExamesComCampos());
		this.setResultados(this.listarResultadoService
				.buscarTodosResultados(gerenciarLoginMB.getUsuario()));

		if (this.getResultados().size() > 0) {
			this.setResultado(this.getResultados().get(0));
		}

		return URL_LISTAR_RESULTADOS;
	}

	public String initManter() {

		this.clear();
		setExamesLista(listarResultadoService.buscarTodosExamesComCampos());
		this.setResultadoEditar(new Resultado());
		return URL_MANTER_RESULTADOS;
	}

	public String salvarResultadoComCampos() {
		String retorno = "";

		if (this.getResultadoEditar() != null) {
			Resultado resultadoSalvar = this.getResultadoEditar();

			if (resultadoSalvar.getResultadoData() == null) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"O data não pode ser vazia.",
								"O data não pode ser vazia."));
			} else if (resultadoSalvar.getResultadoCamposList().size() == 0) {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"O resultado deve ser de um exame com ao menos um campo.",
										"O resultado deve ser de um exame com ao menos um campo."));
			} else {
				String mensagemRetorno = this.listarResultadoService
						.salvarResultado(this.getResultadoEditar());

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
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO,
									"Ocorreu um erro desconhecido durante a transação."
											+ mensagemRetorno,
									"Ocorreu um erro desconhecido durante a transação."
											+ mensagemRetorno));
				}
			}
		}

		return retorno;
	}

	public String cancelarEdicao() {

		return this.initListar();
	}

	private void clear() {
		this.setResultados(new ArrayList<Resultado>());
		this.setResultado(null);
		this.setResultadoEditar(null);
	}

	public void atualizarResultados() {
		this.setResultados(listarResultadoService
				.buscarTodosResultados(gerenciarLoginMB.getUsuario()));
		if (this.getResultados().size() > 0) {
			this.setResultado(this.getResultados().get(0));
		}
	}

	public String novoResultado() throws IOException {
		if (this.getExameParaNovoRes() == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Selecione um exame.", "Selecione um exame."));
			return "";
		}

		Resultado resultadoEditarNovo = new Resultado();
		resultadoEditarNovo.setResultadoData(new Date());
		resultadoEditarNovo.setUsuario(gerenciarLoginMB.getUsuario());
		resultadoEditarNovo.setExame(this.getExameParaNovoRes());

		for (ExameCampos exameCampos : this.getExameParaNovoRes()
				.getExamesCampos()) {
			ResultadoCampos novoResCampo = new ResultadoCampos();
			novoResCampo.setExameCampos(exameCampos);
			novoResCampo.setResultado(resultadoEditarNovo);
			novoResCampo.setResultadoValor("");
			resultadoEditarNovo.getResultadoCamposList().add(novoResCampo);
		}

		return this.beginEditResultado(resultadoEditarNovo);
	}

	public String editarResultado() throws IOException {
		if (this.getResultado() != null) {
			return this.beginEditResultado(this.getResultado());
		}

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Selecione um resultado para edição.",
						"Selecione um resultado para edição."));

		return "";
	}
	
	public void excluirResultado(){
		String retorno = listarResultadoService.excluirResultado(this.getResultado());
		
		if(StrUtil.isNotBlank(retorno)){
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							retorno,
							retorno));
		}
		else{
			this.clear();
			this.atualizarResultados();
		}
	}

	private String beginEditResultado(Resultado resultadoParaEdicao)
			throws IOException {
		this.clear();
		this.setResultadoEditar(resultadoParaEdicao);
		return URL_MANTER_RESULTADOS;
	}

	public List<Resultado> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}

	public Resultado getResultado() {
		return resultado;
	}

	public void setResultado(Resultado resultado) {
		this.resultado = resultado;
	}

	public List<ResultadoCampos> getResultadoCamposList() {
		if (this.getResultado() == null)
			return new ArrayList<ResultadoCampos>();

		return this.getResultado().getResultadoCamposList();
	}

	public List<ResultadoCampos> getResultadoCamposListEditar() {
		if (this.getResultadoEditar() == null)
			return new ArrayList<ResultadoCampos>();

		return this.getResultadoEditar().getResultadoCamposList();
	}

	public Resultado getResultadoEditar() {
		return resultadoEditar;
	}

	public void setResultadoEditar(Resultado resultadoEditar) {
		this.resultadoEditar = resultadoEditar;
	}

	public List<Exame> getExamesLista() {
		return examesLista;
	}

	public void setExamesLista(List<Exame> examesLista) {
		this.examesLista = examesLista;
	}

	public Exame getExameParaNovoRes() {
		return exameParaNovoRes;
	}

	public void setExameParaNovoRes(Exame exameParaNovoRes) {
		this.exameParaNovoRes = exameParaNovoRes;
	}
}
