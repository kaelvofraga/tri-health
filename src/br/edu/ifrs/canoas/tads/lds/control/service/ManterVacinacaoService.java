package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.bean.Vacinacao;
import br.edu.ifrs.canoas.tads.lds.model.dao.VacinacaoDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterVacinacaoService {

	@Inject
	VacinacaoDAO vacinacaoDAO;	
	
	public List<Vacinacao> buscaGeral(String criterioVacinacaoUsuario,
			Usuario usuario) {
		
		List<Vacinacao> retornoList = new ArrayList<Vacinacao>();
		
		if (usuario == null)
			return null;

		if (StrUtil.isNotBlank(criterioVacinacaoUsuario) && criterioVacinacaoUsuario != null) {
			retornoList = vacinacaoDAO.buscaPorCriterio(criterioVacinacaoUsuario,
					usuario);
		} else {
			retornoList = vacinacaoDAO.buscaVacinacoesDoUsuario(usuario);			
		}		
		
		return retornoList;
	}

	public boolean salvaVacinacao(Vacinacao vacinacao) {
		if(vacinacao == null || vacinacao.getUsuario() == null){			
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterVacinacao.salvar.erro");
			return false;
		}
		if (validaData(vacinacao) == false) {
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterPeso.cadastro.data.erro");
			return false;
		}
		
		vacinacaoDAO.insere(vacinacao);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterCondicaoSaude.salvar.sucesso");			
		return true;
	}	
	
	public boolean validaData(Vacinacao vacinacao){
		long timeSysDate = new Date().getTime();
		long timeData = vacinacao.getDataVacinacao().getTime();
		if (timeData > timeSysDate) {
			return false;
		}
		return true;
	}
	
	public boolean alteraVacinacao(Vacinacao vacinacao) {
		try {
			if(validaData(vacinacao)==false){
				Mensagens.define(FacesMessage.SEVERITY_INFO,
						"manterPeso.cadastro.data.erro");
				return false;
			}
			
			vacinacaoDAO.atualiza(vacinacao);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
				"manterComposicao.cadastro.editado");
			return true;
			
		} catch (IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterExameVisao.altera.excecao.erro");
			return false;
		}
	}

	public boolean excluiVacinacao(Vacinacao vacinacao) {
		try{
			if (vacinacao.getId() != null && vacinacao != null) {	
					vacinacaoDAO.exclui(vacinacao.getId());
					Mensagens.define(FacesMessage.SEVERITY_INFO,"manterExameVisao.exclui.sucesso");
					return true;
			} else {
				Mensagens.define(FacesMessage.SEVERITY_INFO,"manterExameVisao.exclui.nulo.erro");
				return false;
			}
		}
		catch (IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterExameVisao.exclui.nulo.erro");
			return false;
		}
	}
}