package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;



@Stateless
public class MedicoDAO extends BaseDAO<Medico, Long>{

	private static final long serialVersionUID = -6896321074436211850L;
	
	/*Metodo para buscar do banco os Medicos pelo parametro do nome*/
		@SuppressWarnings("unchecked")
		public List<Medico> buscaPorNome(String nome) {
			return em.createQuery(
					"SELECT m " 
			         + "FROM Medico m "
			         + "WHERE lower(m.nome) = lower(:nome) ")
			         .setParameter("nome", nome)
			         .getResultList();
		}
}
