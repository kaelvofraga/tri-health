package br.edu.ifrs.canoas.tads.lds.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.edu.ifrs.canoas.tads.lds.bean.ExameCampos;
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
				.addOrder(Order.desc("resultadoData"))
				.list();
			

		for (Resultado resultado : resultados) {

			Criteria resultadoCamposCriteria = this.getSection()
					.createCriteria(ResultadoCampos.class)
					.add(Restrictions.eq("resultado.id", resultado.getId()));

			resultado.setResultadoCamposList(resultadoCamposCriteria.list());
			
			Criteria examesCamposCriteria = this.getSection()
					.createCriteria(ExameCampos.class)
					.add(Restrictions.eq("exame.id",resultado.getExame().getId()));
			
			List<ExameCampos> listaDeCamposNoExame = examesCamposCriteria.list();
			
			if(resultado.getResultadoCamposList().size() < listaDeCamposNoExame.size()){
				
				for (ExameCampos exameCampos : listaDeCamposNoExame) {					
					boolean criarNovo = true;
					
					for (ResultadoCampos resultadoCampos : resultado.getResultadoCamposList()) {
						if(resultadoCampos.getExameCampos().getId() == exameCampos.getId()){
							criarNovo = false;
							break;
						}
					}
					
					if(criarNovo){
						ResultadoCampos novoCampo = new ResultadoCampos();
						novoCampo.setResultado(resultado);
						novoCampo.setExameCampos(exameCampos);
						novoCampo.setResultadoValor("");
						resultado.getResultadoCamposList().add(novoCampo);
					}
				}
			}
		}

		return resultados;
	}
}
