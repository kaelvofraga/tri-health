package br.edu.ifrs.canoas.tads.lds.bean;

import javax.persistence.Entity;

@Entity
public class Idioma extends BaseEntity<Long>{
	private String nomeIdioma;

	public String getNomeIdioma() {
		return nomeIdioma;
	}

	public void setNomeIdioma(String nomeIdioma) {
		this.nomeIdioma = nomeIdioma;
	}
	

}
