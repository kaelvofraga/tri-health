package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;


/**
 * 
 * @author: Miromar J. Lima
 * Proposito da Classe: Compor endereço de usuário 
 *         
 */
@Entity
public class Pais extends BaseEntity<Long> implements Serializable {
	
	/**
	 * Variáveis foram iniciadas para evitar erros ao incluir novos usuários
	 */
	private static final long serialVersionUID = 186935749207326730L;
	private String nome  = " ";
	private String nacionalidade  = " ";
	
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
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
		return "Pais [nome=" + nome + ", nacionalidade="
				+ nacionalidade + "]";
	}
	

}
