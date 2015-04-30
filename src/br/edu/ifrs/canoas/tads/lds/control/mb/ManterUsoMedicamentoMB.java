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
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUsoMedicamentoService;

@Named
@RequestScoped
public class ManterUsoMedicamentoMB implements Serializable {

	private static final String URL_LISTAR_USO_MEDICAMENTOS = "#";
	private static final String URL_MANTER_USO_MEDICAMENTOS = "/private/pages/manterPerfilEmergencia.jsf";
	private static final long serialVersionUID = 8840982087710515671L;

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@Inject MedicamentoUsuario medicamentoUsuario;
	
	@EJB
	private ManterUsoMedicamentoService medicamentoService;
	
	
	private List<MedicamentoUsuario> medicamentosLista;
	
	private List<Medicamento> medicamentos;
	
	public void inicializa() {
		medicamentoUsuario = new MedicamentoUsuario();
		medicamentosLista = new ArrayList<>();
	}
	
	
	public String alteraMedicamento() {
		return "manterUsoMedicamentos";
	}
	
	
	public void salvaMedicamento(){
		medicamentoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		medicamentoService.salvaMedicamentoUsuario(medicamentoUsuario);
		this.inicializa();
	}
	
	
	/*public void excluiMedicamento(){
		medicamentoService.excluiMedicamento(medicamentoUsuario);
		return "listarUsoMedicamentos";
	}*/
	
	
	public boolean isAtualizacao(){
		return medicamentoUsuario != null && medicamentoUsuario.getId() != null;
	}
	
	
	public List<Medicamento> completeMedicamento(String query){
		if (medicamentos == null) 
			medicamentos = medicamentoService.buscaMedicamentos(query, gerenciarLoginMB.getUsuario());

		List<Medicamento> medicamentosBusca = new ArrayList<Medicamento>();
         
        for (int i = 0; i < medicamentos.size(); i++) {
            Medicamento medicamento = medicamentos.get(i);
            if(medicamento.getNome().trim().toLowerCase().startsWith(query)) {
            	medicamentosBusca.add(medicamento);
            }
        }
        return medicamentosBusca;
	}
	
	
	
	
	
	
	
	
	
	
	
	public MedicamentoUsuario getMedicamentoUsuario() {
		return medicamentoUsuario;
	}

	public void setMedicamentoUsuario(MedicamentoUsuario medicamentoUsuario) {
		this.medicamentoUsuario = medicamentoUsuario;
	}
	

}