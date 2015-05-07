package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: PressaoArterial
 *
 * Esta classe � respons�vel pelo "modelo" de informa��es de press�o arterial
 * necess�rias conforme a especifica��o do projeto
 *
 * @author Pablo Diehl da Silva
 * @version 06/05/2015
 *
 *          Atributos: - paSistolica (Double): Corresponde ao valor de Press�o
 *          arterial sist�lica; - paDiastolica (Double): Corresponde ao valor de
 *          Press�o arterial diast�lica; - pulso (int): Corresponde � pulsa��o
 *          arterial; - batimentoIrregular (char): Corresponde ao estado de
 *          batimento (se este encontra-se irregular ou n�o);
 *
 */
@Entity
public class PressaoArterial extends BaseEntity<Long> implements Serializable {
	private static final long serialVersionUID = -679045406140081158L;
	// Todos atibutos s�o marcados como "@NotNull" para evitar a inser��o de
	// valores nulos na base de dados
	@NotNull @DecimalMax(value= "30.00", message = "Valores de press�o devem ser menores que 30.00")
	private double paSistolica;
	@NotNull @DecimalMax(value= "30.00", message = "Valores de press�o devem ser menores que 30.00")
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