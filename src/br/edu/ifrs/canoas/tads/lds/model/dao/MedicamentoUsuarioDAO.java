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
							"SELECT mu FROM MedicamentoUsuario mu "
									+ "WHERE "
									+ "OR lower(mu.medicamento.nome) like '%" + criterioMedicamento.toLowerCase() + "%' "
									+ "OR lower(mu.dosagem) like '%" + criterioMedicamento.toLowerCase() + "%' "
									+ "OR lower(mu.frequencia) like '%" + criterioMedicamento.toLowerCase() + "%' "
									+ "ORDER BY mu.medicamento.nome")
					.getResultList();
			//+ "lower(mu.dataConsulta) like '%" + criterioMedicamento.toLowerCase() + "%' "
		}
		catch (IllegalArgumentException e) {
			return null;
		}
	}

	
}
