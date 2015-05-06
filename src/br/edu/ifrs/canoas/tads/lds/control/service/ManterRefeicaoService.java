package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Alimento;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlimento;
import br.edu.ifrs.canoas.tads.lds.model.dao.AlimentoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.RefeicaoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAlimentoDAO;

@Stateless
public class ManterRefeicaoService {

	
	@Inject
	private TipoAlimentoDAO tipoAlimentoDAO;
	
	@Inject
	private AlimentoDAO alimentoDAO;
	
	public List<TipoAlimento> buscaTodosTiposAlimentos() {
		return  tipoAlimentoDAO.buscaTodos();	
	}	
	
	public List<Alimento> buscaAlimentos(TipoAlimento tipoAlimento){
		List<Alimento> retorno = alimentoDAO.buscaAlimentosPorTipoAlimento(tipoAlimento);
		return retorno;
	}
	
}
