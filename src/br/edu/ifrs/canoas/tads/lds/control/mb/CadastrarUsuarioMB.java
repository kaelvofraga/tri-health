package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.Usuario;
import br.edu.ifrs.canoas.tads.lds.control.service.ManterUsuarioService;

@Named
@RequestScoped
public class CadastrarUsuarioMB implements Serializable {

	private static final long serialVersionUID = 7918766405702133530L;

	@Inject
	private Usuario usuario;

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;

	
	@EJB
	private ManterUsuarioService usuarioService;

	private String confirmacaoSenha;

	public String salva() {
		if (usuarioService.salvaUsario(usuario)){
			gerenciarLoginMB.setUsuario(usuario);
			usuario = new Usuario();
			return "login";
		}
		else return "manterUsuario";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

}