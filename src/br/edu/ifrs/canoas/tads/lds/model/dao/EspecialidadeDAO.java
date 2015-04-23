package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Especialidade;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;


@Stateless
public class EspecialidadeDAO extends BaseDAO<Especialidade, Long>{

	private static final long serialVersionUID = -6896321074436211850L;
	
	
	@SuppressWarnings("unchecked")
	public List<Medico> buscaPorNome(String criterio) {
		return em
				.createQuery(
						"SELECT e FROM Especialidade e "
								+ "WHERE "
								+ "lower(e.descricao) like '%:descricao%' "
								)
								.setParameter("nome", criterio.toLowerCase())
								.setParameter("crm", criterio.toLowerCase())
								.getResultList();
	}
	
}
