package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Atividade
 *
 */
@Entity
public class Atividade extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -5722376654902174425L;
	
	@NotNull 
	private String descricao;
	
	@NotNull 
	private double MET;

	@NotNull @OneToOne
	@JoinColumn(name="TIPOATIVIDADE_ID")
	private TipoAtividade tipoAtividade;
	
	public Atividade() {
		super();
	}	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}	
	
	public double getMET() {
		return MET;
	}

	public void setMET(double mET) {
		MET = mET;
	}
	
}
