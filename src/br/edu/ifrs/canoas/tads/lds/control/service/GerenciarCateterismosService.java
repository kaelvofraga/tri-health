package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Cateterismo;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;
import br.edu.ifrs.canoas.tads.lds.model.dao.CateterismosDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.MedicoDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

/**
 * Classe para controle e validação de dados, 
 * juntamente com a comunicação com a classe responsavel por persistir dados ao banco.
 * 
 * @author Luciano Acosta

 */

@Stateless
public class GerenciarCateterismosService {

	@Inject
	private CateterismosDAO cateterismosDao;
	
	@Inject
	private MedicoDAO medicosDao;

	public boolean salvaCateterismo(Cateterismo cateterismo) {
		
		if (this.validaExame(cateterismo)) { 			
			if(cateterismo.getDataInternacao().after(cateterismo.getDataAlta())){
				Mensagens.define(FacesMessage.SEVERITY_ERROR, "Cateterismo.cadastro.data.erro");
				return false;
			}
			
			cateterismosDao.insere(cateterismo);
			Mensagens.define(FacesMessage.SEVERITY_INFO, "Cateterismo.cadastro.sucesso");
			return true;
			
		} 
		
		Mensagens.define(FacesMessage.SEVERITY_ERROR, "Cateterismo.cadastro.erro");
		return false;
	}

	private boolean validaExame(Cateterismo cateterismo) {
		
		if(cateterismo.getMedicoSolicitante() == null ||
				cateterismo.getMedicoSolicitante() == null ||
				cateterismo.getLaudo() == null){ 
			return false;
		}
		
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<Cateterismo> busca(String criterio) {
		if(criterio != null && criterio != ""){
			return cateterismosDao.buscaPorCriterio(criterio);
		}else{
			return cateterismosDao.buscaTodos();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Medico> buscaMedicos(String criterio) {
		
		return medicosDao.buscaTodos();
	
	}
	
	public void excluiExame(Cateterismo exame){
		cateterismosDao.exclui(exame.getId());
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Cateterismo.exclui.sucesso");

	}
	
	public void alteraExame(Cateterismo exame){
		cateterismosDao.atualiza(exame);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "Cateterismo.cadastro.sucesso");
	}
	
}
