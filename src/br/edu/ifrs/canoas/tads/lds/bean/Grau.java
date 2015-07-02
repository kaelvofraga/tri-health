package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Grau extends BaseEntity<Long> implements Serializable {
	private static final long serialVersionUID = -6833487765093285521L;

	@NotNull 
	private Double esquerdo;
	@NotNull 
	private Double direito;

	@ManyToOne
	@JoinColumn(name="TIPOGRAU_ID")
	private TipoGrau tipoGrau;
	
	
	public Grau() {
		super();
	}

	public Grau(Double esquerdo, Double direito, TipoGrau tipoGrau) {
		super();
		this.esquerdo = esquerdo;
		this.direito = direito;
		this.tipoGrau = tipoGrau;
	}

	public Double getEsquerdo() {
		return esquerdo;
	}

	public void setEsquerdo(Double esquerdo) {
		this.esquerdo = esquerdo;
	}

	public Double getDireito() {
		return direito;
	}

	public void setDireito(Double direito) {
		this.direito = direito;
	}

	public TipoGrau getTipoGrau() {
		return tipoGrau;
	}

	public void setTipoGrau(TipoGrau tipoGrau) {
		this.tipoGrau = tipoGrau;
	}
}