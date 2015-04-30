package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Cidade;
import br.edu.ifrs.canoas.tads.lds.model.dao.CidadeDAO;


@Stateless
public class ManterCidadeService  {
	@Inject
	private CidadeDAO  cidadeDAO ;
	
	public List<Cidade> buscaCidades() {
		return  cidadeDAO.buscaTodos();
	}
	

}