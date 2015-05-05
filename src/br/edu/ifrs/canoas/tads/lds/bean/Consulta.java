package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@NamedQueries({
	@NamedQuery(name="todasConsultas", query = "SELECT c FROM Consulta c")
})
@Entity
public class Consulta extends BaseEntity<Long> implements Serializable  {
	
	private static final long serialVersionUID = -6833487765093285536L;
	
	@NotNull 
	private String descricao;

	//@ManyToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="id_usuario", referencedColumnName="id_usuario")
	private Usuario usuario;
	
	public Consulta() {
		super();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
