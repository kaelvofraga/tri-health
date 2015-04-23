package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;


import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.MedicamentoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.MedicamentoUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterUsoMedicamentoService {

	@Inject
	private MedicamentoUsuarioDAO medicamentoUsuarioDAO;

	@Inject
	private MedicamentoDAO medicamentoDAO;
	
	
	public boolean salvaUsario(MedicamentoUsuario medicamentoUsuario) {
		
//		MedicamentoUsuario medUsuario = new MedicamentoUsuario();
//		medUsuario.setMedicamento(this.buscaOuCriaMedicamentoPorNome(medicamento));
//		medUsuario.setUsuario(alergiaUsuario.getUsuario());
//		
//		medicamentoUsuarioDAO.insere(medUsuario);
//		
//		alergiaUsuario.setMedicamentoUsuario(medUsuario);
//		alergiaUsuario.setTipoAlergia(tipoAlergiaDAO.busca(tipoAlergia.getId()));
		
		medicamentoUsuarioDAO.insere(medicamentoUsuario);
		//Mensagens.define(FacesMessage.SEVERITY_INFO, "Medicamento.cadastro.sucesso");
		
		return true;
	}

	public Medicamento buscaOuCriaMedicamentoPorNome(Medicamento medicamento) {
		List<Medicamento> medicamentos = medicamentoDAO.buscaPorNome(medicamento.getNome());
		
		if (medicamentos.size() == 1)
			medicamento = medicamentos.get(0);
		else{
			medicamentoDAO.insere(medicamento);
		}
		
		return medicamento;
	}

/* 
	@SuppressWarnings("unchecked")
	public List<AlergiaUsuario> busca(String criterioAlergia) {
		if (StrUtil.isNotBlank(criterioAlergia))
			return alergiaUsuarioDAO.buscaPorCriterio(criterioAlergia);
		else
			return alergiaUsuarioDAO.buscaTodos();
	}*/

	public List<Medicamento> buscaMedicamentos(String query, Usuario usuario) {
		if (usuario != null && usuario.getId() != null)
			return medicamentoDAO.buscaNomeMedicamentoPorUsuario(usuario);
		return new ArrayList<Medicamento>();
	}

/*
	public List<TipoAlergia> buscaDescricoesTipoAlergias() {
		return  tipoAlergiaDAO.buscaTodos();
	}*/


	public void alteraMedicamentoUsario(MedicamentoUsuario medicamentoUsuario) {
		medicamentoUsuarioDAO.atualiza(medicamentoUsuario);
	}

	public void excluiMedicamento(MedicamentoUsuario medicamentoUsuario) {
		medicamentoUsuarioDAO.exclui(medicamentoUsuario.getId());
	}

}
