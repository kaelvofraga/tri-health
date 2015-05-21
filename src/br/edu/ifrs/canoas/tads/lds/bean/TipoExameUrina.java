package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * @author: André Ficht
 * Proposito da Classe: Classe que representa a entidade dos tipos de exames presentes na Urina.
 * @author: Alisson Lorscheiter
 * 
 *          
 */

@Entity
public class TipoExameUrina extends BaseEntity<Long> implements Serializable{
	
	private static final long serialVersionUID = 6235100045027463775L;
	
	private String tipo;
	private String unidadeMedida;
	private String valorReferencia;
	
	
	public TipoExameUrina() {
		super();
	}
	
	
	/*GETTERS & SETTERS*/

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
	/*
	@Override
	public String toString() {
		return "TipoExameUrina [tipo=" + tipo + ", unidadeMedida="
				+ unidadeMedida + ", valorReferencia=" + valorReferencia + "]";
	}*/
}
