package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.bean.PesoUsuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.PesoUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

/** 
 * Classe de controle que possui o método salvar, alterar, buscar e excluir
 * 
 * @author Luana
 * @version 06/05/2015
 * @author Alisson Lorscheiter
 * @version 18/06/2015
 * Alteração no metodo salvaPesoUsuario validando data e udm e adição de mensagens.
 * Criação do método de validaData.
 * Alteração no metodo alteraPesoUsuario para validar a udm informada.
 */

@Stateless
public class ManterPesoService {

	@Inject 
	private PesoUsuarioDAO pesoUsuarioDAO;
		
	/** 
	 * @brief Metodo que salva no banco de dados o pesoUsuario após a validação.
	 * Retorna V ou F se conseguiu ou não salvar no banco.	 		  
	 * @param pesoUsuario(PesoUsuario)
	 * @return boolean
	 * Foi inserido a validação da data e da udm informada e também foi adicionada mensagens.
	 * */
	public boolean salvaPesoUsuario(PesoUsuario pesoUsuario) {
		if(pesoUsuario == null || pesoUsuario.getUsuario() == null){			
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterPeso.cadastro.erro");
			return false;
		}
		if (validaData(pesoUsuario) == false) {
			return false;
		}
		if(pesoUsuario.getUdm().getId()==3 || pesoUsuario.getUdm().getId()==4){
		pesoUsuarioDAO.insere(pesoUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterPeso.cadastro.sucesso");
		return true;
		}
		else{
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterPeso.udm.erro");
			return false;
		}
	}	
	
	/** 
	 * @author Alisson Lorscheiter
	 * @brief Metodo que valida a data informada verificando se ela é menor que a data atual..
	 * Retorna V ou F se validou ou não.	 		  
	 * @param pesoUsuario(PesoUsuario)
	 * @return boolean
	 * Método criado para validar a Data informada e adição de mensagem.
	 * */
	public boolean validaData(PesoUsuario pesoUsuario){
		long timeSysDate = new Date().getTime();
		long timeData = pesoUsuario.getData().getTime();
		if (timeData > timeSysDate) {
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterPeso.cadastro.data.erro");
			return false;
		}
		return true;
	}
	
	/** 
	 * @brief Metodo que valida os campos e altera no banco de dados informações do pesoUsuario.
	 * Retorna V ou F se conseguiu ou não alterar no banco.	 		  
	 * @param pesoUsuario(PesoUsuario)
	 * @return boolean
	 * Foi inserida a validação da udm informada e mensagens.
	 * */
	public boolean alteraPesoUsuario(PesoUsuario pesoUsuario) {
		try {
			if(validaData(pesoUsuario)==false){
				Mensagens.define(FacesMessage.SEVERITY_INFO,
						"manterPeso.cadastro.data.erro");
				return false;
			}
			if(pesoUsuario.getUdm().getId()==3 || pesoUsuario.getUdm().getId()==4){
			pesoUsuarioDAO.atualiza(pesoUsuario);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterPeso.altera.sucesso");
			return true;
			}
			else{
				Mensagens.define(FacesMessage.SEVERITY_INFO,
						"manterPeso.udm.erro");	
				return false;
			}
		} catch (IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterPeso.altera.excecao.erro");
			return false;
		}
	}

	/** 
	 * @brief Metodo que exclui o pesoUsuario do banco de dados.
	 * Retorna V ou F se conseguiu ou não excluir do banco.	 		  
	 * @param pesoUsuario(PesoUsuario)
	 * @return boolean
	 * */
	public boolean excluiPesoUsuario(PesoUsuario pesoUsuario) {
		try{
		if (pesoUsuario.getId() != null && pesoUsuario != null) {	
				pesoUsuarioDAO.exclui(pesoUsuario.getId());
				Mensagens.define(FacesMessage.SEVERITY_INFO,"manterPeso.exclui.sucesso");
				return true;
		} else {
			Mensagens.define(FacesMessage.SEVERITY_INFO,"manterPeso.exclui.erro");
			return false;
		}
		}
		catch (IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterPeso.exclui.excecao.erro");
			return false;
		}
	}
	
	/** 
	 * @brief Metodo que realiza busca no banco de dados os pesos do usuário
	 * através de critério informado.
	 * Retorna lista de PesoUsuario.	 		  
	 * @param criterioPeso(String)
	 * @return List<PesoUsuario>
	 * */
	public List<PesoUsuario> busca(String criterioPeso) {
		try{
		if (StrUtil.isNotBlank(criterioPeso)&& criterioPeso != null) {
			if (!pesoUsuarioDAO.buscaPorCriterio(criterioPeso).isEmpty()) {
				return pesoUsuarioDAO.buscaPorCriterio(criterioPeso);
			} 
			else {
				Mensagens.define(FacesMessage.SEVERITY_INFO,"listarPeso.busca.vazio");
				return new ArrayList<PesoUsuario>();
			}
		} else
			return pesoUsuarioDAO.buscaTodos();
		}
		catch(EJBException e){
			Mensagens.define(FacesMessage.SEVERITY_ERROR,"listarPeso.busca.excecao");
			return null;
		}
		catch(NullPointerException e){
			Mensagens.define(FacesMessage.SEVERITY_ERROR,"listarPeso.busca.virgula");
			return null;
		}
	}	
}