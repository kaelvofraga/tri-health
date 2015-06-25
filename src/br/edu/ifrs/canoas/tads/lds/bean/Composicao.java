package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

/***
 * 
 * @author Pablo Diehl
 * @version 23/06/2015
 * 
 * @brief Classe Bean de Composição Corporal
 * #Atributos:
 * - Descrição: String que descreve uma composição corporal
 *
 */

@Entity
public class Composicao extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -7007609707567404986L;
	
	@NotNull @DecimalMax(value="100.00", message="Valor maximo de 100%. :]")
	private double adiposa;
	
	@NotNull @DecimalMax(value="100.00", message="Valor maximo de 100%. :]")
	private double residual;
	
	@NotNull @DecimalMax(value="100.00", message="Valor maximo de 100%. :]")
	private double muscular;
	
	@NotNull @DecimalMax(value="100.00", message="Valor maximo de 100%. :]")
	private double ossea;

	public double getAdiposa() {
		return adiposa;
	}

	public void setAdiposa(double adiposa) {
		this.adiposa = adiposa;
	}

	public double getResidual() {
		return residual;
	}

	public void setResidual(double residual) {
		this.residual = residual;
	}

	public double getMuscular() {
		return muscular;
	}

	public void setMuscular(double muscular) {
		this.muscular = muscular;
	}

	public double getOssea() {
		return ossea;
	}

	public void setOssea(double ossea) {
		this.ossea = ossea;
	}
}
