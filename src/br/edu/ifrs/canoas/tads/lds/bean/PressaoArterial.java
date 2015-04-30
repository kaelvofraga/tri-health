package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: PressaoArterial
 *
 */
@Entity
public class PressaoArterial extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -679045406140081158L;

	@NotNull
	private double paSistolica;
	
	@NotNull
	private double paDiastolica;
	
	@NotNull
	private int pulso;
	
	@NotNull
	private char batimentoIrregular;

	public double getPaSistolica() {
		return paSistolica;
	}

	public void setPaSistolica(double paSistolica) {
		this.paSistolica = paSistolica;
	}

	public double getPaDiastolica() {
		return paDiastolica;
	}

	public void setPaDiastolica(double paDiastolica) {
		this.paDiastolica = paDiastolica;
	}

	public int getPulso() {
		return pulso;
	}

	public void setPulso(int pulso) {
		this.pulso = pulso;
	}

	public char isBatimentoIrregular() {
		return batimentoIrregular;
	}

	public void setBatimentoIrregular(char batimentoIrregular) {
		this.batimentoIrregular = batimentoIrregular;
	}

	@Override
	public String toString() {
		return "PressaoArterial [paSistolica=" + paSistolica
				+ ", paDiastolica=" + paDiastolica + ", pulso=" + pulso
				+ ", batimentoIrregular=" + batimentoIrregular + "]";
	}
	
	
}
