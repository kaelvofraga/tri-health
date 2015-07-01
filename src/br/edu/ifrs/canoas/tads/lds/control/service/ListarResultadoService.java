package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.bean.Resultado;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.ResultadoDAO;


@Stateless
public class ListarResultadoService {
	
	@Inject
	private ResultadoDAO resultadoDao;
	
	public List<Resultado> buscarTodosResultados(Usuario usuario){
		
		return resultadoDao.buscaTodosResultados(usuario);
	}
}
