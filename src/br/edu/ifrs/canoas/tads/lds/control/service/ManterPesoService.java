package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.PesoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.PesoUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

/** 
 * Classe de controle que possui o método salvar, alterar, buscar e excluir
 * 
 * @author Luana
 * @version 06/05/2015
 * @author Alisson Lorscheiter
 * @version 18/06/2015
 * Alteração no metodo salvaPesoUsuario para salvar no banco e adição de mensagens.
 *
 */

@Stateless
public class ManterPesoService {

	@Inject 
	private PesoUsuarioDAO pesoUsuarioDAO;
	
	@Inject
	private PesoDAO pesoDAO;
	
	

	public Boolean salvaPesoUsuario(PesoUsuario pesoUsuario) {
		if(pesoUsuario == null || pesoUsuario.getUsuario() == null){			
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "manterPeso.cadastro.erro");
			return false;
		}
		if (validaData(pesoUsuario) == false) {
			return false;
		}
		if(pesoUsuario.getUdm().getId()==3 || pesoUsuario.getUdm().getId()==4){
		pesoDAO.insere(pesoUsuario.getPeso());
		pesoUsuarioDAO.insere(pesoUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterPeso.cadastro.sucesso");
		return true;
		}
		else{
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "manterPeso.udm.erro");
			return false;
		}
	}	
	
	public boolean validaData(PesoUsuario pesoUsuario){
		long timeSysDate = new Date().getTime();
		long timeData = pesoUsuario.getData().getTime();
		if (timeData > timeSysDate) {
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterPeso.cadastro.data.erro");
			return false;
		}
		
		
		return true;
	}
	
	public void alteraPesoUsuario(PesoUsuario pesoUsuario) {
		try {
			if(pesoUsuario.getUdm().getId()==3 || pesoUsuario.getUdm().getId()==4){
			pesoDAO.atualiza(pesoUsuario.getPeso());
			pesoUsuarioDAO.atualiza(pesoUsuario);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterPeso.altera.sucesso");
			}
			else{
				Mensagens.define(FacesMessage.SEVERITY_INFO,
						"manterPeso.udm.erro");	
			}
		} catch (IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterPeso.altera.excecao.erro");
		}
	}

	public boolean excluiPesoUsuario(PesoUsuario pesoUsuario) {
		try{
		if (pesoUsuario.getId() != null && pesoUsuario != null) {	
				pesoUsuarioDAO.exclui(pesoUsuario.getId());
				Mensagens.define(FacesMessage.SEVERITY_INFO,"manterPeso.exclui.sucesso");
				return true;
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,"manterPeso.exclui.erro");
			return false;
		}
		}
		catch (IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterPeso.exclui.excecao.erro");
			return false;
		}
	}
	
	
	public List<PesoUsuario> busca(String criterioPeso) {
		try{
		if (StrUtil.isNotBlank(criterioPeso)&& criterioPeso != null) {
			if (!pesoUsuarioDAO.buscaPorCriterio(criterioPeso).isEmpty()) {
				return pesoUsuarioDAO.buscaPorCriterio(criterioPeso);
			} 
			else {
				Mensagens.define(FacesMessage.SEVERITY_INFO,"listarPeso.busca.vazio");
				return new ArrayList<PesoUsuario>();
			}
		} else
			return pesoUsuarioDAO.buscaTodos();
		}
		catch(EJBException e){
			Mensagens.define(FacesMessage.SEVERITY_INFO,"listarPeso.busca.excecao");
			return null;
		}
		catch(NullPointerException e){
			Mensagens.define(FacesMessage.SEVERITY_INFO,"listarPeso.busca.virgula");
			return null;
		}
	}	
}