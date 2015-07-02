package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.hibernate.Session;

import br.edu.ifrs.canoas.tads.lds.bean.Exame;
import br.edu.ifrs.canoas.tads.lds.bean.Resultado;
import br.edu.ifrs.canoas.tads.lds.bean.ResultadoCampos;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.ExameDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.ResultadoCamposDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.ResultadoDAO;

@Stateless
public class ListarResultadoService {

	@Inject
	private ResultadoDAO resultadoDao;

	@Inject
	private ExameDAO exameDao;

	@Inject
	private ResultadoCamposDAO resultadoCamposDAO;

	public List<Exame> buscarTodosExamesComCampos() {
		return exameDao.buscaTodosExames();
	}

	public List<Resultado> buscarTodosResultados(Usuario usuario) {

		return resultadoDao.buscaTodosResultados(usuario);
	}

	public String salvarResultado(Resultado resultado) {
		String retorno = "";

		if (resultado != null && resultado.getResultadoCamposList().size() > 0) {
			Session session = resultadoDao.getSection();

			try {
				session.beginTransaction();

				session.saveOrUpdate(resultado);

				for (ResultadoCampos resultadoCampo : resultado
						.getResultadoCamposList()) {
					session.saveOrUpdate("ResultadoCampos", resultadoCampo);
				}

				session.getTransaction().commit();

			} catch (Exception e) {
				session.getTransaction().rollback();
				retorno = e.getMessage() + e.getStackTrace().toString();
			}
		} else {
			retorno = "O exame não pode ser nulo, e deve conter campos.";
		}

		return retorno;
	}

	public String excluirResultado(Resultado resultado) {
		String retorno = "";

		if (resultado != null) {

			Session session = resultadoDao.getSection();

			try {
				
				session.beginTransaction();

				for (ResultadoCampos resultadoCampos : resultado
						.getResultadoCamposList()) {
					resultadoCamposDAO.exclui(resultadoCampos.getId());
				}
				
				resultadoDao.exclui(resultado.getId());

				session.getTransaction().commit();
			} catch (Exception e) {
				session.getTransaction().rollback();
				retorno = e.getMessage() + e.getStackTrace().toString();
			}
		} else {
			retorno = "O resultado para exclusão não pode ser nulo.";
		}

		return retorno;
	}
}
