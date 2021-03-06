package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import br.edu.ifrs.canoas.tads.lds.bean.ComposicaoUsuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterComposicaoService;

/**
 * @brief ManageBean de Listagem e Manutenção de Composições Corporais
 * @author Pablo Diehl
 * @version 24/06/2015
 *
 */
@Named
@SessionScoped
public class ManterComposicoesCorporaisMB implements Serializable {

	private static final long serialVersionUID = 6061701751807684892L;
	private static final String URL_LISTAR_COMPOSICOES_CORPORAIS = "/private/pages/listarComposicoesCorporais.jsf";
	private static final String URL_MANTER_COMPOSICOES_CORPORAIS = "/private/pages/manterComposicoesCorporais.jsf";
	private static final String URL_EDITAR_COMPOSICOES_CORPORAIS = "/private/pages/editarComposicoesCorporais.jsf";

	@EJB
	private ManterComposicaoService composicaoService;

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@Inject
	private ComposicaoUsuario composicaoUsuario;

	private ComposicaoUsuario composicaoListada;

	private List<ComposicaoUsuario> listaComposicaoUsuario;

	private long criterio;

	private PieChartModel grafico;

	private Date data;

	/**
	 * @brief Inicializa a exibição dos gráficos de composições corporais
	 * @autor Pablo Diehl
	 * @version 30/06/2015
	 **/
	public String initListar() {
		data = new Date(0);

		criterio = data.getTime();

		composicaoListada = new ComposicaoUsuario();

		this.busca();

		grafico = new PieChartModel();
		if (composicaoListada != null) {
			grafico.set("Massa Adiposa", composicaoListada.getAdiposa());
			grafico.set("Massa Residual", composicaoListada.getResidual());
			grafico.set("Massa Óssea", composicaoListada.getOssea());
			grafico.set("Massa Muscular", composicaoListada.getMuscular());
		} else {
			grafico.set("Massa Adiposa", 0);
			grafico.set("Massa Residual", 0);
			grafico.set("Massa Óssea", 0);
			grafico.set("Massa Muscular", 0);
		}

		grafico.setTitle("Composições Corporais");
		grafico.setLegendPosition("w");
		grafico.setShowDataLabels(true);

		return URL_LISTAR_COMPOSICOES_CORPORAIS;
	}

	/**
	 * @brief Inicializa o cadastro de composições corporais
	 * @autor Pablo Diehl
	 * @version 29/06/2015
	 **/
	public String initManter() {
		composicaoUsuario = new ComposicaoUsuario();

		return URL_MANTER_COMPOSICOES_CORPORAIS;
	}

	/**
	 * @brief Salva registro de composições corporais no banco
	 * @autor Pablo Diehl
	 * @version 01/07/2015
	 **/
	public void salvaComposicao() {
		composicaoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		if (composicaoService.salvaComposicao(composicaoUsuario)) {
			this.initManter();
		}
	}

	/**
	 * @brief Realiza busca por composições corporais
	 * @autor Pablo Diehl
	 * @version 29/06/2015
	 **/
	public void busca() {
		composicaoListada = composicaoService.buscaGeral(criterio,
				gerenciarLoginMB.getUsuario());
	}

	/**
	 * @brief Exclui registro de composições corporais selecionado
	 * @autor Pablo Diehl
	 * @version 01/07/2015
	 **/
	public String exclui() {
		composicaoService.exclui(composicaoListada);
		return this.initManter();
	}

	/**
	 * @brief Verifica se a soma dos valores de entrada de um novo registro de
	 *        composições corporais não ultrapassa o limite de 100%
	 * @autor Pablo Diehl
	 * @version 29/06/2015
	 **/
	public double getTotal() {
		double total = 0.0;

		total = this.composicaoUsuario.getAdiposa()
				+ this.composicaoUsuario.getMuscular()
				+ this.composicaoUsuario.getOssea()
				+ this.composicaoUsuario.getResidual();

		return total;
	}

	/**
	 * @brief Inicializa a edição de registros de composições corporais
	 * @autor Pablo Diehl
	 * @version 01/07/2015
	 **/
	public String edita() {
		composicaoUsuario = composicaoListada;

		return URL_EDITAR_COMPOSICOES_CORPORAIS;
	}

	/**
	 * @brief Realiza a edição de um registro de composições corporais
	 * @autor Pablo Diehl
	 * @version 29/06/2015
	 **/
	public void editaComposicao() {
		composicaoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		if (composicaoService.editaComposicao(composicaoUsuario)) {
			this.initManter();
		}
	}

	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}

	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}

	public ManterComposicaoService getComposicaoService() {
		return composicaoService;
	}

	public void setComposicaoService(ManterComposicaoService composicaoService) {
		this.composicaoService = composicaoService;
	}

	public ComposicaoUsuario getComposicaoUsuario() {
		return composicaoUsuario;
	}

	public void setComposicaoUsuario(ComposicaoUsuario composicaoUsuario) {
		this.composicaoUsuario = composicaoUsuario;
	}

	public List<ComposicaoUsuario> getListaComposicaoUsuario() {
		return listaComposicaoUsuario;
	}

	public void setListaComposicaoUsuario(
			List<ComposicaoUsuario> listaComposicaoUsuario) {
		this.listaComposicaoUsuario = listaComposicaoUsuario;
	}

	public ComposicaoUsuario getComposicaoListada() {
		return composicaoListada;
	}

	public void setComposicaoListada(ComposicaoUsuario composicaoListada) {
		this.composicaoListada = composicaoListada;
	}

	public PieChartModel getGrafico() {
		return grafico;
	}

	public void setGrafico(PieChartModel grafico) {
		this.grafico = grafico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public long getCriterio() {
		return criterio;
	}

	public void setCriterio(long criterio) {
		this.criterio = criterio;
	}
}
