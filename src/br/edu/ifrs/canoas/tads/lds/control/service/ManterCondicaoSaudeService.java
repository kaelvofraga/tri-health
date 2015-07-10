package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.CondicaoSaudeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.StatusSaude;
import br.edu.ifrs.canoas.tads.lds.model.dao.CondicaoSaudeUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.StatusSaudeDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

/** Service da US Manter Condicao Saude
* @author Luana Gomes
* @since 06/07/2015
* */
@Stateless
public class ManterCondicaoSaudeService {
	
	@Inject
	private StatusSaudeDAO statusDAO;

	@Inject
	private CondicaoSaudeUsuarioDAO condicaoSaudeUsuarioDAO;
	
	/* Metodo busca que retorna todos objetos Udm cadastrados.*/
	public List<StatusSaude> buscaStatus() {

		return statusDAO.buscaTodos();
	}

	public boolean salvaCondicaoUsuario(CondicaoSaudeUsuario condicaoSaude) {
		if(condicaoSaude == null  || condicaoSaude.getUsuario() == null){
			return false;
		}	    
		condicaoSaudeUsuarioDAO.insere(condicaoSaude);
		Mensagens.define(FacesMessage.SEVERITY_INFO,
				"manterCondicaoSaude.salvar.sucesso");
		return true;
	}

}
