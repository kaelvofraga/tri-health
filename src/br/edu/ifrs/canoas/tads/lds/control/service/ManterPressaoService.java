package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.PressaoUsuario;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.PressaoUsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

/*
 * @author Pablo Diehl da Silva
 * @version 06/05/2015 
 * 
*/
@Stateless
public class ManterPressaoService {

	@Inject
	private PressaoUsuarioDAO pressaoUsuarioDAO;
	
	/*
	 * Responsável pela inserção de novo registro de pressão arterial na base de dados
	 * 
	 * @param pressaoUsuario Objeto do tipo "PressaoUsuario" a ser cadastrado. 
	 */
	public boolean salvaPressaoUsuario(PressaoUsuario pressaoUsuario) {
		pressaoUsuarioDAO.insere(pressaoUsuario);
		Mensagens.define(FacesMessage.SEVERITY_INFO,"pressao.cadastro.sucesso");
		return true;
	}

	public void alteraPressaoUsario(PressaoUsuario pressaoUsuario) {
		pressaoUsuarioDAO.atualiza(pressaoUsuario);
	}

	public void excluiPressao(PressaoUsuario pressaoUsuario) {
		pressaoUsuarioDAO.exclui(pressaoUsuario.getId());
	}

}
