package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class TipoSanguineo extends BaseEntity<Long> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5916240453422663518L;
	private String nomeTipoSanguineo;
	
	
	public String getNomeTipoSanguineo() {
		return nomeTipoSanguineo;
	}
	public void setNomeTipoSanguineo(String nomeTipoSanguineo) {
		this.nomeTipoSanguineo = nomeTipoSanguineo;
	}
	
	
}
