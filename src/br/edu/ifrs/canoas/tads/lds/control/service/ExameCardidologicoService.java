package br.edu.ifrs.canoas.tads.lds.control.service;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
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
	
	private Date dataAtual = new Date(System.currentTimeMillis());
	
	@Inject
	private ExameCardiologicoDAO exameCardiologicoDao;
	
	@Inject
	private MedicoDAO medicoDao;

	@Inject
	private TipoExameDAO tipoExameDao;

	
	public boolean salvaExameCard(ExameCardiologico exameCardiologico) {
		
		if (this.validaExame(exameCardiologico)) { 			
			if(exameCardiologico.getDataExame().after(dataAtual)){
				Mensagens.define(FacesMessage.SEVERITY_ERROR, "manterPeso.cadastro.data.erro");
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


		
		/**
		 * @brief Metodo que realiza busca no banco de dados os exames cardiologicos  
		 * de critério informado. Retorna lista de Exame Cardiologicos.
		 * @param criterioExameCardiologico(String),dataDe(Date),dataAte(Date)
		 * @return List<ExameCardiologico>
		 * 
	
		public List<ExameCardiologico> buscaExameCardiCriterio(Date dataDe, Date dataAte, String criterioExameCardiologico) {
				if (StrUtil.isNotBlank(criterioExameCardiologico) || dataDe != null && dataAte != null) {
					if(!exameCardiologicoDao.buscaPorCriterio(dataDe, dataAte,criterioExameCardiologico).isEmpty()){
						return exameCardiologicoDao.buscaPorCriterio(dataDe, dataAte,criterioExameCardiologico);
					}
					else {
						Mensagens.define(FacesMessage.SEVERITY_INFO,"listarExameUrina.busca.vazio");
						return new ArrayList<ExameCardiologico>();
					}
				}else{
				return exameCardiologicoDao.buscaTodos();
				}
	}
		* */
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

	public FileInputStream abreArquivo(ExameCardiologico exame)   {
	  	FileInputStream fileInputStream=null;	
		File file = new File(exame.getArquivoLaudo().toString());	    
        byte[] bFile = new byte[(int) file.length()];
        try {
	            //convert file into array of bytes
		    fileInputStream = new FileInputStream(file);
		    fileInputStream.read(bFile);
		    fileInputStream.close();
	 
	    return fileInputStream; 
	    
        }catch(Exception e){
            e.printStackTrace();
        }
		return null;
    }

 

}
