package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class RefeicaoAlimento extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4552356244957664970L;

	public RefeicaoAlimento() {
		super();
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "REFEICAO_ID")
	private Refeicao refeicao;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "ALIMENTO_ID")
	private Alimento alimento;

	@Min(value = 0, message = "O quantidade do alimento não pode ser negativa.")
	private double pesoEmGramasOuMl;

	public Refeicao getRefeicao() {
		return refeicao;
	}

	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public double getPesoEmGramasOuMl() {
		return pesoEmGramasOuMl;
	}

	public void setPesoEmGramasOuMl(double pesoEmGramasOuMl) {
		this.pesoEmGramasOuMl = pesoEmGramasOuMl;
	}
}
