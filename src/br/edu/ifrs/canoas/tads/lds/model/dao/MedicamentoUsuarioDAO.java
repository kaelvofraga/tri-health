package br.edu.ifrs.canoas.tads.lds.model.dao;

/**
 * DAO implementation class: MedicamentoUsuarioDAO
 * @author Alisson Lorscheiter
 *
 */
import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Medicamento;
import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

@Stateless
public class MedicamentoUsuarioDAO extends BaseDAO< MedicamentoUsuario, Long>{

	private static final long serialVersionUID = -5538941133814935745L;

	/*Metodo para buscar do banco os medicamentos do MedicamentoUsuario passando como parametro o usuario */
	@SuppressWarnings("unchecked")
	public List<Medicamento> buscaNomeMedicamentoPorUsuario(Usuario usuario) {
		try {
		return em.createQuery(
		         "SELECT mu.medicamento " 
		         + "FROM MedicamentoUsuario mu "
		         + "WHERE mu.usuario.id = :usuario ")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
		}
		catch (IllegalArgumentException e) {
			return null;
		}
		
	}
	
	/*Metodo para buscar no banco por criterio informado por parametro */
	@SuppressWarnings("unchecked")
	public List<MedicamentoUsuario> buscaPorCriterio(String criterioMedicamento) {
		try {
			return em
					.createQuery(
							"SELECT mu FROM MedicamentoUsuario mu "
									+ "WHERE "
									+ "lower(mu.medicamento.nome) like '%" + criterioMedicamento.toLowerCase() + "%' "
									+ "OR lower(mu.dosagem) like '%" + criterioMedicamento.toLowerCase() + "%' "
									+ "OR lower(mu.frequencia) like '%" + criterioMedicamento.toLowerCase() + "%' "
									+ "ORDER BY mu.medicamento.nome")
					.getResultList();
		}
		catch (IllegalArgumentException e) {
			return null;
		}
	}
	/*Metodo para buscar id do MedicamentoUsuario que se encontra na AlergiaUsuario passa como parametro
	 * o medicamentoUsuario*/
	@SuppressWarnings("unchecked")
	public List<Integer> buscaIdMedicamentoAlergiaUsuario(MedicamentoUsuario medicamentoUsuario) {
		try {
		return em.createQuery(
		         "SELECT au.medicamentoUsuario.id " 
		         + "FROM AlergiaUsuario au, MedicamentoUsuario mu "
		         + "WHERE mu.id = :medicamento and "
		         + "au.medicamentoUsuario.id=mu.id")
		         .setParameter("medicamento",medicamentoUsuario.getId()).getResultList();
		}catch (IllegalArgumentException e) {
			return null;
		}
		         
	}
	
	
	
}
