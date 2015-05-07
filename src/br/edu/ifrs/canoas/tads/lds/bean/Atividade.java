package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Atividade
 * 
 * @brief Classe que representa uma atividade física.
 * @author Kael Fraga
 * @since 07/05/2015
 * 
 * Atributos:
 * - descricao (String): descricao (nome) da atividade.
 * - tipoAtividade (TipoAtividade): tipo (categoria) associado à atividade.
 * - MET (double): fator associado à atividade para cálculo de calorias queimadas.
 * 
 * **/
@Entity
public class Atividade extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -5722376654902174425L;
	
	@NotNull 
	private String descricao;
	
	@NotNull 
	private double MET;

	@NotNull @ManyToOne
	@JoinColumn(name="TIPOATIVIDADE_ID")
	private TipoAtividade tipoAtividade = new TipoAtividade();
	
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
