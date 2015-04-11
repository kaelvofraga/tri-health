package br.edu.ifrs.canoas.tads.lds.model.dao;


import java.util.List;

import javax.ejb.Stateless;

import org.hibernate.criterion.Restrictions;

import br.edu.ifrs.canoas.tads.lds.bean.TipoAlergia;

@Stateless
public class TipoAlergiaDAO extends BaseDAO<TipoAlergia, Long>{

	private static final long serialVersionUID = 9079654728866074192L;

	public List<TipoAlergia> buscaPorDescricao(TipoAlergia tipoAlergia) {
		return super.createCriteria()
				.add(Restrictions.eq("descricao", tipoAlergia.getDescricao()))
				.list();
		}
	
}
