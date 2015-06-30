package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import br.edu.ifrs.canoas.tads.lds.bean.Exame;
import br.edu.ifrs.canoas.tads.lds.bean.ExameCampos;

@Stateless
public class ExameDAO extends BaseDAO<Exame, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8888411774383824606L;

	public List<Exame> buscaTodosExames() {

		List<Exame> exames = super.buscaTodos();

		for (Exame exame : exames) {

			Criteria examesCampos = this.getSection()
					.createCriteria(ExameCampos.class)
					.add(Restrictions.eq("exame.id", exame.getId()));

			exame.setExamesCampos(examesCampos.list());
		}

		return exames;
	}

}