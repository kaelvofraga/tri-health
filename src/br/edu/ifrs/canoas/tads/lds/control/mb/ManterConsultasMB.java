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

import br.edu.ifrs.canoas.tads.lds.bean.Consulta;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterConsultasService;



@Named
@SessionScoped
public class ManterConsultasMB implements Serializable {
	private static final long serialVersionUID = -6833487765093285579L;
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@EJB
	private ManterConsultasService consultaService;
	
	@Inject private Consulta consulta;	

	private List<Consulta> consultas;
	
	
	public ManterConsultasMB() {
		this.consultas = new ArrayList<Consulta>();
	}
	
	@PostConstruct
	public void init(){
		/** Inicia Lista **/
		consultas = consultaService.buscaTodos();
	}

	public void busca(){
//		consultas = consultaService.busca...
	}

	public String getTeste(){
		return "MB Funcionando";
	}
	
	public void salvaConsulta(){
		Usuario user = gerenciarLoginMB.getUsuario();
		consulta.setUsuario(user);
		if(!user.getConsultas().contains(consulta)){
			user.getConsultas().add(consulta);
		}
		consultaService.salva(consulta);
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		this.consulta = (Consulta) event.getObject();
        FacesContext.getCurrentInstance().getExternalContext().redirect("manterConsultas.xhtml");
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

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}
}
