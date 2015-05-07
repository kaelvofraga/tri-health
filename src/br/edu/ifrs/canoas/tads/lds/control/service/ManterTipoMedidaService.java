package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.bean.TipoMedida;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoMedidaDAO;

/**
 * 
 * @author Juarez Monteiro
 */

@Stateless
public class ManterTipoMedidaService {

	@Inject
	private TipoMedidaDAO tipoMedidaDAO;
	
	/** 
	 * @return o método buscaTipoMedida irá retornar todos os tipos de medidas cadastrados. 
	 */
	public List<TipoMedida> buscaTipoMedida() {
		return  tipoMedidaDAO.buscaTodos();
	}
	

}
