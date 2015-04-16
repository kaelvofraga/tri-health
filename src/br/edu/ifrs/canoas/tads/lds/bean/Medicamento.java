package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: Medicamento
 *
 */
@Entity
public class Medicamento extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 7372369677196846335L;
	
	
	@NotNull
	private String nome;
	
	public Medicamento() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return nome;
	}
}
