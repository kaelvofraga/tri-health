package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.bean.ExameUrinaUsuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.ExameUrinaDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterExameUrinaService {
	
	@Inject
	private ExameUrinaDAO exameUrinaDAO;
	
	
	
	
	
	
	public boolean salvaExameUrinaUsuario(ExameUrinaUsuario exameUrina) {
		exameUrinaDAO.insere(exameUrina);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterExameUrina.cadastro.sucesso");		
		return true;		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ExameUrinaUsuario> busca(String criterioExameUrina) {
		if (StrUtil.isNotBlank(criterioExameUrina))
			return exameUrinaDAO.buscaPorCriterio(criterioExameUrina);
		else
			return exameUrinaDAO.buscaTodos();
	}
	
	public void alteraExameUrina(ExameUrinaUsuario exameUrina) {
		exameUrinaDAO.atualiza(exameUrina);
	}
	
	
	public boolean excluiExameUrina(ExameUrinaUsuario exameUrina) {
		List<Integer> lista = new ArrayList<Integer>();
		lista = exameUrinaDAO.buscaExameUrinaPorUsuario(exameUrina);//ver
		
		if (lista.isEmpty()){
			System.out.println("entrou no empty");
			System.out.println("if empty"+ lista.isEmpty());
			exameUrinaDAO.exclui(exameUrina.getId());
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterExameUrina.exclui.sucesso");
			return true;	   
		}
		else{
			System.out.println("entrou no else empty");
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterExameUrina.exclui.erro");
			return false;
			}
//		exameUrinaDAO.exclui(exameUrina.getId());
	}
		
	/*GETTERS & SETTERS*/
	
	
	public ExameUrinaDAO getExameUrinaDAO() {
		return exameUrinaDAO;
	}

	public void setExameUrinaDAO(ExameUrinaDAO exameUrinaDAO) {
		this.exameUrinaDAO = exameUrinaDAO;
	}	
}