package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Grau extends BaseEntity<Long> implements Serializable {
	private static final long serialVersionUID = -6833487765093285521L;

	private Integer esquerdo;
	private Integer direito;

	@ManyToOne
	@JoinColumn(name="MEDICO_ID")
	private Medico TipoGrau;
	
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

	public Medico getTipoGrau() {
		return TipoGrau;
	}

	public void setTipoGrau(Medico tipoGrau) {
		TipoGrau = tipoGrau;
	}
}
