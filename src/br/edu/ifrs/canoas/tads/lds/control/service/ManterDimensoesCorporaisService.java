package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
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
	
	
	/**
	 * @param criterioMedida Se refere a um critério de busca (Unidade de Medida ou Tipo da Medida) do tipo String;
	 * @return retorna todos os objetos os quais possuem (Aproximado ou igual) em seus atributos o criterioMedida;
	 */
	@SuppressWarnings("unchecked")
	public List<ValorMedidaUsuario> busca(String criterioMedida) {
		if (StrUtil.isNotBlank(criterioMedida))
			return valorMedidaDAO.buscaPorCriterio(criterioMedida);
		else
			return valorMedidaDAO.buscaTodos();
	}
	
	/**
	 * @param medidaUsuario Objeto do tipo ValorMedidaUsuario;
	 * @return Insere no banco uma nova medida;
	 */
	public boolean salvaMedidaUsuario(ValorMedidaUsuario medidaUsuario) {
		valorMedidaDAO.insere(medidaUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterDimensoesCorporais.cadastro.sucesso");
		return true;
	}
	
	/**
	 * @param valorMedidaUsuario variável do tipo ValorMedidaUsuario o qual irá possuir os atributos para a atualização;
	 * Esse método irá alterar um objeto já existente no banco;
	 */
	public void alteraMedida(ValorMedidaUsuario valorMedidaUsuario) {
		valorMedidaDAO.atualiza(valorMedidaUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterDimensoesCorporais.alterar.sucesso");
	}
	
	/**
	 * @param valorMedidaUsuario variável do tipo ValorMedidaUsuario;
	 * Esse método irá excluir um objeto ValorMedidaUsuario que seja iugal ao parâmetro recebido por esse;
	 */
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
