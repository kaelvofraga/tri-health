package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: PressaoArterial 
 * 
 * Esta classe é
 * responsável pelo "modelo" de informações de pressão arterial necessárias
 * conforme a especificação do projeto
 *
 * @author Pablo Diehl da Silva
 * @version 06/05/2015
 * 
 * Atributos:
 * - paSistolica (Double): Corresponde ao valor de Pressão arterial sistólica;
 * - paDiastolica (Double): Corresponde ao valor de Pressão arterial diastólica;
 * - pulso (int): Corresponde à pulsação arterial;
 * - batimentoIrregular (char): Corresponde ao estado de batimento (se este encontra-se irregular ou não);
 * 
 */
@Entity
public class PressaoArterial extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -679045406140081158L;
	//Todos atibutos são marcados como "@NotNull" para evitar a inserção de valores nulos na base de dados
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

	public char getBatimentoIrregular() {
		return batimentoIrregular;
	}

	public void setBatimentoIrregular(char batimentoIrregular) {
		this.batimentoIrregular = batimentoIrregular;
	}

}