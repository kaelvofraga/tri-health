package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 
 * @author Pablo Diehl
 * @version 24/06/2015
 * 
 * @brief Classe de relacionamento entre Composicao e Usuario
 *
 */

@Entity
public class ComposicaoUsuario  extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -3342715828920539423L;

	@NotNull @ManyToOne
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;
	
	@NotNull @DecimalMax(value="100.00", message="O valor máximo permitido é 100%.")
	private double adiposa;
	
	@NotNull @DecimalMax(value="100.00", message="O valor máximo permitido é 100%.")
	private double residual;
	
	@NotNull @DecimalMax(value="100.00", message="O valor máximo permitido é 100%.")
	private double muscular;
	
	@NotNull @DecimalMax(value="100.00", message="O valor máximo permitido é 100%.")
	private double ossea;

	
	@NotNull @Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Length(max=144, message="Apenas 144 caracteres de notas fera. =P")
	private String notas;

	public ComposicaoUsuario() {
		super();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

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
