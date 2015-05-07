package br.edu.ifrs.canoas.tads.lds.control.service;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.PressaoUsuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.PesoUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

/** 
 * @author Luana
 * @version 06/05/2015
 */

@Stateless
public class ManterPesoService {

	@Inject
	private PesoUsuarioDAO pesoUsuarioDAO;

	public Boolean salvaPesoUsuario(PesoUsuario pesoUsuario) {
	
		if(pesoUsuario == null || pesoUsuario.getNota() == null || pesoUsuario.getUsuario() == null ){			
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "Peso.cadastro.erro");
			return false;
		}		
		pesoUsuarioDAO.insere(pesoUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Peso.cadastro.sucesso");
		return true;   
	}	
}