package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Cidade;
import br.edu.ifrs.canoas.tads.lds.bean.UnidadeFederativa;
import br.edu.ifrs.canoas.tads.lds.model.dao.CidadeDAO;



/**
 * 
 * @author: Miromar J. Lima
 * Proposito da Classe: Intermediar a camada de interface com o usuário e o banco de dados
 *         
 */
@Stateless
public class ManterCidadeService  {
	@Inject
	private CidadeDAO  cidadeDAO ;
	
	public List<Cidade> buscaCidades() {
		return  cidadeDAO.buscaTodos();
	}

	public List<Cidade> buscaCidadesPorUf(UnidadeFederativa unidadeFederativa) {
		return cidadeDAO.buscaPorUf(unidadeFederativa);
	}
	

}