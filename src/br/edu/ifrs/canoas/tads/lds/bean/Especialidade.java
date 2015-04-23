package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Especialidade extends BaseEntity<Long> implements Serializable{

	private static final long serialVersionUID = -3160647703419884L;

	@NotNull
	private String descricao;
}
