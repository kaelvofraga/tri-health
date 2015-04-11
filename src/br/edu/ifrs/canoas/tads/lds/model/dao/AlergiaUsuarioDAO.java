package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.edu.ifrs.canoas.tads.lds.bean.AlergiaUsuario;

@Stateless
public class AlergiaUsuarioDAO extends BaseDAO<AlergiaUsuario, Long> {

	private static final long serialVersionUID = -126746156147500109L;

	public List<AlergiaUsuario> buscaPorCriterio(String criterioAlergia) {
		return em
				.createQuery(
						"SELECT au FROM AlergiaUsuario au "
								+ "WHERE "
								+ "lower(au.medicamentoUsuario.medicamento.nome) like '%" + criterioAlergia.toLowerCase() + "%' "
								+ "OR lower(au.reacao) like '%" + criterioAlergia.toLowerCase() + "%' "
								+ "OR lower(au.tipoAlergia.descricao) like '%" + criterioAlergia.toLowerCase() + "%' "
								+ "ORDER BY au.medicamentoUsuario.medicamento.nome")
				.getResultList();
	}

}
