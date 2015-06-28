package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Hospital;


/**
 * @author JuarezMonteiro 
 */

@Stateless
public class HospitalDAO extends BaseDAO<Hospital, Long>{

	private static final long serialVersionUID = 8439308016461408036L;
	
	/**
	 * nomeHospital Irá receber um valor o qual irá ser atribuído a uma query pelo método buscaHospitalPorNome;
	 * Esse método irá retornar uma lista com os campos iguais ou aproximados da variável nomeHospital;
	 * */
	@SuppressWarnings("unchecked")
	public List<Hospital> buscaHospitalPorNome(String nomeHospital) {
		try {
			return em
					.createQuery(
							"SELECT hp FROM Hospital hp "
									+ "WHERE "
									+ "lower(hp.nome) like '%" + nomeHospital.toLowerCase() + "%' "
									+ "ORDER BY hp.nome")
					.getResultList();
		}
		catch (IllegalArgumentException e) {
			return null;
		}
	}
	
}
