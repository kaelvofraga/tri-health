package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Peso;
import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterPesoService;

/** 
 * @author Luana
 * @version 06/05/2015
 */

@Named 
@RequestScoped 
public class ManterPesoMB implements Serializable {


	private static final long serialVersionUID = 8840982087710515671L;

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	
	private PesoUsuario pesoUsuario;
	
	@EJB
	private ManterPesoService pesoService;
	
	
	private List<PesoUsuario> valores;
	
	
	public void inicializa() {
		pesoUsuario = new PesoUsuario();
		setValores(new ArrayList<PesoUsuario>());
	}
	
	public void salvaPeso(){
		pesoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		pesoService.salvaPesoUsuario(pesoUsuario);
		this.inicializa();
	}
	

	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}

	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}

	public PesoUsuario getPesoUsuario() {
		return pesoUsuario;
	}

	public void setPesoUsuario(PesoUsuario pesoUsuario) {
		this.pesoUsuario = pesoUsuario;
	}

	public ManterPesoService getPesoService() {
		return pesoService;
	}

	public void setPesoService(ManterPesoService pesoService) {
		this.pesoService = pesoService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<PesoUsuario> getValores() {
		return valores;
	}

	public void setValores(List<PesoUsuario> valores) {
		this.valores = valores;
	}	
	
	
	
}