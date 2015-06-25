package br.edu.ifrs.canoas.tads.lds.control.service;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.ComposicaoUsuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.ComposicaoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.ComposicaoUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

/**
 * @brief Service de Listagem e Manutenção de Composições Corporais
 * @author Pablo Diehl
 * @version 24/06/2015
 *
 */
@Stateless
public class ManterComposicaoService {

	@Inject
	private ComposicaoUsuarioDAO composicaoUsuarioDAO;

	@Inject
	private ComposicaoDAO composicaoDAO;

	/**
	 * @brief Salva novo registro de composições corporais
	 * @autor Pablo Diehl
	 * @version 24/06/2015
	 **/
	public boolean salvaComposicao(ComposicaoUsuario composicaoUsuario) {

		if (composicaoUsuario == null || composicaoUsuario.getUsuario() == null) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "manterComposicao.cadastro.erro");
			return false;
		}

		if (composicaoUsuario.getAdiposa() + composicaoUsuario.getResidual() + composicaoUsuario.getMuscular() + composicaoUsuario.getOssea() != 100) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterComposicao.cadastro.erro.nao");
			return false;
		}

		
		composicaoUsuarioDAO.insere(composicaoUsuario);

		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterComposicao.cadastro.sucesso");

		return true;
	}
}
