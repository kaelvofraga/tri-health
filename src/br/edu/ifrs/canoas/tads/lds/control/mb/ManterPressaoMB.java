package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifrs.canoas.tads.lds.bean.PressaoArterial;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterPressaoService;

/*
 * @author Pablo Diehl da Silva
 * @version 06/05/2015
 */
@Named
@SessionScoped
public class ManterPressaoMB implements Serializable {
	private static final long serialVersionUID = 1695998945721419655L;
	//private static final String URL_LISTAR_PRESSAO = "/private/pages/listarPressaoArterial.jsf";
	//private static final String URL_MANTER_PRESSAO = "/private/pages/manterPressaoArterial.jsf";
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	@Inject
	private PressaoArterial pressaoArterial;
	@EJB
	private ManterPressaoService pressaoService;
	private List<PressaoArterial> listaDePressao;

	/*
	 * Inicializa atributos para serem utilizados em outros m�todos
	 */
	public void inicializa() {
		pressaoArterial = new PressaoArterial();
		listaDePressao = new ArrayList<>();
	}

	public String alteraPressao() {
		return "manterPressaoService";
	}

	/*
	 * "Envia" objeto do tipo "PressaoUsuario" para o service afim de salv�-lo
	 * na base de dados.
	 */
	public void salvaPressao() {
		pressaoArterial.setUsuario(gerenciarLoginMB.getUsuario());
		pressaoService.salvaPressaoArterial(pressaoArterial);
		this.inicializa();
	}

	/*
	 * Verifica se o objeto que est� sendo manipulado � "novo" e dever� ser
	 * cadastrado em um novo registro da base de dados, ou se ele "j� existe" na
	 * base de dados e dever� ser atualizado.
	 */
	public boolean isAtualizacao() {
		return pressaoArterial != null && pressaoArterial.getId() != null;
	}

	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}

	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}

	public PressaoArterial getPressaoArterial() {
		return pressaoArterial;
	}

	public void setPressaoArterial(PressaoArterial pressaoArterial) {
		this.pressaoArterial = pressaoArterial;
	}

	public ManterPressaoService getPressaoService() {
		return pressaoService;
	}

	public void setPressaoService(ManterPressaoService pressaoService) {
		this.pressaoService = pressaoService;
	}

	public List<PressaoArterial> getListaDePressao() {
		return listaDePressao;
	}

	public void setListaDePressao(List<PressaoArterial> listaDePressao) {
		this.listaDePressao = listaDePressao;
	}

	
}