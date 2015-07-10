package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.CondicaoSaudeUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.StatusSaude;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.CondicaoSaudeUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.StatusSaudeDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

/** Service da US Manter Condicao Saude
* @author Luana Gomes
* @since 06/07/2015
* */
@Stateless
public class ManterCondicaoSaudeService {
	
	@Inject
	private StatusSaudeDAO statusDAO;

	@Inject
	private CondicaoSaudeUsuarioDAO condicaoSaudeUsuarioDAO;
	
	public List<StatusSaude> buscaStatus() {

		return statusDAO.buscaTodos();
	}

	public boolean salvaCondicaoUsuario(CondicaoSaudeUsuario condicaoSaude) {
		if(condicaoSaude == null  || condicaoSaude.getUsuario() == null){
			return false;
		}	
		if (validaDatas(condicaoSaude) == false) {
			return false;
		}
		condicaoSaudeUsuarioDAO.insere(condicaoSaude);
		Mensagens.define(FacesMessage.SEVERITY_INFO,
				"manterCondicaoSaude.salvar.sucesso");
		return true;
	}
	/**
	 * @return false se ocorrer erro na validação de data, ou TRUE caso data válida*/
	/* Metodo para validar as datas da view manterUsoMedicamentos */
	private boolean validaDatas(CondicaoSaudeUsuario condicaoSaudeUsuario) {
		long timeDifMilli = 0L;
		long timeDifMinutes = 0L;
		long timeSysDate = new Date().getTime();
		
		long timeDataIni = condicaoSaudeUsuario.getDataInicio().getTime();
		long timeDataFim = condicaoSaudeUsuario.getDataFim().getTime();
		timeDifMilli = timeDataFim - timeDataIni;
		timeDifMinutes = TimeUnit.MILLISECONDS.toMinutes(timeDifMilli);

		if (timeDataIni > timeSysDate) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterCondicaoSaude.data.erro");
			return false;
		}		
		// verifica diferença entre as datas de inicio e termino do tratamento
		if (timeDifMinutes < 0L || timeDataFim == timeDataIni) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterCondicaoSaude.data.invalida");
			return false;
		}

		return true;
	}

	/** 
	 * @brief Metodo que realiza busca no banco de dados as condições de saúde 
	 * do usuário através de critério informado.
	 * @param criterioDescricao(String)
	 * @return List<CondicaoSaudeUsuario>
	 * */
	public List<CondicaoSaudeUsuario> busca(String criterioDescricao, Usuario usuario) {
		
		List<CondicaoSaudeUsuario> condicaoList = null;
		
		if(usuario == null)
			return null;					
		
			if (StrUtil.isNotBlank(criterioDescricao) && criterioDescricao != null) {
				condicaoList = condicaoSaudeUsuarioDAO.buscaPorCriterio(criterioDescricao, usuario);
			}else {
				condicaoList = condicaoSaudeUsuarioDAO.buscaTodos();
			}
			return condicaoList;			
	}

}
