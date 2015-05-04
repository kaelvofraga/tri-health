package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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

	private static final long serialVersionUID = 6240201264929366814L;
	private static final String URL_LISTAR_USO_MEDICAMENTOS = "/private/pages/listarUsoMedicamentos.jsf";
	private static final String URL_MANTER_USO_MEDICAMENTOS = "/private/pages/manterUsoMedicamentos.jsf";
	

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
 
	private MedicamentoUsuario medicamentoUsuario;
	
	@EJB
	private ManterUsoMedicamentoService medicamentoService;
	
	
	private List<MedicamentoUsuario> medicamentosLista;
	
	private String criterioMedicamento;
	
	private List<Medicamento> medicamentos;
	
	@PostConstruct
	public void init(){
		medicamentoUsuario = new MedicamentoUsuario();
		criterioMedicamento="";
		medicamentosLista = new ArrayList<>();
		//medicamentos = new ArrayList<>();
	}
	
	public void salvaMedicamento(){
		medicamentoUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		medicamentoService.salvaMedicamentoUsuario(medicamentoUsuario);
		this.init();
	}
	
	public String alteraMedicamento() {
		medicamentoService.alteraMedicamentoUsario(medicamentoUsuario);
		return URL_LISTAR_USO_MEDICAMENTOS;
	}
	
	public String excluiMedicamento(){
		medicamentoService.excluiMedicamento(medicamentoUsuario);
		this.busca();
		return URL_LISTAR_USO_MEDICAMENTOS;
	}
	
	
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
	
	public void busca(){
		medicamentosLista = medicamentoService.busca(criterioMedicamento);
	}
	
	
	/*GETTERS & SETTERS*/
	public List<MedicamentoUsuario> getMedicamentosLista() {
		return medicamentosLista;
	}
	
	public void setMedicamentosLista(List<MedicamentoUsuario> medicamentosLista) {
		this.medicamentosLista = medicamentosLista;
	}

	public String getCriterioMedicamento() {
		return criterioMedicamento;
	}

	public void setCriterioMedicamento(String criterioMedicamento) {
		this.criterioMedicamento = criterioMedicamento;
	}

	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}

	public MedicamentoUsuario getMedicamentoUsuario() {
		return medicamentoUsuario;
	}

	public void setMedicamentoUsuario(MedicamentoUsuario medicamentoUsuario) {
		this.medicamentoUsuario = medicamentoUsuario;
	}

}