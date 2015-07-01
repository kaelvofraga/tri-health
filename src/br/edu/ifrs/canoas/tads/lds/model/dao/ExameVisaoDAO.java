package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;

import br.edu.ifrs.canoas.tads.lds.bean.Consulta;
import br.edu.ifrs.canoas.tads.lds.bean.ExameVisao;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

public class ExameVisaoDAO extends BaseDAO<ExameVisao, Long> implements Serializable  {
private static final long serialVersionUID = -7233487765093285536L;
	
	/*
	 * Retorna Exames de Visao por grau
	 */
	@SuppressWarnings("unchecked")
	public List<ExameVisao> buscaPorGrau(String grau) {
		//TODO: fazer busca por INNER JOIN Grau
		return em.createQuery("SELECT e FROM ExameVisao c WHERE upper(c.graus) like '%"
				+ grau.toUpperCase() + "%'").getResultList();
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
	public List<ExameVisao> buscaPorCriterio(String criterioExame) {
		try {
			return em
					.createQuery(
							"SELECT e FROM ExameVisao e "
									+ "WHERE "
									+ "lower(e.medico.nome) like '%" + criterioExame.toLowerCase() + "%' "
									+ "ORDER BY e.data DESC")
					.getResultList();
		}
		catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	/** 
	 * @author Delmar
	 * @brief Busca ExameVisao baseado no usuario.  	 		  
	 * @param Usuario usuario: usuario logado.
	 * @return List<ExameVisao>
	 * */
	@SuppressWarnings("unchecked")
	public List<ExameVisao> buscaExameVisaoPorUsuario(Usuario usuario) {
		try {
		return em.createQuery(
		         "SELECT e " 
		         + "FROM ExameVisao e "
		         + "WHERE e.usuario.id = :usuario ")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
		}
		catch (IllegalArgumentException e) {
			return null;
		}
		
	}
}
