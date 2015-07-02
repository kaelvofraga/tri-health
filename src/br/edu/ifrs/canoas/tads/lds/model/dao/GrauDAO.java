package br.edu.ifrs.canoas.tads.lds.model.dao;

import javax.ejb.Stateless;

import br.edu.ifrs.canoas.tads.lds.bean.Grau;
import br.edu.ifrs.canoas.tads.lds.bean.Medico;

@Stateless
public class GrauDAO extends BaseDAO<Grau, Long> {
	private static final long serialVersionUID = -6896321074436211828L;
}
