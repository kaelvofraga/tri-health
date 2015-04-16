package br.edu.ifrs.canoas.tads.lds.bean;

import javax.persistence.Entity;

@Entity
public class Pais extends BaseEntity<Long>{
	
	private String nomePais;
	private String nacionalidade;
	
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	
	public String getNomePais() {
		return nomePais;
	}
	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}
	

}
