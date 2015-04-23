package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUsoMedicamentoService;

@Named
@RequestScoped
public class ManterUsoMedicamentoMB implements Serializable {


	private static final long serialVersionUID = 8840982087710515671L;

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@Inject MedicamentoUsuario medicamentoUsuario;
	
	@EJB
	private ManterUsoMedicamentoService medicamentoService;
	
	
	private List<MedicamentoUsuario> medicamentos;
	
	
	public void inicializa() {
		medicamentoUsuario = new MedicamentoUsuario();
		medicamentos = new ArrayList();
	}
	
	
	public String alteraMedicamento() {
		return "manterUsoMedicamentos";
	}
	
	
	public void salvaMedicamento(){
		medicamentoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		medicamentoService.salvaUsario(medicamentoUsuario);
		this.inicializa();
	}
	
	
	/*public void excluiMedicamento(){
		medicamentoService.excluiMedicamento(medicamentoUsuario);
		return "listarUsoMedicamentos";
	}*/
	
	
	public boolean isAtualizacao(){
		return medicamentoUsuario != null && medicamentoUsuario.getId() != null;
	}
	
	public MedicamentoUsuario getMedicamentoUsuario() {
		return medicamentoUsuario;
	}

	public void setMedicamentoUsuario(MedicamentoUsuario medicamentoUsuario) {
		this.medicamentoUsuario = medicamentoUsuario;
	}
	

}