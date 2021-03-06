package br.edu.ifrs.canoas.tads.lds.control.service;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Alimento;
import br.edu.ifrs.canoas.tads.lds.bean.Refeicao;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAlimento;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.AlimentoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.RefeicaoDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAlimentoDAO;


@Stateless
public class ListarRefeicaoService {
	
	@Inject
	private TipoAlimentoDAO tipoAlimentoDAO;
	
	@Inject
	private AlimentoDAO alimentoDAO;
	
	@Inject
	private RefeicaoDAO refeicaoDAO;
	
	public List<TipoAlimento> buscaTodosTiposAlimentos() {
		return  tipoAlimentoDAO.buscaTodos();	
	}	
	
	public List<Alimento> buscaAlimentos(TipoAlimento tipoAlimento){
		List<Alimento> retorno = alimentoDAO.buscaAlimentosPorTipoAlimento(tipoAlimento);
		return retorno;
	}
	
	public List<Refeicao> buscaRefeicoes(Date dataDe, Date dataAte, Alimento alimento, Usuario usuario){
		List<Refeicao> retorno = refeicaoDAO.buscaRefeicoes(dataDe, dataAte, alimento, usuario);
		return retorno;
	}
}
