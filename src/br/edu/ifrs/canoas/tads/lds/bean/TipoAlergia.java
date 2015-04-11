package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: Alergia
 *
 */
@Entity
public class TipoAlergia extends BaseEntity<Long> implements Serializable {
	
	private static final long serialVersionUID = 2835632269224212566L;

	@NotNull
	private String descricao;
	
	public TipoAlergia() {
		super();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return descricao;
	}
	
}
