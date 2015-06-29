package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.ComposicaoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
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

	/**
	 * @brief Salva novo registro de composições corporais
	 * @autor Pablo Diehl
	 * @version 24/06/2015
	 **/
	public boolean salvaComposicao(ComposicaoUsuario composicaoUsuario) {

		if (composicaoUsuario == null || composicaoUsuario.getUsuario() == null) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterComposicao.cadastro.erro");
			return false;
		}

		if (!validaData(composicaoUsuario)) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterComposicao.cadastro.erro.data");
			return false;
		}

		if (composicaoUsuario.getAdiposa() + composicaoUsuario.getResidual()
				+ composicaoUsuario.getMuscular()
				+ composicaoUsuario.getOssea() != 100.00) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterComposicao.cadastro.erro.nao");
			return false;
		}

		composicaoUsuarioDAO.insere(composicaoUsuario);

		Mensagens.define(FacesMessage.SEVERITY_INFO,
				"manterComposicao.cadastro.sucesso");

		return true;
	}

	/**
	 * @brief Verifica se o novo registro de composição corporal possui uma data
	 *        válida.
	 * @autor Pablo Diehl
	 * @version 29/06/2015
	 **/
	public boolean validaData(ComposicaoUsuario composicaoUsuario) {
		long dataAtual = new Date().getTime();
		long dataInformada = composicaoUsuario.getData().getTime();

		if (dataInformada > dataAtual) {
			return false;
		}

		return true;
	}

	public ComposicaoUsuario buscaGeral(long criterio, Usuario usuario) {
		ComposicaoUsuario composicao = null;

		if (criterio != 0) {
			Date data = new Date(criterio);
			if (composicaoUsuarioDAO.buscaPorCriterio(data, usuario).size() > 0) {
				composicao = composicaoUsuarioDAO.buscaPorCriterio(data,
						usuario).get(0);
			}
		} else {
			if (composicaoUsuarioDAO.buscaComposicoesDoUsuario(usuario).size() > 0) {
				composicao = composicaoUsuarioDAO.buscaComposicoesDoUsuario(
						usuario).get(0);
			}
		}

		return composicao;
	}
}
