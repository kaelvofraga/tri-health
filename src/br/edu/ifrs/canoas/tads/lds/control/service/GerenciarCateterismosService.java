package br.edu.ifrs.canoas.tads.lds.control.service;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Cateterismo;
import br.edu.ifrs.canoas.tads.lds.model.dao.CateterismosDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

@Stateless
public class GerenciarCateterismosService {

	@Inject
	private CateterismosDAO cateterismosDao;
	
	public boolean salvaCateterismo(Cateterismo cateterismo) {

		
		if (this.validaExame(cateterismo)) { 
			cateterismosDao.insere(cateterismo);
			//Mensagens.define(FacesMessage.SEVERITY_INFO, "Usuario.cadastro.sucesso",usuario.getEmail());
			return true;
			
		} 
		
		//Mensagens.define(FacesMessage.SEVERITY_ERROR, "Usuario.email.erro.cadastrado",usuario.getEmail());
		return false;
	}

	private boolean validaExame(Cateterismo cateterismo) {
		// TODO Auto-generated method stub
		return false;
	}
}
