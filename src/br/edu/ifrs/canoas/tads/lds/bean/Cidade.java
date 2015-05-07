package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * 
 * @author: Miromar J. Lima
 * Proposito da Classe: Gerar a cidade do usuário para compor o endereço 
 *         
 */

@Entity
public class Cidade extends BaseEntity<Long> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7728449874961720940L;

	private String nome  = " ";
	
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ID_UF", nullable = true)
	private UnidadeFederativa uf = new UnidadeFederativa();
	
	
	public UnidadeFederativa getUf() {
		return uf;
	}
	public void setUf(UnidadeFederativa uf) {
		this.uf = uf;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cidade [nome=" + nome + ", uf=" + uf + "]";
	}

}
