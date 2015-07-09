package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: CondicaoSaudeUsuario
 * Classe StatusSaude. Irá possuir id e nome
 * 
 * @author Luana Gomes
 * @brief Classe que representa o status de uma condição de saúde 
 * @since 07/07/2015
 * 
 * 
 * Atributos:
 * nome (String): descricao do status
 * 
 */

@Entity
public class StatusSaude extends BaseEntity<Long> implements Serializable{	

	private static final long serialVersionUID = 2380549496314752834L;

	@NotNull
	private String nome;
	
	public StatusSaude() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}