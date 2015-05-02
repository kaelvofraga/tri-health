package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class TipoExameUrina extends BaseEntity<Long> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3763819174766205770L;

	private String tipo;
//	private String unidadeMedida;
//	private String valorReferencia;
	
	public TipoExameUrina() {
		super();
	}

	public TipoExameUrina(String tipo){ // String unidadeMedida, 
//			String valorReferencia) {
		super();
		this.tipo = tipo;
//		this.unidadeMedida = unidadeMedida;
//		this.valorReferencia = valorReferencia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/*public String getUnidadeMedida() {
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
*/
}
