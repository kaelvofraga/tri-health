package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.edu.ifrs.canoas.tads.lds.bean.BaseEntity;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

/**
 * Entity implementation class for Entity: PressaoUsuario
 * 
 * Esta é a classe responsável pelo relacionamento das classes PressaoArterial e Usuario.
 * 
 * @author Pablo Diehl da Silva
 * @version 06/05/2015
 * 
 * Atributos:
 * - pressaoArterial (PressaoArterial): Objeto da classe PressaoArterial;
 * - usuario (Usuario): Objeto da classe Usuario;
 * - data (Date): Corresponde à data de medição da pressão arterial a ser registrada;
 * - notas (String): Atributo textual para salvar possíveis observações.
 * 
 */
@Entity
public class PressaoUsuario extends BaseEntity<Long> implements Serializable{
	private static final long serialVersionUID = 9138218268279147003L;
	//Todos atibutos são marcados como "@NotNull" para evitar a inserção de valores nulos na base de dados

	@NotNull @ManyToOne(targetEntity=Usuario.class)
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;
	
	@NotNull
	private double paSistolica;

	@NotNull
	private double paDiastolica;

	@NotNull
	private int pulso;

	@NotNull
	private char batimentoIrregular;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@NotNull @Length(max=144, message="As notas devem possuir no m�ximo 144 caracteres!")
	private String notas;
	
	public PressaoUsuario(){
		super();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}
}
