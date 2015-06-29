package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;
import br.edu.ifrs.canoas.tads.lds.bean.Consulta;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

public class ConsultaDAO extends BaseDAO<Consulta, Long> implements Serializable {
	private static final long serialVersionUID = -7233487765093285536L;
	
	@SuppressWarnings("unchecked")
	public List<Consulta> buscaPorSintoma(String sintoma) {
		return em.createQuery("SELECT c FROM Consulta c WHERE upper(c.sintoma) like '%"
				+ sintoma.toUpperCase() + "%'").getResultList();
	}
	
	/*Metodo para buscar do banco os medicos */
	@SuppressWarnings("unchecked")
	public List<Medico> buscaNomeMedico() {
		
		return em.createQuery(
				"SELECT m " 
		         + "FROM Medico m")
		         .getResultList();
	}
	
	/*Metodo para buscar no banco por criterio informado por parametro */
	@SuppressWarnings("unchecked")
	public List<Consulta> buscaPorCriterio(String criterioConsulta) {
		try {
			return em
					.createQuery(
							"SELECT c FROM Consulta c "
									+ "WHERE "
									+ "lower(c.sintomas) like '%" + criterioConsulta.toLowerCase() + "%' "
									+ "OR lower(c.diagnostico) like '%" + criterioConsulta.toLowerCase() + "%' "
									+ "OR lower(c.medico.nome) like '%" + criterioConsulta.toLowerCase() + "%' "
									+ "ORDER BY c.data DESC")
					.getResultList();
		}
		catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	/** 
	 * @author Alisson Lorscheiter
	 * @brief Busca consulta baseado no usuario.  	 		  
	 * @param Usuario usuario: usuario logado.
	 * @return List<Consulta>: lista de pesos ou null se um erro ocorrer
	 * */
	@SuppressWarnings("unchecked")
	public List<Consulta> buscaConsultaPorUsuario(Usuario usuario) {
		try {
		return em.createQuery(
		         "SELECT c " 
		         + "FROM Consulta c "
		         + "WHERE c.usuario.id = :usuario ")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
		}
		catch (IllegalArgumentException e) {
			return null;
		}
		
	}
}
