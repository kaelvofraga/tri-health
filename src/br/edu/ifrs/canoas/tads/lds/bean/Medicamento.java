package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Medicamento
 * @author Alisson Lorscheiter
 *
 */
@Entity
public class Medicamento extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 5892853808532686182L;
	@NotNull
	private String nome;
	
	public Medicamento() {
		super();
	}

	/*GETTERS & SETTERS*/
	
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
