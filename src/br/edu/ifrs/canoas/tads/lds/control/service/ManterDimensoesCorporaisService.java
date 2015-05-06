package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.MedicamentoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Udm;
import br.edu.ifrs.canoas.tads.lds.bean.ValorMedidaUsuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.TipoMedidaDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.UdmDAO;
import br.edu.ifrs.canoas.tads.lds.model.dao.ValorMedidaDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterDimensoesCorporaisService {
	
	@Inject
	ValorMedidaDAO valorMedidaDAO;
	
	@Inject
	UdmDAO udmDAO;
	
	@Inject
	TipoMedidaDAO tipoMedidaDAO;
	
	
	@SuppressWarnings("unchecked")
	public List<ValorMedidaUsuario> busca(String criterioMedida) {
		if (StrUtil.isNotBlank(criterioMedida))
			return valorMedidaDAO.buscaPorCriterio(criterioMedida);
		else
			return valorMedidaDAO.buscaTodos();
	}
	
	public boolean salvaMedidaUsuario(ValorMedidaUsuario medidaUsuario) {
		valorMedidaDAO.insere(medidaUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterDimensoesCorporais.cadastro.sucesso");
		return true;
	}
	
	public void alteraMedida(ValorMedidaUsuario valorMedidaUsuario) {
		valorMedidaDAO.atualiza(valorMedidaUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterDimensoesCorporais.alterar.sucesso");
	}
	
	public void excluiMedida(ValorMedidaUsuario valorMedidaUsuario) {
		if (valorMedidaUsuario != null && valorMedidaUsuario.getId() != null) {
			valorMedidaDAO.exclui(valorMedidaUsuario.getId());
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterDimensoesCorporais.excluir.sucesso");
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterDimensoesCorporais.excluir.erro");
		}
	}
}
