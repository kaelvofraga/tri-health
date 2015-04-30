package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class TipoExameUrina extends BaseEntity<Long> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3763819174766205770L;

	private String nomeExameUrina;
	private String unidadeMedida;
	private String valorReferencia;
	
	public TipoExameUrina() {
		super();
	}

	public TipoExameUrina(String nomeExameUrina, String unidadeMedida, 
			String valorReferencia) {
		super();
		this.nomeExameUrina = nomeExameUrina;
		this.unidadeMedida = unidadeMedida;
		this.valorReferencia = valorReferencia;
	}

	public String getNomeExameUrina() {
		return nomeExameUrina;
	}

	public void setNomeExameUrina(String nomeExameUrina) {
		this.nomeExameUrina = nomeExameUrina;
	}

	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public String getValorReferencia() {
		return valorReferencia;
	}

	public void setValorReferencia(String valorReferencia) {
		this.valorReferencia = valorReferencia;
	}

}
