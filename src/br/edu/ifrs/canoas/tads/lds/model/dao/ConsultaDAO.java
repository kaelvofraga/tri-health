package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;

import br.edu.ifrs.canoas.tads.lds.bean.Consulta;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;

public class ConsultaDAO extends BaseDAO<Consulta, Long> implements Serializable {
	private static final long serialVersionUID = -7233487765093285536L;
	
	@SuppressWarnings("unchecked")
	public List<Consulta> buscaPorSintoma(String sintoma) {
		return em.createQuery("SELECT c FROM Consulta c WHERE upper(c.sintoma) like '%"
				+ sintoma.toUpperCase() + "%'").getResultList();
	}	
	
	/*
     * retorna a lista de clientes do autoComplete
     */
	public List<Medico> getMedico(String descricao) {
    	return (List<Medico>) em.createQuery("SELECT m FROM Medico m WHERE "
			+ "UPPER(TRANSLATE(m.nome,'ÃÀÁÂãáàâÉÈÊéèêÍíÕÓõóÒòÚú','AAAAaaaaEEEeeeIiOOooOoUu')) "
			+ "LIKE UPPER(TRANSLATE(:descricao,'ÃÀÁÂãáàâÉÈÊéèêÍíÕÓõóÒòÚú','AAAAaaaaEEEeeeIiOOooOoUu')) ORDER BY m.nome")
                .setParameter("descricao", "%" + descricao.toUpperCase() + "%")
                .getResultList();
    }
}
