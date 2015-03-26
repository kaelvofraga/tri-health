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
public class GerenciarLoginService {

	@Inject
	private UsuarioDAO usuarioDAO;

	public Usuario login(Usuario usuario) {
		
		//Criptografa a senha para comparar com a do banco
		usuario.setSenha(StrUtil.getMd5(usuario.getSenha()));
		
		List<Usuario> usuarios = usuarioDAO.busca(usuario);

		if (usuarios.size() == 1) {
			Mensagens.define(FacesMessage.SEVERITY_INFO,
					"Usuario.login.sucesso", usuario.getEmail());
			return usuarios.get(0);
		} else {
			Mensagens.define(FacesMessage.SEVERITY_ERROR, "Usuario.login.erro",
					usuario.getEmail());
		}
		return usuario;
	}

	public Usuario logout() {
		return new Usuario();
	}

}
