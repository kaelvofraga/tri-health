package br.edu.ifrs.canoas.tads.lds.control.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.model.dao.ExameUrinaDAO;

@Stateless
public class ManterExameUrinaService {
	
	@Inject
	private ExameUrinaDAO exameUrinaDAO;

	public ExameUrinaDAO getExameUrinaDAO() {
		return exameUrinaDAO;
	}

	public void setExameUrinaDAO(ExameUrinaDAO exameUrinaDAO) {
		this.exameUrinaDAO = exameUrinaDAO;
	}

	
	


}
