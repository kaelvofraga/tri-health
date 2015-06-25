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
	public boolean salvaComposicao(ComposicaoUsuario adiposa,
			ComposicaoUsuario residual, ComposicaoUsuario muscular,
			ComposicaoUsuario ossea) {

		if (adiposa == null || residual == null || muscular == null
				|| ossea == null || adiposa.getUsuario() == null
				|| residual.getUsuario() == null
				|| muscular.getUsuario() == null || ossea.getUsuario() == null){
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "Deu treta");
			return false;
		}
		
		if(adiposa.getValor() + residual.getValor() + muscular.getValor() + ossea.getValor() != 100){
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "Soma das percentagens deve ser igual a 100!");
			return false;
		}
		
		residual.setData(adiposa.getData());
		muscular.setData(adiposa.getData());
		ossea.setData(adiposa.getData());
		
		residual.setNotas(adiposa.getNotas());
		muscular.setNotas(adiposa.getNotas());
		ossea.setNotas(adiposa.getNotas());
		
		composicaoUsuarioDAO.insere(adiposa);
		composicaoUsuarioDAO.insere(residual);
		composicaoUsuarioDAO.insere(muscular);
		composicaoUsuarioDAO.insere(ossea);
		
		Mensagens.define(FacesMessage.SEVERITY_INFO,
				"Entrou!");

		return true;
	}
}
