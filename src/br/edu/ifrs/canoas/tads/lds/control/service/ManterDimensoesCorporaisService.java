package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.ValorMedidaUsuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoMedidaDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.UdmDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.ValorMedidaDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;

@Stateless
public class ManterDimensoesCorporaisService {
	
	@Inject
	ValorMedidaDAO valorMedidaDAO;
	
	@Inject
	UdmDAO udmDAO;
	
	@Inject
	TipoMedidaDAO tipoMedidaDAO;
	
	
	public List<ValorMedidaUsuario> busca(String criterioTipoDimensao) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean salvaMedidaUsuario(ValorMedidaUsuario medidaUsuario) {
		
		valorMedidaDAO.insere(medidaUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterDimensoesCorporais.cadastro.sucesso");
		return true;
	}

}
