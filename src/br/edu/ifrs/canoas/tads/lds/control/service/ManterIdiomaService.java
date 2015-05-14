package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Idioma;
import br.edu.ifrs.canoas.tads.lds.model.dao.IdiomasDAO;

/**
 * 
 * @author: Miromar J. Lima
 * Proposito da Classe: Intermediar a camada de interface com o usuário e o banco de dados
 *         
 */
@Stateless

public class ManterIdiomaService {
	@Inject
	private IdiomasDAO idiomaDAO;
	
	public List<Idioma> buscaIdiomas() {
		return  idiomaDAO.buscaTodos();
	}
}
