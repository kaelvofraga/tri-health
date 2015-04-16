package br.edu.ifrs.canoas.tads.lds.bean;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cidade extends BaseEntity<Long>{
	
	private String nomeCidade;
	@OneToOne
    @JoinColumn(name="ID_UF")
	private UnidadeFederativa uf;
	
	
	public UnidadeFederativa getUf() {
		return uf;
	}
	public void setUf(UnidadeFederativa uf) {
		this.uf = uf;
	}
	
	public String getNomeCidade() {
		return nomeCidade;
	}
	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

}
