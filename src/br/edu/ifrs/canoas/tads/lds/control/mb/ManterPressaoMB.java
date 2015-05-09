package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.PressaoUsuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterPressaoService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUsoMedicamentoService;

/*
 * @author Pablo Diehl da Silva
 * @version 06/05/2015
 */

@Named
@SessionScoped
public class ManterPressaoMB implements Serializable {

	private static final long serialVersionUID = 1695998945721419655L;
	private static final String URL_LISTAR_PRESSAO = "/private/pages/listarPressaoArterial.jsf";
	private static final String URL_MANTER_PRESSAO = "/private/pages/manterPressaoArterial.jsf";

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@Inject
	private PressaoUsuario pressaoUsuario;

	@EJB
	private ManterPressaoService pressaoService;

	private List<PressaoUsuario> listaDePressao;

	/*
	 * Inicializa atributos para serem utilizados em outros métodos
	 */

	public void inicializa() {
		pressaoUsuario = new PressaoUsuario();
		listaDePressao = new ArrayList<>();
	}

	public String alteraPressao() {
		return "manterPressaoService";
	}

	/*
	 * "Envia" objeto do tipo "PressaoUsuario" para o service afim de salvá-lo
	 * na base de dados.
	 */

	public void salvaPressao() {
		pressaoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		pressaoService.salvaPressaoUsuario(pressaoUsuario);
		this.inicializa();
	}

	/*
	 * Verifica se o objeto que está sendo manipulado é "novo" e deverá ser
	 * cadastrado em um novo registro da base de dados, ou se ele "já existe" na
	 * base de dados e deverá ser atualizado.
	 */
	public boolean isAtualizacao() {
		return pressaoUsuario != null && pressaoUsuario.getId() != null;
	}

	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}

	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}

	public PressaoUsuario getPressaoUsuario() {
		return pressaoUsuario;
	}

	public void setPressaoUsuario(PressaoUsuario pressaoUsuario) {
		this.pressaoUsuario = pressaoUsuario;
	}

	public ManterPressaoService getPressaoService() {
		return pressaoService;
	}

	public void setPressaoService(ManterPressaoService pressaoService) {
		this.pressaoService = pressaoService;
	}

	public List<PressaoUsuario> getListaDePressao() {
		return listaDePressao;
	}

	public void setListaDePressao(List<PressaoUsuario> listaDePressao) {
		this.listaDePressao = listaDePressao;
	}

}
