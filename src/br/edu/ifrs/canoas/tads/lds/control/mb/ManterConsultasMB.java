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

import br.edu.ifrs.canoas.tads.lds.bean.Consulta;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterConsultasService;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;



@Named
@SessionScoped
public class ManterConsultasMB implements Serializable {
	private static final long serialVersionUID = -6833487765093285579L;
	private static final String URL_LISTAR = "/private/pages/listarConsulta.jsf";
	private static final String URL_CADASTRAR = "/private/pages/manterConsulta.jsf";
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@EJB
	private ManterConsultasService consultaService;
	
	@Inject 
	private Consulta consulta;	

	private List<Consulta> consultas;
	
	
	public ManterConsultasMB() {
		consulta = new Consulta();
	}
	
	@PostConstruct
	public void init(){
		/** Inicia Lista **/
		//consultas = consultaService.buscaTodos();
		
	}

	public void busca(){
//		consultas = consultaService.busca...
	}

	public void salva(){
		Usuario user = gerenciarLoginMB.getUsuario();
		consultaService.insere(consulta, user);
		consulta = new Consulta();
	}

	public void altera(){
		consultaService.salva(consulta);
	}
	
	public String exclui(){
		consultaService.exclui(consulta);
		System.out.println("Excluido!!!");
		return URL_LISTAR;
	}
	
	public String voltarParaListar(){
		return URL_LISTAR;
	}
	
	public String novaConsulta(){
		consulta = new Consulta();
		return URL_CADASTRAR;
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		this.consulta = (Consulta) event.getObject();
        FacesContext.getCurrentInstance().getExternalContext().redirect("manterConsulta.xhtml");
    }
	
	public boolean isAtualizacao(){
		return consulta != null && consulta.getId() != null;
	}
	
	/*
	 * Gets e Sets
	 */	
	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public List<Consulta> getListaConsultas() {
		return consultaService.buscaTodos();
	}

	public List<Consulta> getConsultas() {
		return this.consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
	
	public List<Medico> completeMedico(String query) {
        return consultaService.getMedico(query);
    }

}
