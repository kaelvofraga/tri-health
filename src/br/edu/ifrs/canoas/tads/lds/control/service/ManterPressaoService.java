package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import br.edu.ifrs.canoas.tads.lds.bean.PressaoUsuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.PressaoUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

/**
* 
* @author Pablo Diehl da Silva
* @version 06/05/2015
* @author Alisson Lorscheiter
* @version 18/06/2015
* Altera��o no metodo salvaPressaoUsuario validando data e adi��o de mensagens.
* Cria��o do m�todo de validaData.
* Altera��o no metodo alteraPesoUsuario para validar a data informada.
*/
@Stateless
public class ManterPressaoService {

	@Inject
	private PressaoUsuarioDAO pressaoUsuarioDAO;
	
	/** 
	 * @brief Metodo que salva no banco de dados a pressaoUsuario ap�s a valida��o.
	 * Retorna V ou F se conseguiu ou n�o salvar no banco.	 		  
	 * @param pressaoUsuario(PressaoUsuario)
	 * @return boolean
	 * Foi inserido a valida��o da data informada e tamb�m foi adicionada mensagens.
	 * */
	public boolean salvaPressaoUsuario(PressaoUsuario pressaoUsuario) {
		if(pressaoUsuario == null || pressaoUsuario.getUsuario() == null){			
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterPressao.cadastro.erro");
			return false;
		}
		if (validaData(pressaoUsuario) == false) {
			return false;
		}
		try{		
		pressaoUsuarioDAO.insere(pressaoUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO, "manterPressao.cadastro.sucesso");
		return true;
		}
		catch (IllegalArgumentException e){
			Mensagens.define(FacesMessage.SEVERITY_INFO, "manterPressao.excecao.erro");
			return false;
		}
	}
	
	/** 
	 * @author Alisson Lorscheiter
	 * @brief Metodo que valida a data informada verificando se ela � menor que a data atual..
	 * Retorna V ou F se validou ou n�o.	 		  
	 * @param pressaoUsuario(PressaoUsuario)
	 * @return boolean
	 * M�todo criado para validar a Data informada e adi��o de mensagem.
	 * */
	public boolean validaData(PressaoUsuario pressaoUsuario){
		long timeSysDate = new Date().getTime();
		long timeData = pressaoUsuario.getData().getTime();
		if (timeData > timeSysDate) {
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterPressao.cadastro.data.erro");
			return false;
		}
		return true;
	}

	/** 
	 * @brief Metodo que valida os campos e altera no banco de dados informa��es da pressaoUsuario.
	 * Retorna V ou F se conseguiu ou n�o alterar no banco.	 		  
	 * @param pressaoUsuario(PressaoUsuario)
	 * @return boolean
	 * 
	 * */
	public boolean alteraPressaoUsuario(PressaoUsuario pressaoUsuario) {
		try {
			if(validaData(pressaoUsuario)==false){
				Mensagens.define(FacesMessage.SEVERITY_INFO,
						"manterPressao.cadastro.data.erro");
				return false;
			}
			pressaoUsuarioDAO.atualiza(pressaoUsuario);
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"manterPressao.altera.sucesso");
			return true;
			}
			catch (IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterPressao.altera.excecao.erro");
			return false;
		}
	}

	/** 
	 * @brief Metodo que exclui o pesoUsuario do banco de dados.
	 * Retorna V ou F se conseguiu ou n�o excluir do banco.	 		  
	 * @param pesoUsuario(PesoUsuario)
	 * @return boolean
	 * */
	public boolean excluiPressaoUsuario(PressaoUsuario pressaoUsuario) {
		try{
		if (pressaoUsuario.getId() != null && pressaoUsuario != null) {	
				pressaoUsuarioDAO.exclui(pressaoUsuario.getId());
				Mensagens.define(FacesMessage.SEVERITY_INFO,"manterPressao.exclui.sucesso");
				return true;
		} else {
			Mensagens.define(FacesMessage.SEVERITY_INFO,"manterPressao.exclui.erro");
			return false;
		}
		}
		catch (IllegalArgumentException e) {
			Mensagens.define(FacesMessage.SEVERITY_ERROR,
					"manterPressao.exclui.excecao.erro");
			return false;
		}
	}
	
	/** 
	 * @brief Metodo que realiza busca no banco de dados das press�es do usu�rio
	 * atrav�s de crit�rio informado.
	 * Retorna lista de PressaoUsuario.	 		  
	 * @param criterioPressao(String)
	 * @return List<PressaoUsuario>
	 * */
	public List<PressaoUsuario> busca(String criterioPressao) {
		try{
		if (StrUtil.isNotBlank(criterioPressao)&& criterioPressao != null) {
			if (!pressaoUsuarioDAO.buscaPorCriterio(criterioPressao).isEmpty()) {
				return pressaoUsuarioDAO.buscaPorCriterio(criterioPressao);
			} 
			else {
				Mensagens.define(FacesMessage.SEVERITY_INFO,"listarPressao.busca.vazio");
				return new ArrayList<PressaoUsuario>();
			}
		} else
			return pressaoUsuarioDAO.buscaTodos();
		}
		catch(EJBException e){
			Mensagens.define(FacesMessage.SEVERITY_ERROR,"listarPressao.busca.excecao");
			return null;
		}
		catch(NullPointerException e){
			Mensagens.define(FacesMessage.SEVERITY_ERROR,"listarPressao.busca.virgula");
			return null;
		}
	}	

}
