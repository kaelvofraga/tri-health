package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.PressaoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.PressaoArterialDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.PressaoUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterPressaoService {
	@Inject
	private PressaoUsuarioDAO pressaoUsuarioDAO;

	@Inject
	private PressaoArterialDAO pressaoArterialDAO;

	public boolean salvaPressaoUsuario(PressaoUsuario pressaoUsuario) {
		if (pressaoUsuario != null) {
			pressaoUsuarioDAO.insere(pressaoUsuario);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"Atividade.cadastro.sucesso");
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Atividade.cadastro.erro");
		}
		return true;
	}

	public PressaoUsuario buscaPressaoUsuarioPorID(PressaoUsuario pressaoUsuario) {
		PressaoUsuario pressao = pressaoUsuarioDAO.busca(pressaoUsuario.getId());

		if (pressaoUsuario != null)
			pressaoUsuario = pressao;

		return pressaoUsuario;
	}

	public List<PressaoUsuario> buscaPressoesDoUsuario(Usuario usuario) {

		if (usuario != null && usuario.getId() != null)
			return pressaoUsuarioDAO.buscaPressaoPorUsuario(usuario);

		return new ArrayList<PressaoUsuario>();
	}


	public void alteraPressaoUsario(PressaoUsuario pressaoUsuario) {
		if (pressaoUsuario != null && pressaoUsuario.getId() != null) {
			pressaoUsuarioDAO.atualiza(pressaoUsuario);
			Mensagens.define(FacesMessage.SEVERITY_INFO,null);
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR, null);
		}
	}

	public void excluiPressaoUsuario(PressaoUsuario pressaoUsuario) {
		if (pressaoUsuario != null && pressaoUsuario.getId() != null) {
			pressaoUsuarioDAO.exclui(pressaoUsuario.getId());
			Mensagens.define(FacesMessage.SEVERITY_INFO,null);
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,null);
		}
	}

}
