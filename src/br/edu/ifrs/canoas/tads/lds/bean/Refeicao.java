package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Refeicao extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4676157611979535684L;

	public Refeicao() {
		super();
	}

	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIO_ID")
	private Usuario usuario;

	@NotNull(message = "A data da refeição é obrigatória.")
	@Temporal(TemporalType.TIMESTAMP)
	private Date refeicaoData;

	@Length(max = 500, message = "A Observação deve ter no máximo 500 caractéres.")
	private String observacao;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getRefeicaoData() {
		return refeicaoData;
	}

	public void setRefeicaoData(Date refeicaoData) {
		this.refeicaoData = refeicaoData;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
