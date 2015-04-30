package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity
public class UnidadeFederativa extends BaseEntity<Long> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4613659829962646745L;
	private String nome;
	@OneToOne
    @JoinColumn(name="ID_PAIS")
	private Pais pais = new Pais();
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
