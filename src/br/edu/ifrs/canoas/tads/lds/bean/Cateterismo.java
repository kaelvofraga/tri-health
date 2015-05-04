package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
public class Cateterismo extends BaseEntity<Long> implements Serializable{

	private static final long serialVersionUID = -7501350516318175545L;
	
	@NotNull
	@OneToOne
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;

	@NotNull
	@OneToOne
	@JoinColumn(name="MEDICO_SOL_ID")
	private Medico medicoSolicitante;
	
	@NotNull
	@OneToOne
	@JoinColumn(name="MEDICO_RES_ID")
	private Medico medicoResponsavel;
	
	@NotNull
	private String laudo;
	
	@NotNull
	private String dataInternacao;
	
	@NotNull
	private String dataAlta;
	
	@NotNull
	private String observacoes;

	public Medico getMedicoSolicitante() {
		return medicoSolicitante;
	}

	public void setMedicoSolicitante(Medico medicoSolicitante) {
		this.medicoSolicitante = medicoSolicitante;
	}

	public Medico getMedicoResponsavel() {
		return medicoResponsavel;
	}

	public void setMedicoResponsavel(Medico medicoResponsavel) {
		this.medicoResponsavel = medicoResponsavel;
	}

	public String getLaudo() {
		return laudo;
	}

	public void setLaudo(String laudo) {
		this.laudo = laudo;
	}

	public String getDataInternacao() {
		return dataInternacao;
	}

	public void setDataInternacao(String dataInternacao) {
		this.dataInternacao = dataInternacao;
	}

	public String getDataAlta() {
		return dataAlta;
	}

	public void setDataAlta(String dataAlta) {
		this.dataAlta = dataAlta;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
		
	

}
