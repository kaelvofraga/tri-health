package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.bean.Pais;
import br.edu.ifrs.canoas.tads.lds.model.dao.PaisDAO;


/**
 * 
 * @author: Miromar J. Lima
 * Proposito da Classe: Intermediar a camada de interface com o usuário e o banco de dados
 *         
 */
@Stateless
public class ManterPaisesService {
	@Inject
	private PaisDAO paisDAO;
	
	public List<Pais> buscaPaises() {
		return  paisDAO.buscaTodos();
	}
	

}
