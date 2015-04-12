package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.TipoAtividade;

@Stateless
public class TipoAtividadeDAO extends BaseDAO<TipoAtividade, Long> implements
Serializable{

	private static final long serialVersionUID = 1289654162766030500L;
	
	@SuppressWarnings("unchecked")
	public List<TipoAtividade> buscaPorNome(TipoAtividade tipoAtividade) {
		return em.createQuery("SELECT ta FROM TipoAtividade ta WHERE lower(ta.nome) = lower(:nome) ")
		         .setParameter("nome", tipoAtividade.getNome())
		         .getResultList();
	}
	
	
}
