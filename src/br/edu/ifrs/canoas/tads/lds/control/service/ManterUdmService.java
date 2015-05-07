package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.bean.Udm;
import br.edu.ifrs.canoas.tads.lds.model.dao.UdmDAO;

/**
 * @author JuarezMonteiro
 */

@Stateless
public class ManterUdmService {
	@Inject
	private UdmDAO udmDAO;
	
	/* Metodo busca que retorna todos objetos Udm cadastrados.*/
	public List<Udm> buscaUdm() {
		return  udmDAO.buscaTodos();
	}
	

}
