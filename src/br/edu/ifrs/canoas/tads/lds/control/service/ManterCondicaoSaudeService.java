package br.edu.ifrs.canoas.tads.lds.control.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.model.dao.CondicaoSaudeUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.StatusSaudeDAO;

/** Service da US Manter Condicao Saude
* @author Luana Gomes
* @since 06/07/2015
* */
@Stateless
public class ManterCondicaoSaudeService {
	
	@Inject
	private StatusSaudeDAO statusSaudeDAO;

	@Inject
	private CondicaoSaudeUsuarioDAO condicaoSaudeUsuarioDAO;

}
