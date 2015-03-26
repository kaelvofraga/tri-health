package br.edu.ifrs.canoas.tads.lds.control.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.model.dao.UsuarioDAO;
import br.edu.ifrs.canoas.tads.lds.util.Mensagens;
import br.edu.ifrs.canoas.tads.lds.util.StrUtil;

@Stateless
public class ManterUsuarioService {

	@Inject
	private UsuarioDAO usuarioDAO;

	public boolean salvaUsario(Usuario usuario) {

		int qtdEmailCadastrado = this.validaEmail(usuario);
		
		if (qtdEmailCadastrado == 0) {
			if (validaSenha(usuario)){
				
				usuarioDAO.insere(usuario);
				Mensagens.define(FacesMessage.SEVERITY_INFO, "Usuario.cadastro.sucesso",usuario.getEmail());
				return true;
			}
		} 
		
		Mensagens.define(FacesMessage.SEVERITY_ERROR, "Usuario.email.erro.cadastrado",usuario.getEmail());
		return false;
	}

	
	/**
	 * Valida a senha do usuário. Testa o algoritmo de criptografia
	 * @param usuario
	 * @return
	 */
	private boolean validaSenha(Usuario usuario) {
		String senha = StrUtil.getMd5(usuario.getSenha());
		if (senha.length() > 0){
			//atualizar senha criptografada
			usuario.setSenha(senha);	
			return true;
		}
		return false;
	}

	/**
	 * retorna a quantidade de e-mails cadastrados no banco iguais ao informado.
	 * @param usuario
	 * @return int
	 */
	private int validaEmail(Usuario usuario) {
		if (usuario == null || !StrUtil.isNotBlank(usuario.getEmail()))
			return -1;

		return usuarioDAO
				.buscaPorEmail(usuario.getEmail().trim().toLowerCase())
				.size();
	}


	@SuppressWarnings("unchecked")
	public List<Usuario> busca(String criterio) {
		if (criterio != null && criterio.length() > 0) 
			return usuarioDAO.buscaPorCriterio(criterio);
		else
			return usuarioDAO.buscaTodos();
	}

}
