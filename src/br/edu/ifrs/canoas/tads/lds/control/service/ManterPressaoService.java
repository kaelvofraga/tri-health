package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.PressaoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.PressaoArterial;
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
	private PressaoArterialDAO pressaoDAO;

	public boolean salvaPressaoUsuario(PressaoUsuario pressaoUsuario) {
		pressaoUsuarioDAO.insere(pressaoUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO,null);
		return true;
	}

	public List<PressaoArterial> buscaPressoes(Date query, Usuario usuario) {
		if (usuario != null && usuario.getId() != null)
			return pressaoDAO.buscaPressaoPorUsuario(usuario);
		return new ArrayList<PressaoArterial>();
	}

	
	public void alteraPressaoUsario(PressaoUsuario pressaoUsuario) {
		pressaoUsuarioDAO.atualiza(pressaoUsuario);
	}

	public void excluiPressao(PressaoUsuario pressaoUsuario) {
		pressaoUsuarioDAO.exclui(pressaoUsuario.getId());
	}

}
