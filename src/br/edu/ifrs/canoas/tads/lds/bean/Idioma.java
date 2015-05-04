package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Idioma extends BaseEntity<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6181295878306601637L;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
