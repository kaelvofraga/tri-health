package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class ExameUrina extends BaseEntity<Long> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private TipoExameUrina tipoExameUrina;
	private String dataExameUrina;
	private String observacao;
	
	public ExameUrina() {
		super();
	}

	public ExameUrina(Usuario usuario, TipoExameUrina tipoExameUrina,
			String dataExameUrina, String observacao) {
		super();
		this.usuario = usuario;
		this.tipoExameUrina = tipoExameUrina;
		this.dataExameUrina = dataExameUrina;
		this.observacao = observacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoExameUrina getTipoExameUrina() {
		return tipoExameUrina;
	}

	public void setTipoExameUrina(TipoExameUrina tipoExameUrina) {
		this.tipoExameUrina = tipoExameUrina;
	}

	public String getDataExameUrina() {
		return dataExameUrina;
	}

	public void setDataExameUrina(String dataExameUrina) {
		this.dataExameUrina = dataExameUrina;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	

}
