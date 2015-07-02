package br.edu.ifrs.canoas.tads.lds.model.dao;


import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.ExameCardiologico;
import br.edu.ifrs.canoas.tads.lds.bean.UnidadeFederativa;

/**
* @author Miromar J. Lima
 * Classe responsavel por persistir e buscar dados no banco
 * Data: 25/06/2015 

 */
@Stateless
public class ExameCardiologicoDAO extends BaseDAO<ExameCardiologico, Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3949439339667200160L;
	@SuppressWarnings("unchecked")
	public List<ExameCardiologico> buscaPorCriterio(String criterio){
		return em.createQuery(
		         "SELECT e "
		         + "FROM ExameCardiologico e "
		         + "WHERE lower(e.nota) LIKE like '%" +criterio.trim().toLowerCase()+"%")
		         .getResultList();
	} 

	/*
	@SuppressWarnings("unchecked")
	public List<ExameCardiologico> buscaPorCriterio(Date dataDe, Date dataAte,
			String criterioExameCardiologico) {
		String criterio = "";
		if (criterioExameCardiologico!=null) {
			return em.createQuery(
		         "SELECT c "
		         + "FROM ExameCardiologico c "
		         + "WHERE lower(c.nota) LIKE :nota ")
		         .setParameter("nota", "%"+criterio.trim().toLowerCase()+"%")
		         .getResultList();
	}		
		

		if (dataDe!=null && dataAte!=null) {
			criterio += "ec.dataExame between :dataDe and :dataAte ";
			
			
		
			try {
				return em
					.createQuery(
							"SELECT ec FROM ExameCardiologico ec " + "WHERE "
									+ criterio + "ORDER BY ec.dataExame")
					.setParameter("dataDe", dataDe, TemporalType.TIMESTAMP)
					.setParameter("dataAte", dataAte, TemporalType.TIMESTAMP)
					.getResultList();
			} catch (IllegalArgumentException e) {
			}
		}	
		return null;
		
	}
	*/
}	
