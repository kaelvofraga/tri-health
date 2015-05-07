package br.edu.ifrs.canoas.tads.lds.model.dao;


import java.util.List;
import javax.ejb.Stateless;
import br.edu.ifrs.canoas.tads.lds.bean.ValorMedidaUsuario;
/**
 * @author JuarezMonteiro
 */

@Stateless
public class ValorMedidaDAO extends BaseDAO< ValorMedidaUsuario, Long>{


	private static final long serialVersionUID = -6004161395440143951L;

	/**
	 * CriterioMedida Ir� receber um valor o qual ir� ser atribu�do a uma query pelo m�todo buscaPorCriterio;
	 * Esse m�todo ir� retornar uma lista com os campos iguais ou aproximados da vari�vel criterioMedida;
	 * */
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
}
