package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.edu.ifrs.canoas.tads.lds.bean.Resultado;
import br.edu.ifrs.canoas.tads.lds.bean.ResultadoCampos;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

public class ResultadoDAO extends BaseDAO<Resultado, Long> {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3529459899624110442L;

	public List<Resultado> buscaTodosResultados(Usuario usuario) {
		if(usuario == null)
			return new ArrayList<Resultado>();
		
		List<Resultado> resultados = this.getSection().createCriteria(Resultado.class)
				.add(Restrictions.eq("usuario.id", usuario.getId()))
				.list();
			

		for (Resultado resultado : resultados) {

			Criteria resultadoCampos = this.getSection()
					.createCriteria(ResultadoCampos.class)
					.add(Restrictions.eq("resultado.id", resultado.getId()));

			resultado.setResultadoCamposList(resultadoCampos.list());
		}

		return resultados;
	}
}
