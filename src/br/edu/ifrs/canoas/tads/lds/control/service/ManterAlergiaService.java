package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.AlergiaUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlergia;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.AlergiaUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.MedicamentoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.MedicamentoUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAlergiaDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterAlergiaService {

	@Inject
	private AlergiaUsuarioDAO alergiaUsuarioDAO;
	
	@Inject
	private MedicamentoUsuarioDAO medicamentoUsuarioDAO;

	@Inject
	private MedicamentoDAO medicamentoDAO;
	
	@Inject
	private TipoAlergiaDAO tipoAlergiaDAO;
	
	public boolean salvaUsario(AlergiaUsuario alergiaUsuario, Medicamento medicamento, TipoAlergia tipoAlergia) {
		
		MedicamentoUsuario medUsuario = new MedicamentoUsuario();
		medUsuario.setMedicamento(this.buscaOuCriaMedicamentoPorNome(medicamento));
		medUsuario.setUsuario(alergiaUsuario.getUsuario());
		
		medicamentoUsuarioDAO.insere(medUsuario);
		
		alergiaUsuario.setMedicamentoUsuario(medUsuario);
		alergiaUsuario.setTipoAlergia(tipoAlergiaDAO.buscaPorDescricao(tipoAlergia).get(0));
		
		alergiaUsuarioDAO.insere(alergiaUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Usuario.cadastro.sucesso",alergiaUsuario.getTipoAlergia().getDescricao());
		
		return true;
	}

	

	private Medicamento buscaOuCriaMedicamentoPorNome(Medicamento medicamento) {
		List<Medicamento> medicamentos = medicamentoDAO.buscaPorNome(medicamento.getNome());
		
		if (medicamentos.size() == 1)
			medicamento = medicamentos.get(0);
		else{
			medicamentoDAO.insere(medicamento);
		}
		
		return medicamento;
	}



	@SuppressWarnings("unchecked")
	public List<AlergiaUsuario> busca(String criterioAlergia) {
		if (StrUtil.isNotBlank(criterioAlergia)) 
			return alergiaUsuarioDAO.buscaPorCriterio(criterioAlergia);
		else
			return alergiaUsuarioDAO.buscaTodos();
	}



	public List<Medicamento> buscaMedicamentos(String query, Usuario usuario) {
		if (usuario != null && usuario.getId() != null)
			return medicamentoDAO.buscaNomeMedicamentoPorUsuario(usuario);
		return new ArrayList<Medicamento>();
	}



	public List<String> buscaDescricoesTipoAlergias() {
		List<TipoAlergia> tipoAlergia = tipoAlergiaDAO.buscaTodos();
		List<String> descricoes = new ArrayList<>();
		for (TipoAlergia tipo : tipoAlergia) {
			descricoes.add(tipo.getDescricao());
		}
		return descricoes;
	}

}
