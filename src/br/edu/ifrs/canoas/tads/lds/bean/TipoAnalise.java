package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * @author: André Ficht/Alisson Lorscheiter
 * Proposito da Classe: Classe que representa a entidade dos tipos de exames presentes nos diferentes exames. 
 *          
 */

@Entity
public class TipoAnalise extends BaseEntity<Long> implements Serializable{
	

	private static final long serialVersionUID = 97366118737547620L;
	
	private String tipo;
	private String valorReferencia;
	
	
	public TipoAnalise() {
		super();
	}
	
	
	/*GETTERS & SETTERS*/

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		return "TipoExameUrina [tipo=" + tipo + ", valorReferencia=" + valorReferencia + "]";
	}*/
}
