package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Peso
 */
@Entity
public class Peso extends BaseEntity<Long> implements Serializable {

	/** 
	 * @author Luana
	 * @version 06/05/2015
	 */
	private static final long serialVersionUID = -2819412320994175355L;
	@NotNull	
	private double valor;
	
	public Peso(){}

	public Peso(double valor) {
		super();
		this.valor = valor;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
