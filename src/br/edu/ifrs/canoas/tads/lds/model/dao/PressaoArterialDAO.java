package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import br.edu.ifrs.canoas.tads.lds.bean.PressaoArterial;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

/*
 * Classe responsável pela comunicação de objetos do tipo "pressaoArterial" com a base de dados
 *
 * @author Pablo Diehl da Silva
 * @version 06/05/2015
 *
 */
@Stateless
public class PressaoArterialDAO extends BaseDAO<PressaoArterial, Long>
		implements Serializable {
	private static final long serialVersionUID = -8779592587312664221L;

	/*
	 * Este método busca por registros de 'PressaoArterial' salvos na base de
	 * dados que estejam relacionados a um determinado Usuário.
	 * 
	 * @param usuario Objeto do tipo Usuario do qual se deseja buscar registros
	 * de pressão arterial
	 */
	@SuppressWarnings("unchecked")
	public List<PressaoArterial> buscaPressaoPorUsuario(Usuario usuario) {
		return em
				.createQuery(
						"SELECT pa" + "FROM PressaoArterial pa"
								+ "WHERE pa.id in"
								+ "(select pu.pressaoarterial.id "
								+ "	from PressaoUsuario pu "
								+ "	where pu.usuario.id = :usuario) ")
				.setParameter("usuario", usuario.getId()).getResultList();
	}

	/*
	 * Este método busca por registros de 'PressaoArterial' salvos na base de
	 * dados que estejam relacionados a uma determinada data.
	 * 
	 * @param data Campo do tipo data pela qual serão pesquisados registros de
	 * pressão arterial.
	 */
	@SuppressWarnings("unchecked")
	public List<PressaoArterial> buscaPorData(Date data) {
		return em
				.createQuery(
						"SELECT pa" + "FROM PressaoArterial pa"
								+ "WHERE pa.data = :data"
								+ "ORDER BY pa.data")
				.setParameter("data", data).getResultList();
	}
}