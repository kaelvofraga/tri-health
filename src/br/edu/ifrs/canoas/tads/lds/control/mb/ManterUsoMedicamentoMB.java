package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;

@Named
@RequestScoped
public class ManterUsoMedicamentoMB implements Serializable {

	
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@Inject MedicamentoUsuario medicamentoUsuario;

	public MedicamentoUsuario getMedicamentoUsuario() {
		return medicamentoUsuario;
	}

	public void setMedicamentoUsuario(MedicamentoUsuario medicamentoUsuario) {
		this.medicamentoUsuario = medicamentoUsuario;
	}
	
	
	
	

}