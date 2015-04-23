package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Medico;



@Stateless
public class MedicoDAO extends BaseDAO<Medico, Long>{

	private static final long serialVersionUID = -6896321074436211850L;
	
	
	@SuppressWarnings("unchecked")
	public List<Medico> buscaPorNome(String criterio) {
		return em
				.createQuery(
						"SELECT med FROM Medico med "
								+ "WHERE "
								+ "lower(med.nome) like '%:nome%' "
								+ "lower(med.crm) like '%:crm%'"
								)
								.setParameter("nome", criterio.toLowerCase())
								.setParameter("crm", criterio.toLowerCase())
								.getResultList();
	}
	
}
