package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Pais;
import br.edu.ifrs.canoas.tads.lds.bean.UnidadeFederativa;
import br.edu.ifrs.canoas.tads.lds.model.dao.UnidadeFederativaDAO;

/**
 * 
 * @author: Miromar J. Lima
 * Proposito da Classe: Intermediar a camada de interface com o usuário e o banco de dados
 *         
 */

@Stateless
public class ManterUfService {
	@Inject
	private UnidadeFederativaDAO  ufDAO ;
	
	public List<UnidadeFederativa> buscaUfs() {
		return  ufDAO.buscaTodos();
	}

	public List<UnidadeFederativa> buscaUfsPorPais(Pais pais) {
		return ufDAO.buscaPorPais(pais);
	}
	

}