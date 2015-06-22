package br.edu.ifrs.canoas.tads.lds.control.mb;

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
import br.edu.ifrs.canoas.tads.lds.bean.PressaoUsuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterPressaoService;

/** 
 * @author Pablo Diehl da Silva
 * @version 06/05/2015
 * @author Alisson Lorscheiter
 * @version 22/06/2015
 * Adição dos metodos,initManter e initListar.
 * Adição de comentários aos métodos.
 * Criação método busca.
 * Alterações metodo de salvar,alterar e excluir.
 * Criação do método onRowSelect
 *
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
	private String criterioPressao;
	
	
	/** 
	 * @brief Metodo que inicializa as variaveis ao ser selecionada a opção de listar
	 * as medidas de pressão do usuário.	 		  
	 * @param void
	 * @return String
	 * */
	public String initListar() {
		pressaoUsuario = new PressaoUsuario();
		criterioPressao = "";
		listaDePressao = new ArrayList<PressaoUsuario>();
		return URL_LISTAR_PRESSAO;
	}

	/** 
	 * @brief Metodo que inicializa as variaveis ao ser selecionada a opção de cadastrar
	 * as medidas de pressão do usuário.	 		  
	 * @param void
	 * @return String
	 * */
	public String initManter() {
		pressaoUsuario = new PressaoUsuario();
		return URL_MANTER_PRESSAO;
	}
	
	/** 
	 * @brief Metodo que verifica se pressaoUsuario está sendo atualizado
	 * ou é um novo cadastro. 		  
	 * @param void
	 * @return boolean
	 * */
	public boolean isAtualizacao() {
		return pressaoUsuario != null && pressaoUsuario.getId() != null;
	}
	
	
	/** 
	 * @brief Metodo que salva a pressaoUsuario no banco de dados	 		  
	 * @param void
	 * @return void
	 * */
	public void salvaPressao(){
		pressaoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		if(pressaoService.salvaPressaoUsuario(pressaoUsuario)==true){
		this.initManter();
		}
	}
	
	/** 
	 * @brief Metodo que faz alterações no banco de dados das informações de pressaoUsuario.
	 * @param void
	 * @return String
	 * */
	public String alteraPressao() {
		if(pressaoService.alteraPressaoUsuario(pressaoUsuario)==true){
		return URL_LISTAR_PRESSAO;
		}
		else{
			return URL_MANTER_PRESSAO;
		}
	}
	
	/** 
	 * @brief Metodo que exclui do banco de dados a pressaoUsuario, retorna a URL 
	 * da pagina que sera redirecionado.	 		  
	 * @param void
	 * @return String
	 * */
	public String excluiPressao() {
		if (pressaoService.excluiPressaoUsuario(pressaoUsuario)) {
			this.busca();
			return URL_LISTAR_PRESSAO;
		}
		return URL_MANTER_PRESSAO;
	}
	
	/** 
	 * @brief Metodo que realiza a busca da view de listagem de pressões do usuario.	 		  
	 * @param void
	 * @return void
	 * */
	public void busca() {
		listaDePressao = pressaoService.busca(criterioPressao);
	}
	
	/** 
	 * @brief Metodo que realiza o evento de seleção da linha da tabela que lista
	 *  as pressões do usuário.	 		  
	 * @param event (SelectEvent)
	 * @return void
	 * */
	public void onRowSelect(SelectEvent event) throws IOException {
		this.pressaoUsuario = (PressaoUsuario) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext().redirect("../../private/pages/manterPressao.jsf");
    }
	

	
	
	/* Getters & Setters*/

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

	public String getCriterioPressao() {
		return criterioPressao;
	}

	public void setCriterioPressao(String criterioPressao) {
		this.criterioPressao = criterioPressao;
	}
	
	

}
