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

import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterAtividadesService;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

/** ManageBean das views de Manter e Listar Atividades Físicas
* @author Kael Fraga
* @since 07/05/2015
* */
@Named
@SessionScoped
public class ManterAtividadesMB implements Serializable {

	private static final long serialVersionUID = -8771171961658521800L;
	private static final String URL_LISTAR_ATIVIDADES = "/private/pages/listarAtividadesFisicas.jsf";
	private static final String URL_MANTER_ATIVIDADES = "/private/pages/manterAtividadesFisicas.jsf";
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@EJB
	private ManterAtividadesService atvUsuarioService;
	
	//Beans
	@Inject 
	private AtividadeUsuario atividadeUsuario;	
	
	//Lista Atividades do Usuario
	private List<AtividadeUsuario> atividadeUsuarioList;
	private String criterioAtividadeUsuario;

	//Form Atividades do Usuario
	private List<Atividade> atividadeList;
	private List<Atividade> atividadeListFiltrada;
	private List<TipoAtividade> tipoAtividadeList;

	public ManterAtividadesMB() {
	}
	
	@PostConstruct
	public void init(){		
		/** Busca **/
		if(criterioAtividadeUsuario == null || criterioAtividadeUsuario.length() == 0)
			criterioAtividadeUsuario = "";
		
		/** Listas **/
		if(atividadeUsuarioList == null)
			atividadeUsuarioList = new ArrayList<>();	
		if(tipoAtividadeList == null)
			tipoAtividadeList = atvUsuarioService.buscaNomeTipoAtividades();
		if(atividadeList == null)
			atividadeList = atvUsuarioService.buscaDescricoesAtividades();
		if(atividadeListFiltrada == null)
			atividadeListFiltrada = new ArrayList<>();
	}
	
	/** 
	 * @brief Busca atividades relacionadas ao usuário logado de acordo com um critério de pesquisa.	  	 		  
	 * @param void
	 * @return void
	 * */
	public void busca(){
		atividadeUsuarioList = atvUsuarioService.buscaGeral(criterioAtividadeUsuario, gerenciarLoginMB.getUsuario());
	}
	
	/** 
	 * @brief Zera os atributos do objeto atividadeUsuario e inicializa as listas de atividades e tipos.	  	 		  
	 * @param void
	 * @return void
	 * */
	private void clear() {
		/** Zera POJO **/
		atividadeUsuario = new AtividadeUsuario();
		
		/** Zera Listas **/
		tipoAtividadeList = atvUsuarioService.buscaNomeTipoAtividades();
		atividadeList = atvUsuarioService.buscaDescricoesAtividades();
		this.filtrarAtividades();		
	}
	
	/** 
	 * @brief Reinicia dados exibidos na tabela de listagem.	  	 		  
	 * @param void
	 * @return void
	 * */
	public void clearTable(){
		/** POJO **/
		atividadeUsuario = new AtividadeUsuario();

		/** Zera critério de filtro **/
		criterioAtividadeUsuario = "";

		/** Limpa filtro na lista atividadesUsuarioList **/
		this.busca();
	}
	
	/** 
	 * @brief Vincula usuário logado à atividade e inseri a nova atividade no BD, após limpa formulário
	 * @param void
	 * @return void
	 * **/
	public void salvaAtividadeUsuario(){
		atividadeUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		if(atvUsuarioService.salvaAtividadeUsuario(atividadeUsuario))
			this.clear();
	}
	
	/** 
	 * @brief Atualiza dados da atividade no BD.	  	 		  
	 * @param void
	 * @return String: url da listagem de atividades
	 * */
	public String alteraAtividadeUsuario(){
		atvUsuarioService.alteraAtividadeUsario(atividadeUsuario);
		this.clear();
		return URL_LISTAR_ATIVIDADES;
	}

	/** 
	 * @brief Retorna da view de cadastro para view de listagem.	  	 		  
	 * @param void
	 * @return String: url da listagem de atividades
	 * */
	public String voltarParaListar(){
		this.clear();
		return URL_LISTAR_ATIVIDADES;
	}
	
	/** 
	 * @brief Exclui atividade cadastrada do BD.	  	 		  
	 * @param void
	 * @return String: url da listagem de atividades
	 * */
	public String excluiAtividadeUsuario(){	
		atvUsuarioService.excluiAtividadeUsuario(atividadeUsuario);
		this.busca();
		return URL_LISTAR_ATIVIDADES;
	}
	
	/** 
	 * @brief Redireciona da view de listagem para view de cadastro.	  	 		  
	 * @param void
	 * @return String: url do cadastro de atividades
	 * */
	public String novaAtividadeUsuario(){
		this.clear();
		return URL_MANTER_ATIVIDADES;
	}
	
	/** 
	 * @brief Verifica se a atividade atual está sendo inserida nova ou atualizada uma antiga.	  	 		  
	 * @param void
	 * @return true se está atualizando atividade ou false se não.
	 * */
	public boolean isAtualizacao(){
		return atividadeUsuario != null && atividadeUsuario.getId() != null;
	}
	
	/** 
	 * @brief Filtra lista de atividades de acordo com o tipo escolhido previamente.	  	 		  
	 * @param void
	 * @return void
	 * */
	public void filtrarAtividades(){
		this.atividadeListFiltrada.clear();
		TipoAtividade ta = atividadeUsuario.getAtividade().getTipoAtividade();
		if ( ta != null && ta.getId() != null ){			
			for (int i = 0; i < atividadeList.size(); i++) {
				Atividade atv = atividadeList.get(i);
				if(atv.getTipoAtividade().getId() == ta.getId()) {
					atividadeListFiltrada.add(atv);
				}
			}

		}else{
			atividadeListFiltrada = new ArrayList<>();
		}
	}
	
	/** 
	 * @brief Efetua cálculo de calorias queimadas.	  	 		  
	 * @param void
	 * @return void
	 * */
	public void calculaCalorias(){
		if (atividadeUsuario == null) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Atividade.cadastro.erro.calculaCaloria");
			return;
		}
	
		if(atvUsuarioService.validaDatas(this.atividadeUsuario) == false){
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Atividade.cadastro.erro.dataFinalMenor");
			return;
		}
		
		atividadeUsuario.setDuracao(atvUsuarioService.calculaDuracao(atividadeUsuario));
		
		atividadeUsuario.setCalorias(atvUsuarioService.calculaCaloriasQueimadas(atividadeUsuario));			
	}	
	
	/** 
	 * @brief Evento gerado ao selecionar uma linha da tabela de atividades.	  	 		  
	 * @param void
	 * @return void
	 * */
	public void onRowSelect(SelectEvent event) throws IOException {
		this.atividadeUsuario = (AtividadeUsuario) event.getObject();
		this.filtrarAtividades();
		FacesContext.getCurrentInstance().getExternalContext().redirect("manterAtividadesFisicas.jsf");
    }
	
	
	/*
	 * GETTERS & SETTERS
	 */	
	public AtividadeUsuario getAtividadeUsuario() {
		return atividadeUsuario;
	}

	public void setAtividadeUsuario(AtividadeUsuario atividadeUsuario) {
		this.atividadeUsuario = atividadeUsuario;
	}

	public String getCriterioAtividadeUsuario() {
		return criterioAtividadeUsuario;
	}

	public void setCriterioAtividadeUsuario(String criterioAtividadeUsuario) {
		this.criterioAtividadeUsuario = criterioAtividadeUsuario;
	}

	public List<AtividadeUsuario> getAtividadeUsuarioList() {	
		if(atividadeUsuarioList == null || atividadeUsuarioList.size() == 0)
			this.busca();
		return atividadeUsuarioList;
	}

	public void setAtividadeUsuarioList(List<AtividadeUsuario> atividadeUsuarioList) {
		this.atividadeUsuarioList = atividadeUsuarioList;
	}

	public List<Atividade> getAtividadeList() {
		if (atividadeList == null) 
			atividadeList = atvUsuarioService.buscaDescricoesAtividades();
		return atividadeList;
	}

	public void setAtividadeList(List<Atividade> atividadeList) {
		this.atividadeList = atividadeList;
	}

	public List<TipoAtividade> getTipoAtividadeList() {
		if (tipoAtividadeList == null) 
			tipoAtividadeList = atvUsuarioService.buscaNomeTipoAtividades();
		return tipoAtividadeList;
	}

	public void setTipoAtividadeList(List<TipoAtividade> tipoAtividadeList) {
		this.tipoAtividadeList = tipoAtividadeList;
	}
			
	public List<Atividade> getAtividadeListFiltrada() {
		return atividadeListFiltrada;
	}

	public void setAtividadeListFiltrada(List<Atividade> atividadeListFiltrada) {
		this.atividadeListFiltrada = atividadeListFiltrada;
	}
}
