package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Alimento;
import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlimento;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;
import br.edu.ifrs.canoas.tads.lds.model.dao.AlimentoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.AtividadeDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAlimentoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAtividadeDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

/** 
 * Classe de controle que possui o método salvar, alterar, buscar e excluir
 * 
 * @author Luana
 * @version 21/06/2015

 */

@Stateless
public class ManterCategoriaService {

	@Inject 
	private AlimentoDAO alimentoDAO;
	
	@Inject
	private TipoAlimentoDAO tipoAlimentoDAO;
	
	@Inject
	private AtividadeDAO atividadeDAO;
	
	@Inject
	private TipoAtividadeDAO tipoAtividadeDAO;
	
	public Boolean salvaCategoria(Atividade atividade, TipoAtividade tipoAtividade) {
		if(tipoAtividade == null || tipoAtividade.getNome() == null){	
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "Atividade.cadastro.erro");
			return false;	
		}
		if(atividade == null || atividade.getDescricao() == null){	
		
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "Atividade.cadastro.erro");
			return false;					
		}
				
		tipoAtividadeDAO.insere(tipoAtividade);
		atividade.setTipoAtividade(tipoAtividade);
		atividadeDAO.insere(atividade);		
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Atividade.cadastro.sucesso");
		
		return true;	
	}	
		
	/*
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
	
	/*public void alteraPesoUsuario(PesoUsuario pesoUsuario) {
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
	}	*/
}