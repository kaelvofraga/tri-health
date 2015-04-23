package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class EspecialidadeMedica extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -3785695628695056405L;
	
	@NotNull @ManyToOne
	@JoinColumn(name="ID")
	private Medico medico;
	
	@NotNull
	private String descricao;

}
