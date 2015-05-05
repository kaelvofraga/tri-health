package br.edu.ifrs.canoas.tads.lds.model.dao;


import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.bean.ValorMedidaUsuario;

@Stateless
public class ValorMedidaDAO extends BaseDAO< ValorMedidaUsuario, Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6004161395440143951L;

	
	@SuppressWarnings("unchecked")
	public List<ValorMedidaUsuario> buscaPorCriterio(String criterioMedida) {
		try {
			return em
					.createQuery(
							"SELECT vmu FROM ValorMedidaUsuario vmu "
									+ "WHERE "
									+ "lower(vmu.udm.descricao) like '%" + criterioMedida.toLowerCase() + "%' "
									+ "OR lower(vmu.tipoMedida.descricao) like '%" + criterioMedida.toLowerCase() + "%' "
									+ "ORDER BY vmu.tipoMedida.descricao")
					.getResultList();
		}
		catch (IllegalArgumentException e) {
			return null;
		}
	}



	/*
	public List<Medicamento> buscaNomeMedicamentoPorUsuario(Usuario usuario) {
		
		return em.createQuery(
		         "SELECT mu.medicamento " 
		         + "FROM MedicamentoUsuario mu "
		         + "WHERE mu.usuario.id = :usuario ")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
		
	}
*/
	
}
