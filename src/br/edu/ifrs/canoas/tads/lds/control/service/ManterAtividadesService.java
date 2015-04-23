package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.AtividadeDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.AtividadeUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAtividadeDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterAtividadesService {

	@Inject
	private AtividadeUsuarioDAO atividadeUsuarioDAO;

	@Inject
	private AtividadeDAO atividadeDAO;

	@Inject
	private TipoAtividadeDAO tipoAtividadeDAO;

	public boolean salvaAtividadeUsuario(AtividadeUsuario atividadeUsuario) {
		atividadeUsuarioDAO.insere(atividadeUsuario);
		Mensagens
				.define(FacesMessage.SEVERITY_INFO, "Atividade.cadastro.sucesso");

		return true;
	}
	
	public Atividade buscaAtividadePorDescricao(Atividade atividade) {
		List<Atividade> atividades = atividadeDAO
				.buscaPorDescricao(atividade.getDescricao());

		if (atividades.size() == 1)
			atividade = atividades.get(0);

		return atividade;
	}

	@SuppressWarnings("unchecked")
	public List<AtividadeUsuario> busca(String criterioAlergia) {
		if (StrUtil.isNotBlank(criterioAlergia))
			return atividadeUsuarioDAO.buscaPorCriterio(criterioAlergia);
		else
			return atividadeUsuarioDAO.buscaTodos();
	}

	public List<Atividade> buscaAtividades(String query, Usuario usuario) {
		if (usuario != null && usuario.getId() != null)
			return atividadeDAO.buscaAtividadePorUsuario(usuario);
		return new ArrayList<Atividade>();
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoAtividade> buscaDescricoesTipoAtividades() {
		return tipoAtividadeDAO.buscaTodos();
	}

	public void alteraAlergiaUsario(AtividadeUsuario atividadeUsuario) {
		atividadeUsuarioDAO.atualiza(atividadeUsuario);
	}

	public void excluiAlergia(AtividadeUsuario atividadeUsuario) {
		atividadeUsuarioDAO.exclui(atividadeUsuario.getId());
	}
}
