package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.Date;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.ComposicaoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
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
		
		if((composicaoUsuario.getAdiposa() > 100) || (composicaoUsuario.getResidual() > 100) || (composicaoUsuario.getMuscular() > 100) || (composicaoUsuario.getOssea() > 100)){
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterComposicao.cadastro.erro.demais");
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

	/**
	 * @brief Busca por registros salvos.
	 * @autor Pablo Diehl
	 * @version 29/06/2015
	 **/
	public ComposicaoUsuario buscaGeral(long criterio, Usuario usuario) {
		ComposicaoUsuario composicao = null;

		if (criterio != 0) {
			//Date data = new Date(criterio);
			if (composicaoUsuarioDAO.buscaPorCriterio(criterio, usuario).size() > 0) {
				composicao = composicaoUsuarioDAO.buscaPorCriterio(criterio,
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
	
	/**
	 * @brief Deleta registro de composição selecionado pelo usuário
	 * @autor Pablo Diehl
	 * @version 01/07/2015
	 **/
	public boolean exclui(ComposicaoUsuario excluir){
		try{
			if(excluir != null && excluir.getId() != null){
				composicaoUsuarioDAO.exclui(excluir.getId());
				Mensagens.define(FacesMessage.SEVERITY_INFO,"manterComposicao.exclui.sucesso");
				return true;
			}else{
				Mensagens.define(FacesMessage.SEVERITY_INFO,"manterComposicao.exclui.erro");
				return false;
			}
		}catch(IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterComposicao.exclui.erro");
			return false;
		}
	}
	
	/**
	 * @brief Realiza a edição de um registro de composições corporais
	 * @autor Pablo Diehl
	 * @version 24/06/2015
	 **/
	public boolean editaComposicao(ComposicaoUsuario composicaoUsuario) {

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
		
		if((composicaoUsuario.getAdiposa() > 100) || (composicaoUsuario.getResidual() > 100) || (composicaoUsuario.getMuscular() > 100) || (composicaoUsuario.getOssea() > 100)){
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterComposicao.cadastro.erro.demais");
			return false;
		}

		if (composicaoUsuario.getAdiposa() + composicaoUsuario.getResidual()
				+ composicaoUsuario.getMuscular()
				+ composicaoUsuario.getOssea() != 100.00) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterComposicao.cadastro.erro.nao");
			return false;
		}

		composicaoUsuarioDAO.atualiza(composicaoUsuario);

		Mensagens.define(FacesMessage.SEVERITY_INFO,
				"manterComposicao.cadastro.editado");

		return true;
	}
}
