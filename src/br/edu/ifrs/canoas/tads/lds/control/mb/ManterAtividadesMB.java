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
	
	public String initListar(){			
		/** Busca **/
		criterioAtividadeUsuario = "";
		
		/** Listas **/
		atividadeUsuarioList = new ArrayList<>();
		
		return URL_LISTAR_ATIVIDADES;
	}
	
	@PostConstruct
	public String initManter(){			
		/** Zera POJO **/
		atividadeUsuario = new AtividadeUsuario();
		
		/** Listas **/
		tipoAtividadeList = atvUsuarioService.buscaNomeTipoAtividades();
		
		atividadeList = atvUsuarioService.buscaDescricoesAtividades();
		
		atividadeListFiltrada = new ArrayList<>();
		this.filtrarAtividades();	
		
		return URL_MANTER_ATIVIDADES;
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
			this.initManter();
	}
	
	/** 
	 * @brief Atualiza dados da atividade no BD.	  	 		  
	 * @param void
	 * @return String: url da listagem de atividades
	 * */
	public String alteraAtividadeUsuario(){
		atvUsuarioService.alteraAtividadeUsario(atividadeUsuario);
		this.initManter();
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
	 * @brief Atribui valor de calorias queimadas a atividadeUsuario que está sendo trabalhada.	  	 		  
	 * @param void
	 * @return void
	 * */
	public void calculaCalorias(){
		this.atividadeUsuario.setCalorias(atvUsuarioService.calculaCaloriasQueimadas(this.atividadeUsuario));			
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
