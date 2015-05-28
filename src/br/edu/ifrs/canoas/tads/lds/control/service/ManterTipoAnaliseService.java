package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.bean.TipoAnalise;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoAnaliseDAO;

/** 
 * @author Alisson Lorscheiter
 * Classe de serviço dos tipos de analise de exames. 
 */

@Stateless
public class ManterTipoAnaliseService {
	
	@Inject
	private TipoAnaliseDAO tipoAnaliseDAO;
	
	
	/** 
	 * @return o método buscaTipoAnalise irá retornar todos os tipos de analise cadastrados. 
	 */
	public List<TipoAnalise> buscaTipoAnalise() {
		return  tipoAnaliseDAO.buscaTodos();
	}
	

}
