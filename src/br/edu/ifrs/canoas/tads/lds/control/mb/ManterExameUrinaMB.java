package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.ExameUrina;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterExameUrinaService;

@ManagedBean
public class ManterExameUrinaMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7113326743475818284L;
	
	@Inject
	private ExameUrina exameUrina;
	
	@EJB
	private ManterExameUrinaService exameUrinaService;

	public ExameUrina getExameUrina() {
		return exameUrina;
	}

	public void setExameUrina(ExameUrina exameUrina) {
		this.exameUrina = exameUrina;
	}
	
	public void salva() {
		System.out.println("TipoExame: " + exameUrina.getTipoExameUrina().getNomeExameUrina());
		/*if (exameUrinaService.salvaExameUrina(exameUrina)){
			exameUrina = new ExameUrina();
			return "login";
		}
		else return "manterExameUrina";*/
	}

}

