package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Alimento extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2190806938689060416L;

	public Alimento() {
		super();
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "TIPOALIMENTO_ID")
	private TipoAlimento tipoAlimento;

	@Length(max = 200, min = 4, message = "O nome do alimento deve ter entre 4 e 200 caracteres.")
	private String nome;
	@Min(value = 0, message = "A quantidade de calorias não pode ser negativa.")
	private double caloriasPorCemGrOuMl;

	public TipoAlimento getTipoAlimento() {
		return tipoAlimento;
	}

	public void setTipoAlimento(TipoAlimento tipoAlimento) {
		this.tipoAlimento = tipoAlimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getCaloriasPorCemGrOuMl() {
		return caloriasPorCemGrOuMl;
	}

	public void setCaloriasPorCemGrOuMl(double caloriasPorCemGrOuMl) {
		this.caloriasPorCemGrOuMl = caloriasPorCemGrOuMl;
	}
}
