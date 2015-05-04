package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

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
		
		if(cateterismo.getMedicoSolicitante() == null ||
				cateterismo.getMedicoSolicitante() == null ||
				cateterismo.getLaudo() == null){ 
			return false;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Cateterismo> busca(String criterio) {
		if(criterio != null && criterio != ""){
			return cateterismosDao.buscaPorCriterio(criterio);
		}else{
			return cateterismosDao.buscaTodos();
		}
	}
}
