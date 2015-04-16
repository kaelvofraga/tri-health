package br.edu.ifrs.canoas.tads.lds.bean;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class UnidadeFederativa extends BaseEntity<Long>{
	
	private String nomeUf;
	@OneToOne
    @JoinColumn(name="ID_PAIS")
	private Pais pais;
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public String getNomeUf() {
		return nomeUf;
	}
	public void setNomeUf(String nomeUf) {
		this.nomeUf = nomeUf;
	}

}
