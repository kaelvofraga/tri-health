package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterPesoService;

@Named 
@RequestScoped 
public class ManterPesoMB implements Serializable {


	private static final long serialVersionUID = 8840982087710515671L;

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@Inject 
	private PesoUsuario pesoUsuario;
	
	@EJB
	private ManterPesoService pesoService;
	
	
	//private List<PesoUsuario> valores;
	
	
	public void inicializa() {
		pesoUsuario = new PesoUsuario();
		//valores = new ArrayList<PesoUsuario>();
	}
	
	public void salvaPeso(){
		pesoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		pesoService.salvaPesoUsuario(pesoUsuario);
		this.inicializa();
	}	

}