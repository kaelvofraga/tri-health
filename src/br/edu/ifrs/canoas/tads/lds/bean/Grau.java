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
	private Integer esquerdo;
	@NotNull 
	private Integer direito;

	@ManyToOne
	@JoinColumn(name="TIPOGRAU_ID")
	private TipoGrau tipoGrau;
	
	
	public Grau() {
		super();
	}

	public Integer getEsquerdo() {
		return esquerdo;
	}

	public void setEsquerdo(Integer esquerdo) {
		this.esquerdo = esquerdo;
	}

	public Integer getDireito() {
		return direito;
	}

	public void setDireito(Integer direito) {
		this.direito = direito;
	}

	public TipoGrau getTipoGrau() {
		return tipoGrau;
	}

	public void setTipoGrau(TipoGrau tipoGrau) {
		this.tipoGrau = tipoGrau;
	}
}