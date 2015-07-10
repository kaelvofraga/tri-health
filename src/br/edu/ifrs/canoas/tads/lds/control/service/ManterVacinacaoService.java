package br.edu.ifrs.canoas.tads.lds.control.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.model.dao.VacinacaoDAO;

@Stateless
public class ManterVacinacaoService {

	@Inject
	VacinacaoDAO vacinacaoDAO;
	
}