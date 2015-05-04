package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.ExameUrina;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterExameUrinaService;

@Named
@RequestScoped
public class ManterExameUrinaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7113326743475818284L;
	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@Inject
	private ExameUrina exameUrina;
	
	@EJB
	private ManterExameUrinaService exameUrinaService;
	
	private List<ExameUrina> examesLista;

	public ExameUrina getExameUrina() {
		return exameUrina;
	}

	public void setExameUrina(ExameUrina exameUrina) {
		this.exameUrina = exameUrina;
	}
	
	public void salvaExame(){
		exameUrina.setUsuario(gerenciarLoginMB.getUsuario());
		exameUrinaService.salvaExameUrinaUsuario(exameUrina);
		this.inicializa();
	}
	
	public boolean isAtualizacao(){
		return exameUrina != null && exameUrina.getId() != null;
	}
	
	public void inicializa() {
		exameUrina = new ExameUrina();
		examesLista = new ArrayList<>();
	}

}

/*	public void inicializa() {
		medicamentoUsuario = new MedicamentoUsuario();
		medicamentosLista = new ArrayList<>();
	}

	public void salvaMedicamento(){
		medicamentoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		medicamentoService.salvaMedicamentoUsuario(medicamentoUsuario);
		this.inicializa();
	}*/

