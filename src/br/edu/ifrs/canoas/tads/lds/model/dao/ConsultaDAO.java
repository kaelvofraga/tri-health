package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;

import br.edu.ifrs.canoas.tads.lds.bean.Consulta;

public class ConsultaDAO extends BaseDAO<Consulta, Long> implements Serializable {
	private static final long serialVersionUID = -7233487765093285536L;
	
	@SuppressWarnings("unchecked")
	public List<Consulta> buscaPorSintoma(String sintoma) {
		return em.createQuery("SELECT c FROM Consulta c WHERE upper(c.sintoma) like '%"
				+ sintoma.toUpperCase() + "%'").getResultList();
	}	
}
