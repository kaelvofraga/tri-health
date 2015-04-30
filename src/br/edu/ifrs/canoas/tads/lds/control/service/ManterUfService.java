package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.UnidadeFederativa;
import br.edu.ifrs.canoas.tads.lds.model.dao.UnidadeFederativaDAO;


@Stateless
public class ManterUfService {
	@Inject
	private UnidadeFederativaDAO  ufDAO ;
	
	public List<UnidadeFederativa> buscaUfs() {
		return  ufDAO.buscaTodos();
	}
	

}