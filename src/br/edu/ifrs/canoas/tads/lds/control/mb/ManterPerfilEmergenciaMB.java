package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.AlergiaUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlergia;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterAlergiaService;

@Named
@RequestScoped
public class ManterPerfilEmergenciaMB implements Serializable {

	private static final long serialVersionUID = -6762932920422815855L;

	@Inject
	private AlergiaUsuario alergiaUsuario;
		
	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	@EJB
	private ManterAlergiaService alergiaService;
	
	
	//Lista Alergia
	private List<AlergiaUsuario> alergias;
	private String criterioAlergia;
	
	//Form Alergia
	@Inject private Medicamento medicamento;
	@Inject private TipoAlergia tipoAlergia;
	private List<Medicamento> medicamentos;
	private List<String> tipoAlergias;

	public ManterPerfilEmergenciaMB() {
	}
	
	
	public void busca(){
		alergias = alergiaService.busca(criterioAlergia);
	}
	
	public List<Medicamento> completeMedicamentoAlergia(String query){
		if (medicamentos == null) 
			medicamentos = alergiaService.buscaMedicamentos(query, gerenciarLoginMB.getUsuario());

		List<Medicamento> medicamentosFiltrados = new ArrayList<Medicamento>();
         
        for (int i = 0; i < medicamentos.size(); i++) {
            Medicamento medicamento = medicamentos.get(i);
            if(medicamento.getNome().trim().toLowerCase().startsWith(query)) {
            	medicamentosFiltrados.add(medicamento);
            }
        }
        return medicamentosFiltrados;
	}
	
	public List<String> completeTipoAlergia(String query){
		if (tipoAlergias == null)
			tipoAlergias = alergiaService.buscaDescricoesTipoAlergias();	
		
		return tipoAlergias;
	}
	
	public void salvaAlergia(){
		alergiaUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		alergiaService.salvaUsario(alergiaUsuario, medicamento, tipoAlergia);
	}


	
	/*
	 * GETTERS & SETTERS
	 */
	
	public AlergiaUsuario getAlergiaUsuario() {
		return alergiaUsuario;
	}

	public void setAlergiaUsuario(AlergiaUsuario alergiaUsuario) {
		this.alergiaUsuario = alergiaUsuario;
	}

	public String getCriterioAlergia() {
		return criterioAlergia;
	}

	public void setCriterioAlergia(String criterioAlergia) {
		this.criterioAlergia = criterioAlergia;
	}

	public List<AlergiaUsuario> getAlergias() {
		return alergias;
	}


	public void setAlergias(List<AlergiaUsuario> alergias) {
		this.alergias = alergias;
	}


	public Medicamento getMedicamento() {
		return medicamento;
	}


	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}


	public List<Medicamento> getMedicamentos() {
		return medicamentos;
	}


	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}


	public TipoAlergia getTipoAlergia() {
		return tipoAlergia;
	}


	public void setTipoAlergia(TipoAlergia tipoAlergia) {
		this.tipoAlergia = tipoAlergia;
	}


	public List<String> getTipoAlergias() {
		return tipoAlergias;
	}


	public void setTipoAlergias(List<String> tipoAlergias) {
		this.tipoAlergias = tipoAlergias;
	}
}