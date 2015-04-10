package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: TipoAtividade
 *
 */
@Entity
public class TipoAtividade extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 8162539112492360609L;

	@NotNull
	private String nome;

	public TipoAtividade() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
