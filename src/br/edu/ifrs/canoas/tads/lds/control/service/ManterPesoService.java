package br.edu.ifrs.canoas.tads.lds.control.service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Udm;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.bean.ValorMedidaUsuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.PesoUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

/** 
 * Classe de controle que possui o método salvar, alterar, buscar e excluir
 * 
 * @author Luana
 * @version 06/05/2015
 */

@Stateless
public class ManterPesoService {

	@Inject 
	private PesoUsuarioDAO pesoUsuarioDAO;

	public Boolean salvaPesoUsuario(PesoUsuario pesoUsuario) {
	
		if(pesoUsuario == null || pesoUsuario.getUsuario() == null){			
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "Peso.cadastro.erro");
			return false;
		}	
		pesoUsuarioDAO.insere(pesoUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Peso.cadastro.sucesso");
		return true;   
	}	
	
	public void alteraPeso(PesoUsuario peso) {
		pesoUsuarioDAO.atualiza(peso);
	}

	public void excluiPeso(PesoUsuario peso) {
		pesoUsuarioDAO.exclui(peso.getId());
	}
	
	public void buscaPeso(PesoUsuario peso){
		pesoUsuarioDAO.busca(peso.getId());
	}
	
	public List<PesoUsuario> listaPeso() {
		return pesoUsuarioDAO.buscaTodos();
	}
	
	@SuppressWarnings("unchecked")
	public List<PesoUsuario> buscaPeso(String pesquisaPeso) {
			return pesoUsuarioDAO.buscaPorNota(pesquisaPeso);		
	}
	
	
}