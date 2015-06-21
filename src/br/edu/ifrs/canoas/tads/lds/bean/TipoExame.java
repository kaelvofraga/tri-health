package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

/**
 * @author: Miromar J. Lima
 * Proposito da Classe: Classe que representa a entidade dos tipos de exames presentes nos exames cardiológicos. 
 *          
 */


public class TipoExame extends BaseEntity<Long> implements Serializable{
	private static final long serialVersionUID = 97366118737547620L;
	private String descricao;
	private String unidadeDeMedida;
	private String valorReferencia;
	
	
	public TipoExame() {
		super();
	}
	public String getDescricao() {
		return descricao;
	}


	public String getUnidadeDeMedida() {
		return unidadeDeMedida;
	}


	public String getValorReferencia() {
		return valorReferencia;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public void setUnidadeDeMedida(String unidadeDeMedida) {
		this.unidadeDeMedida = unidadeDeMedida;
	}


	public void setValorReferencia(String valorReferencia) {
		this.valorReferencia = valorReferencia;
	}
	@Override
	public String toString() {
		return "TipoExame [descricao=" + descricao + ", unidadeDeMedida="
				+ unidadeDeMedida + ", valorReferencia=" + valorReferencia
				+ "]";
	}


	

}
