package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.AlergiaUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
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
	
	@Inject
	private ManterUsoMedicamentoService manterUsoMedicamentoService;
	
	public boolean salvaUsario(AlergiaUsuario alergiaUsuario) {
		
//		MedicamentoUsuario medUsuario = new MedicamentoUsuario();
//		medUsuario.setMedicamento(this.buscaOuCriaMedicamentoPorNome(medicamento));
//		medUsuario.setUsuario(alergiaUsuario.getUsuario());
//		
//		medicamentoUsuarioDAO.insere(medUsuario);
//		
//		alergiaUsuario.setMedicamentoUsuario(medUsuario);
//		alergiaUsuario.setTipoAlergia(tipoAlergiaDAO.busca(tipoAlergia.getId()));
		
		alergiaUsuarioDAO.insere(alergiaUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Alergia.cadastro.sucesso");
		
		return true;
	}

	private Medicamento buscaOuCriaMedicamentoPorNome(Medicamento medicamento) {
		
		return manterUsoMedicamentoService.buscaOuCriaMedicamentoPorNome(medicamento);
	}

	@SuppressWarnings("unchecked")
	public List<AlergiaUsuario> busca(String criterioAlergia) {
		if (StrUtil.isNotBlank(criterioAlergia))
			return alergiaUsuarioDAO.buscaPorCriterio(criterioAlergia);
		else
			return alergiaUsuarioDAO.buscaTodos();
	}


	public List<Medicamento> buscaMedicamentos(String query, Usuario usuario) {
		
		return manterUsoMedicamentoService.buscaMedicamentoUsuario(query,usuario);
	}


	public List<TipoAlergia> buscaDescricoesTipoAlergias() {
		return  tipoAlergiaDAO.buscaTodos();
	}

	public void alteraAlergiaUsario(AlergiaUsuario alergiaUsuario) {
		alergiaUsuarioDAO.atualiza(alergiaUsuario);
	}

	public void excluiAlergia(AlergiaUsuario alergiaUsuario) {
		alergiaUsuarioDAO.exclui(alergiaUsuario.getId());
	}

}
