package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class TipoGrau extends BaseEntity<Long> implements Serializable  {
	private static final long serialVersionUID = -6833487765093285520L;
	
	@NotNull 
	private String descricao;
	
	public TipoGrau() {
		super();
	}

	public TipoGrau(String descricao) {
		super();
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
