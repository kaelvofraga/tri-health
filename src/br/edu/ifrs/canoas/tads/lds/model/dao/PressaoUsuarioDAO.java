package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;
import javax.ejb.Stateless;
import br.edu.ifrs.canoas.tads.lds.bean.PressaoArterial;
import br.edu.ifrs.canoas.tads.lds.bean.PressaoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

/*
 * Classe responsável pela comunicação de objetos do tipo "pressaoUsuario" com a base de dados
 *
 * @author Pablo Diehl da Silva
 * @version 06/05/2015
 *
 */
@Stateless
public class PressaoUsuarioDAO extends BaseDAO<PressaoUsuario, Long> {
	private static final long serialVersionUID = -3230082070333146918L;

	/*
	 * Este método busca por registros de 'PressaoUsuario' salvos na base de
	 * dados que estejam relacionados a um determinado Usuário.
	 * 
	 * @param usuario Objeto do tipo Usuario do qual deseja buscar registros de
	 * objetos "PressaoUsuario"
	 */
	@SuppressWarnings("unchecked")
	public List<PressaoUsuario> buscaPressaoPorUsuario(Usuario usuario) {
		return em
				.createQuery(
						"SELECT pu.pressaoArterial "
								+ "FROM PressaoUsuario pu "
								+ "WHERE pu.usuario.id = :usuario ")
				.setParameter("usuario", usuario.getId()).getResultList();
	}
}