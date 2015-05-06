package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.hibernate.Session;

import br.edu.ifrs.canoas.tads.lds.bean.Alimento;
import br.edu.ifrs.canoas.tads.lds.bean.Refeicao;
import br.edu.ifrs.canoas.tads.lds.bean.RefeicaoAlimento;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlimento;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.AlimentoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.RefeicaoAlimentoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.RefeicaoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAlimentoDAO;

@Stateless
public class ManterRefeicaoService {

	
	@Inject
	private TipoAlimentoDAO tipoAlimentoDAO;
	
	@Inject
	private AlimentoDAO alimentoDAO;
	
	@Inject
	private RefeicaoDAO refeicaoDAO;
	
	public List<TipoAlimento> buscaTodosTiposAlimentos() {
		return  tipoAlimentoDAO.buscaTodos();	
	}	
	
	public List<Alimento> buscaAlimentos(TipoAlimento tipoAlimento){
		List<Alimento> retorno = alimentoDAO.buscaAlimentosPorTipoAlimento(tipoAlimento);
		return retorno;
	}
	
	public Boolean salvarRefeicoes(Refeicao refeicao, Usuario usuario){
		Boolean retorno = false;
		
		if(refeicao != null && refeicao.getRefeicaoAlimentos().size() > 0 && usuario != null){
			Session session = refeicaoDAO.getSection();
			try {
				session.beginTransaction();	
				
				refeicao.setUsuario(usuario);
				session.saveOrUpdate(refeicao);
				
				for (RefeicaoAlimento refeicaoAlimento : refeicao.getRefeicaoAlimentos()) {
					session.saveOrUpdate("RefeicaoAlimento", refeicaoAlimento);
				}		
				
				session.getTransaction().commit();					
				retorno = true;
				
			} catch (Exception e) {
				session.getTransaction().rollback();
			}	
		}		
		
		return retorno;
	}
}
