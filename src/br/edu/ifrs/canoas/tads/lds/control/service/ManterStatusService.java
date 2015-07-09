package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.StatusSaude;
import br.edu.ifrs.canoas.tads.lds.model.dao.StatusSaudeDAO;

/**
 * @author Luana Gomes
 */

@Stateless
public class ManterStatusService {

	@Inject
	private StatusSaudeDAO statusDAO;
	
	/* Metodo busca que retorna todos objetos Udm cadastrados.*/
	public List<StatusSaude> buscaStatus() {
		return statusDAO.buscaTodos();
	}
	
}
