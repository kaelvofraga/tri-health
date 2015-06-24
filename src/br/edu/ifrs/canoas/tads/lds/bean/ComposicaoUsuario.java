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
	@JoinColumn(name="COMPOSICAO_ID")
	private Composicao composicao = new Composicao();
	
	@NotNull @ManyToOne
	@JoinColumn(name="USUARIO_ID")
	private Usuario usuario;
	
	@NotNull @Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	@Length(max=144, message="Só 144 caracteres de notas fera. =P")
	private String notas;
	
	@NotNull @DecimalMax(value="100.00", message="Valor maximo de 100%. :]")
	private double valor;

	public ComposicaoUsuario() {
		super();
	}

	public Composicao getComposicao() {
		return composicao;
	}

	public void setComposicao(Composicao composicao) {
		this.composicao = composicao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
}
