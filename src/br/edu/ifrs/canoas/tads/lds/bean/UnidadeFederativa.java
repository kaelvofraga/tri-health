package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
@Entity


/**
 * 
 * @author: Miromar J. Lima
 * Proposito da Classe: Comopor endere�o de usu�rio 
 *         
 */
public class UnidadeFederativa extends BaseEntity<Long> implements Serializable {
	
	
	private static final long serialVersionUID = -4613659829962646745L;
	private String nome  = " ";
	/**
	 * Estabelecer relacionamento com tabela de Pais
	 */
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ID_PAIS", nullable = true)
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
