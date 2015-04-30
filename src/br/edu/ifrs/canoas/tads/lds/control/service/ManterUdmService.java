package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Pais;
import br.edu.ifrs.canoas.tads.lds.bean.TipoMedida;
import br.edu.ifrs.canoas.tads.lds.bean.Udm;
import br.edu.ifrs.canoas.tads.lds.model.dao.PaisDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoMedidaDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.UdmDAO;
@Stateless
public class ManterUdmService {
	@Inject
	private UdmDAO udmDAO;
	
	public List<Udm> buscaUdm() {
		return  udmDAO.buscaTodos();
	}
	

}
