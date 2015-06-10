package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.PesoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.PesoUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

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
	
	@Inject
	private PesoDAO pesoDAO;

	public Boolean salvaPesoUsuario(PesoUsuario pesoUsuario) {
	
		if(pesoUsuario == null || pesoUsuario.getUsuario() == null){			
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "Peso.cadastro.erro");
			return false;
		}
		pesoDAO.insere(pesoUsuario.getPeso());
		pesoUsuarioDAO.insere(pesoUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Peso.cadastro.sucesso");
		return true;   
	}	
	
	public void alteraPeso(PesoUsuario pesoUsuario) {
		pesoUsuarioDAO.atualiza(pesoUsuario);
	}

	public void excluiPeso(PesoUsuario pesoUsuario) {
		pesoUsuarioDAO.exclui(pesoUsuario.getId());
	}
	
	public void buscaPeso(PesoUsuario pesoUsuario){
		pesoUsuarioDAO.busca(pesoUsuario.getId());
	}
	
	public List<PesoUsuario> listaPeso() {
		return pesoUsuarioDAO.buscaTodos();
	}
	
	
}