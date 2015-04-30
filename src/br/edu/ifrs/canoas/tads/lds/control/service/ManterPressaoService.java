package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.bean.PressaoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.PressaoUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterPressaoService {
	@Inject
	private PressaoUsuarioDAO pressaoUsuarioDAO;

	public boolean salvaPressaoUsuario(PressaoUsuario pressaoUsuario) {
		if (pressaoUsuario != null && pressaoUsuario.getId() != null) {
			pressaoUsuarioDAO.insere(pressaoUsuario);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"Pressao.cadastro.sucesso");
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Pressao.cadastro.erro");
		}
		return true;
	}

	public PressaoUsuario buscaPressaoUsuarioPorID(PressaoUsuario pressaoUsuario) {
		PressaoUsuario pu = pressaoUsuarioDAO.busca(pressaoUsuario.getId());
		if (pressaoUsuario != null)
			pressaoUsuario = pu;
		return pressaoUsuario;
	}

	public List<PressaoUsuario> buscaPressoes(Usuario usuario) {
		if (usuario != null && usuario.getId() != null)
			return pressaoUsuarioDAO.buscaPressaoPorUsuario(usuario);
		return new ArrayList<PressaoUsuario>();
	}

	public void alteraPressaoUsuario(PressaoUsuario pressaoUsuario) {
		pressaoUsuarioDAO.atualiza(pressaoUsuario);
	}

	public void excluiPressao(PressaoUsuario pressaoUsuario) {
		pressaoUsuarioDAO.exclui(pressaoUsuario.getId());
	}
}
