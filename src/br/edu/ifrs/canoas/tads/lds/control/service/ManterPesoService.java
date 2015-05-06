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

@Stateless
public class ManterPesoService {

	@Inject
	private PesoUsuarioDAO pesoUsuarioDAO;

	public Boolean salvaPesoUsuario(PesoUsuario pesoUsuario) {
		try{
		if(pesoUsuario == null || pesoUsuario.getNota() == null || pesoUsuario.getUsuario() == null ){			
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "Peso.cadastro.erro");
		}
		}catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			System.out.println("user"+pesoUsuario.getNota());
			System.out.println("user"+pesoUsuario.getUsuario());
			System.out.println("user"+pesoUsuario);			
			return false;
		
		}
		pesoUsuarioDAO.insere(pesoUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Peso.cadastro.sucesso");
		return true;
	   
	}	
	}