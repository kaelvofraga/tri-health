package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.edu.ifrs.canoas.tads.lds.bean.ExameVisao;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterExameVisaoService;

@Named
@SessionScoped
public class ManterExameVisaoMB implements Serializable {
	private static final long serialVersionUID = -6833487765093285554L;
	private static final String URL_LISTAR = "/private/pages/listarExameVisao.jsf";
	private static final String URL_MANTER = "/private/pages/manterExameVisao.jsf";
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@EJB
	private ManterExameVisaoService exameVisaoService;
	
	@Inject 
	private ExameVisao exameVisao;	

	// Lista da listagem
		private List<ExameVisao> examesVisao;
		
	// Lista do autoComplete
	private List<Medico> medicos;
		
	//string de busca
	private String criterioExameVisao;
	
	public ManterExameVisaoMB() {
	}
	
	/*
	 * Metodo que limpa ExameVisao,criterio de busca, lista de
	 * ExameVisao ao entrar na view de Listar
	 */
	public String initListar() {
		criterioExameVisao = "";
		clear();
		return URL_LISTAR;
	}

	/*
	 * Metodo que limpa ExameVisao, e lista de ExameVisao
	 * do autocomplete da view de manter
	 */
	public String initManter() {
		clear();
		return URL_MANTER;
	}
	
	/* Metodo que inicializa as variaveis apos Salvar */
	private void clear() {
		exameVisao = new ExameVisao();
		exameVisao.setMedico(new Medico());
		examesVisao = new ArrayList<>();
	}
	
	/*
	 * Metodo que chama o service para salvar o exameVisao para o Usuario que
	 * esta logado
	 */
	public void salvaExameVisao(){
		exameVisao.setUsuario(gerenciarLoginMB.getUsuario());
		exameVisaoService.salvaExameVisao(exameVisao);
		this.clear();
	}
	/*
	 * Metodo que chama o service para alterar o exameVisao passando ela
	 * como parametro retorna a url de listagem.
	 */
	public String alteraExameVisao(){
		exameVisaoService.salva(exameVisao);
		return URL_LISTAR;
	}
	
	/*
	 * Metodo que chama o service para excluir o exameVisao e retorna
	 * URL listar ou manter
	 */
	public String exclui(){
		if (exameVisaoService.exclui(exameVisao)) {
			this.busca();
			return URL_LISTAR;
		}
		return URL_MANTER;
	}
	
	/*
	 * Metodo que pega o evento de selecão da linha da tabela por parametro e
	 * carrega na view manter o objeto exameVisao selecionado.
	 */
	public void onRowSelect(SelectEvent event) throws IOException {
		this.exameVisao = (ExameVisao) event.getObject();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../../private/pages/manterExameVisao.jsf");
    }

	public boolean isAtualizacao(){
		return exameVisao != null && exameVisao.getId() != null;
	}
	
	/*
	 * Metodo que realiza o autocomplete do input de Medico na view de
	 * manterExameVisao
	 */
	public List<Medico> completeMedico(String query) {
		if (medicos == null || medicos.isEmpty())
			medicos = exameVisaoService.buscaMedico(query);
		
		List<Medico> medicosBusca = new ArrayList<Medico>();
		System.out.println(	);
		
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
		examesVisao = exameVisaoService.busca(criterioExameVisao);
	}
	
	public String voltarParaListar(){
		return URL_LISTAR;
	}
	
	public String novaExameVisao(){
		exameVisao = new ExameVisao();
		return URL_MANTER;
	}
	
	/*
	 * Gets e Sets
	 */	

	public List<Medico> getMedicos() {
		return medicos;
	}
	
	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public ExameVisao getExameVisao() {
		return exameVisao;
	}

	public void setExameVisao(ExameVisao exameVisao) {
		this.exameVisao = exameVisao;
	}

	public List<ExameVisao> getExamesVisao() {
		return examesVisao;
	}

	public void setExamesVisao(List<ExameVisao> examesVisao) {
		this.examesVisao = examesVisao;
	}

	public String getCriterioExameVisao() {
		return criterioExameVisao;
	}

	public void setCriterioExameVisao(String criterioExameVisao) {
		this.criterioExameVisao = criterioExameVisao;
	}
}