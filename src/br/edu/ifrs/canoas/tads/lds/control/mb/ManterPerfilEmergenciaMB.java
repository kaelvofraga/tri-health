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
	private GerenciarLoginMB gerenciarLoginMB;

	@EJB
	private ManterAlergiaService alergiaService;
	
	private AlergiaUsuario alergiaUsuario;
	

	//Lista Alergia
	private List<AlergiaUsuario> alergias;
	private String criterioAlergia;
	
	//Form Alergia
	private Medicamento medicamento;
	private TipoAlergia tipoAlergia;
	private List<Medicamento> medicamentos;
	private List<TipoAlergia> tipoAlergias;

	public ManterPerfilEmergenciaMB() {
		this.reset();
	}
	
	private void reset(){
		alergiaUsuario = new AlergiaUsuario();
		medicamento = new Medicamento();
		tipoAlergia = new TipoAlergia();
		criterioAlergia = "";
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
	
	public List<TipoAlergia> completeTipoAlergia(String query){
		if (tipoAlergias == null)
			tipoAlergias = alergiaService.buscaDescricoesTipoAlergias();	
		
		return tipoAlergias;
	}
	
	public void salvaAlergia(){
		alergiaUsuario.setUsuario(gerenciarLoginMB.getUsuario());
		alergiaService.salvaUsario(alergiaUsuario, medicamento, tipoAlergia);
		this.reset();
	}
	
	public String editarAlergiaUsuario(AlergiaUsuario alergia){
		this.alergiaUsuario = alergia;
		return "/private/pages/manterPerfilEmergencia";
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


	public List<TipoAlergia> getTipoAlergias() {
		return tipoAlergias;
	}


	public void setTipoAlergias(List<TipoAlergia> tipoAlergias) {
		this.tipoAlergias = tipoAlergias;
	}
	

}