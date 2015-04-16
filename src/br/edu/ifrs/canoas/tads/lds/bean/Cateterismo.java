package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class Cateterismo extends BaseEntity<Long> implements Serializable{

	private static final long serialVersionUID = -7501350516318175545L;
	
	@NotNull
	private Medico medicoSolicitante;
	
	@NotNull
	private Medico medicoResponsavel;
	
	@NotNull
	private String laudo;
	
	@NotNull
	private String dataInternacao;
	
	@NotNull
	private String dataAlta;
	
	@NotNull
	private String observacoes;
		

}
