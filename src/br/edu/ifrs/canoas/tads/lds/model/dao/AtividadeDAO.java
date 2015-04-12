package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Atividade;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

@Stateless
public class AtividadeDAO extends BaseDAO<Atividade, Long> implements Serializable {
	
	private static final long serialVersionUID = 7111756116176127749L;
	
	@SuppressWarnings("unchecked")
	public List<Atividade> buscaPorDescricao(Atividade atividade) {
		return em.createQuery("SELECT a FROM Atividade a WHERE lower(a.descricao) like '%"
				+ atividade.getDescricao().toLowerCase() + "%'").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Atividade> buscaAtividadePorUsuario(Usuario usuario) {	
		return em.createQuery(
				"SELECT a " 
		         + "FROM Atividade a "
		         + "WHERE a.id in "
		         + "(select au.atividade.id "
		         + "	from AtividadeUsuario au "
		         + "	where au.usuario.id = :usuario) ")
		         .setParameter("usuario", usuario.getId())
		         .getResultList();
	}
}	

