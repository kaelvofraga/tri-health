package br.edu.ifrs.canoas.tads.lds.model.dao;


import java.util.List;

import javax.ejb.Stateless;
import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

@Stateless
public class MedicamentoUsuarioDAO extends BaseDAO< MedicamentoUsuario, Long>{

	private static final long serialVersionUID = -5538941133814935745L;


	@SuppressWarnings("unchecked")
	public List<Medicamento> buscaNomeMedicamentoPorUsuario(Usuario usuario) {
		
		return em.createQuery(
		         "SELECT mu.medicamento " 
		         + "FROM MedicamentoUsuario mu "
		         + "WHERE mu.usuario.id = :usuario ")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
		
	}
	
		
	@SuppressWarnings("unchecked")
	public List<MedicamentoUsuario> buscaPorCriterio(String criterioMedicamento) {
		try {
			return em
					.createQuery(
							"SELECT mu "
									+ "FROM MedicamentoUsuario mu "
									+ "WHERE lower(mu.dataConsulta) like '%" + criterioMedicamento.trim().toLowerCase() + "%' "
									+ "OR lower(mu.medicamento.nome) like '%" + criterioMedicamento.trim().toLowerCase() + "%' "
									+ "OR lower(mu.dosagem) like '%" + criterioMedicamento.trim().toLowerCase() + "%' "
									+ "OR lower(mu.frequencia) like '%" + criterioMedicamento.toLowerCase() + "%' "
									+ "ORDER BY au.medicamento.nome").getResultList();

		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	
}
