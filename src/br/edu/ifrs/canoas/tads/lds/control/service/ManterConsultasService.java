package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.AtividadeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Consulta;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.ConsultaDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

@Stateless
public class ManterConsultasService {
	
	@Inject
	private ConsultaDAO consultaDAO;

	@SuppressWarnings("unchecked")
	public List<Consulta> buscaTodos() {
		return consultaDAO.buscaTodos();
	}	
	
	public boolean salva(Consulta consulta) {
		try{
			consultaDAO.atualiza(consulta);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "Consulta salva com sucesso");
		}catch(Exception e){
			System.out.println("Exception ao Salvar: "+e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}	
	
	public boolean insere(Consulta consulta, Usuario usuario) {
		try{
			consultaDAO.insere(consulta);
			consulta.setUsuario(usuario);
			consultaDAO.atualiza(consulta);	
			Mensagens.define(FacesMessage.SEVERITY_INFO, "Consulta Inserida com sucesso");
		}catch(Exception e){
			System.out.println("Exception ao Inserir: "+e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void exclui(Consulta consulta) {
		if (consulta != null && consulta.getId() != null) {
			consultaDAO.exclui(consulta.getId());
			consulta = null;
			System.out.println("Excluido totality!!!");
			/*
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"Consulta excluida com sucesso");*/
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"Consulta excluida com ERRO");
		}
	}
}