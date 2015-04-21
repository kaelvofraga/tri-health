package br.edu.ifrs.canoas.tads.lds.control.mb;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

@Named
@SessionScoped

public class ManterPerfilUsuarioMB implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6343391229889538808L;

	@Inject
	private GerenciarLoginMB gerenciarLoginMB;
	
	@Inject
	private Usuario usuario;

	public ManterPerfilUsuarioMB() {
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public GerenciarLoginMB getGerenciarLoginMB() {
		return gerenciarLoginMB;
	}

	public void setGerenciarLoginMB(GerenciarLoginMB gerenciarLoginMB) {
		this.gerenciarLoginMB = gerenciarLoginMB;
	}

}
