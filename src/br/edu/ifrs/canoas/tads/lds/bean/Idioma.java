package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Idioma extends BaseEntity<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6181295878306601637L;
	private String nomeIdioma;

	public String getNomeIdioma() {
		return nomeIdioma;
	}

	public void setNomeIdioma(String nomeIdioma) {
		this.nomeIdioma = nomeIdioma;
	}
	

}
