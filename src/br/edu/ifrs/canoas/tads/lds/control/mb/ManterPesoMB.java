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
import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Udm;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterPesoService;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUdmService;

/** 
 * @author Luana
 * @version 06/05/2015
 * @author Alisson Lorscheiter
 * @version 10/06/2015
 * Adição dos metodos,initManter e initListar.
 * Adição de comentários aos métodos.
 * Criação método busca.
 * Alterações metodo de salvar,alterar e excluir.
 * Alteração no redirect do método onRowSelect
 *
 */

@Named 
@SessionScoped 
public class ManterPesoMB implements Serializable {
	
	private static final long serialVersionUID = 8840982087710515671L;
	private static final String URL_MANTER_PESO = "/private/pages/manterPeso.jsf";
	private static final String URL_LISTAR_PESO = "/private/pages/listarPeso.jsf";

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@Inject
	private PesoUsuario pesoUsuario;
	
	@EJB
	private ManterUdmService manterUdmService;
	
	@EJB
	private ManterPesoService pesoService;
	
	private List<PesoUsuario> pesoUsuarioList;
	private List<Udm> udmLista;
	
	private String criterioPeso;
	
	private Udm udm;	
	
	
	/** 
	 * @brief Metodo que inicializa as variaveis ao ser selecionada a opção de listar
	 * os pesos do usuário.	 		  
	 * @param void
	 * @return String
	 * */
	public String initListar() {
		pesoUsuario = new PesoUsuario();
		criterioPeso = "";
		pesoUsuarioList = new ArrayList<PesoUsuario>();
		return URL_LISTAR_PESO;
	}

	/** 
	 * @brief Metodo que inicializa as variaveis ao ser selecionada a opção de cadastrar
	 * os pesos do usuário.	 		  
	 * @param void
	 * @return String
	 * */
	public String initManter() {
		udm = new Udm();
		udmLista= this.getUdmLista();
		pesoUsuario = new PesoUsuario();
		return URL_MANTER_PESO;
	}
	
	/** 
	 * @brief Metodo que verifica se o pesoUsuario está sendo atualizado
	 * ou é um novo cadastro. 		  
	 * @param void
	 * @return boolean
	 * */
	public boolean isAtualizacao() {
		return pesoUsuario != null && pesoUsuario.getId() != null;
	}
	
	/** 
	 * @brief Metodo que realiza o evento de seleção da linha da tabela que lista
	 *  os pesos do usuário.	 		  
	 * @param event (SelectEvent)
	 * @return void
	 * @version 18/06/2015
	 * Foi alterada a string de redirecionamento do método.
	 * */
	public void onRowSelect(SelectEvent event) throws IOException {
		this.pesoUsuario = (PesoUsuario) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext().redirect("../../private/pages/manterPeso.jsf");
    }	
	
	/** 
	 * @brief Metodo que realiza a busca da view de listagem de pesos do usuario.	 		  
	 * @param void
	 * @return void
	 * */
	public void busca() {
		pesoUsuarioList = pesoService.busca(criterioPeso);
	}

	/** 
	 * @brief Metodo que salva o pesoUsuario no banco de dados	 		  
	 * @param void
	 * @return void
	 * */
	public void salvaPeso(){
		pesoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		if(pesoService.salvaPesoUsuario(pesoUsuario)==true){
		this.initManter();
		}
	}
	
	/** 
	 * @brief Metodo que faz alterações no banco de dados das informações de pesoUsuario.
	 * @param void
	 * @return String
	 * */
	public String alteraPeso() {
		if(pesoService.alteraPesoUsuario(pesoUsuario)==true){
		return URL_LISTAR_PESO;
		}
		else{
			return URL_MANTER_PESO;
		}
	}
	
	/** 
	 * @brief Metodo que exclui do banco de dados o pesoUsuario, retorna a URL 
	 * da pagina que sera redirecionado.	 		  
	 * @param void
	 * @return String
	 * */
	public String excluiPeso() {
		if (pesoService.excluiPesoUsuario(pesoUsuario)) {
			this.busca();
			return URL_LISTAR_PESO;
		}
		return URL_MANTER_PESO;
	}
	
	/** 
	 * @brief Metodo que seta a unidade de medida selecionada. 		  
	 * @param void
	 * @return void
	 * */
	public void onSelectUdm(){
		pesoUsuario.setUdm(udm);
	}
	
	
	/*Getters & Setters*/
	
	public List<Udm> getUdmLista() {
		if(udmLista == null)
			udmLista = manterUdmService.buscaUdm();
		return udmLista;
	}
	
	public void setUdmLista(List<Udm> udmLista) {
		this.udmLista = udmLista;
	}
	
	public String getCriterioPeso() {
		return criterioPeso;
	}

	public void setCriterioPeso(String criterioPeso) {
		this.criterioPeso = criterioPeso;
	}
	
	public PesoUsuario getPesoUsuario() {
		return pesoUsuario;
	}

	public void setPesoUsuario(PesoUsuario pesoUsuario) {
		this.pesoUsuario = pesoUsuario;
	}

	public List<PesoUsuario> getPesoUsuarioList() {
		if(pesoUsuarioList == null || pesoUsuarioList.size() == 0) {
				 pesoUsuarioList = new ArrayList<PesoUsuario>();
		}
		return pesoUsuarioList;
	}
	
	public void setPesoUsuarioList(List<PesoUsuario> pesoUsuarioList) {
		this.pesoUsuarioList = pesoUsuarioList;
	}

	public Udm getUdm() {
		return udm;
	}

	public void setUdm(Udm udm) {
		this.udm = udm;
	}	
}