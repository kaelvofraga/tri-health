package br.edu.ifrs.canoas.tads.lds.control.service;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.ExameUrina;
import br.edu.ifrs.canoas.tads.lds.model.dao.ExameUrinaDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoExameUrinaDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

@Stateless
public class ManterExameUrinaService {
	
	@Inject
	private ExameUrinaDAO exameUrinaDAO;
	
	@Inject
	private TipoExameUrinaDAO tipoExameUrinaDAO;

	public ExameUrinaDAO getExameUrinaDAO() {
		return exameUrinaDAO;
	}

	public void setExameUrinaDAO(ExameUrinaDAO exameUrinaDAO) {
		this.exameUrinaDAO = exameUrinaDAO;
	}

	public TipoExameUrinaDAO getTipoExameUrinaDAO() {
		return tipoExameUrinaDAO;
	}

	public void setTipoExameUrinaDAO(TipoExameUrinaDAO tipoExameUrinaDAO) {
		this.tipoExameUrinaDAO = tipoExameUrinaDAO;
	}

	public boolean salvaExameUrinaUsuario(ExameUrina exameUrina) {
		exameUrinaDAO.insere(exameUrina);
//		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterMedicamento.cadastro.sucesso");
		
		return true;
		
	}

	
	


}
