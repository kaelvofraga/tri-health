package br.edu.ifrs.canoas.tads.lds.control.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.ExameCardiologico;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;
import br.edu.ifrs.canoas.tads.lds.bean.TipoExame;
import br.edu.ifrs.canoas.tads.lds.model.dao.ExameCardiologicoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.MedicoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoExameDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

/**
 * @author: Miromar J. Lima
 * Classe para controle e validaca de dados, 
 * e comunicao com a classe responsavel por persistir dados ao banco.
 * 
 */

@Stateless
public class ExameCardidologicoService {
	
	Date dataAtual = new Date(System.currentTimeMillis());
	
	@Inject
	private ExameCardiologicoDAO exameCardiologicoDao;
	
	@Inject
	private MedicoDAO medicoDao;

	@Inject
	private TipoExameDAO tipoExameDao;

	
	public boolean salvaExameCard(ExameCardiologico exameCardiologico) {
		
		if (this.validaExame(exameCardiologico)) { 			
			if(exameCardiologico.getDataExame().after(dataAtual)){
				Mensagens.define(FacesMessage.SEVERITY_ERROR, "Cateterismo.cadastro.data.erro");
				return false;
			}
			
			exameCardiologicoDao.insere(exameCardiologico);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "Cateterismo.cadastro.sucesso");
			return true;
			
		} 
		
		Mensagens.define(FacesMessage.SEVERITY_ERROR, "Cateterismo.cadastro.erro");
		return false;
		
	}

	private boolean validaExame(ExameCardiologico exameCardiologico) {
		
		if(exameCardiologico.getMedico() == null ||				
				exameCardiologico.getTipoExame() == null				
				//|| exameCardiologico.getArquivoLaudo() == null
				){ 
			return false;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<ExameCardiologico> busca(String criterio) {
		if(criterio != null && criterio != ""){
			return exameCardiologicoDao.buscaPorCriterio(criterio);
		}else{
			return exameCardiologicoDao.buscaTodos();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Medico> buscaMedicos(String criterio) {
		
		return medicoDao.buscaTodos();
	
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoExame> buscaTipoExames(String criterio) {
		
		return tipoExameDao.buscaTodos();
	
	}
	
	public void excluiExame(ExameCardiologico exame){
		exameCardiologicoDao.exclui(exame.getId());
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Cateterismo.exclui.sucesso");

	}
	
	public void alteraExame(ExameCardiologico exame){
		exameCardiologicoDao.atualiza(exame);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Cateterismo.cadastro.sucesso");
	}

}
