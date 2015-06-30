package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.hibernate.Session;

import br.edu.ifrs.canoas.tads.lds.bean.Exame;
import br.edu.ifrs.canoas.tads.lds.bean.ExameCampos;
import br.edu.ifrs.canoas.tads.lds.bean.Refeicao;
import br.edu.ifrs.canoas.tads.lds.bean.RefeicaoAlimento;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.ExameDAO;

@Stateless
public class ManterExamesService {

	@Inject
	private ExameDAO exameDao;
	
	public List<Exame> buscaExames(){
		return exameDao.buscaTodosExames();
	}
	
	public String salvarExames(Exame exame){
		String retorno = "";
		
		if(exame != null && exame.getExamesCampos().size() > 0){
			Session session = exameDao.getSection();
			try {
				session.beginTransaction();	
				
				session.saveOrUpdate(exame);
				
				for (ExameCampos exameCampo : exame.getExamesCampos()) {
					session.saveOrUpdate("ExameCampos", exameCampo);
				}		
				
				for (ExameCampos exameCampo : exame.getexamesCamposRemovidos()) {
					Object exameCampoDelete = session.merge(exameCampo);
					session.delete("ExameCampos", exameCampoDelete);
				}
				
				session.getTransaction().commit();	
				
			} catch (Exception e) {
				session.getTransaction().rollback();
				retorno = e.getMessage() + e.getStackTrace().toString();
			}	
		}
		else{
			retorno = "O exame não pode ser nulo, e deve conter campos.";
		}
		
		return retorno;
	}
}
