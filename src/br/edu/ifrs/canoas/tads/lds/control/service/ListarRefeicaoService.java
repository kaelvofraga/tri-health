package br.edu.ifrs.canoas.tads.lds.control.service;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.TipoAlimento;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAlimentoDAO;


@Stateless
public class ListarRefeicaoService {
	
	@Inject
	private TipoAlimentoDAO tipoAlimentoDAO;
	
	public List<TipoAlimento> buscaTodosTiposAlimentos() {
		return  tipoAlimentoDAO.getTesteJinqStream();	
	}	
}
