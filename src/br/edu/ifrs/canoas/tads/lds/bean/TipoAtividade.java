package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: TipoAtividade
 *
 * @brief Classe que representa o tipo (categoria) de uma atividade.
 * @author Kael Fraga
 * @since 07/05/2015
 * 
 * Atributos:
 * - nome (String): nome do tipo de atividade.
 * 
 * **/
@Entity
public class TipoAtividade extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 8162539112492360609L;

	@NotNull
	private String nome;

	public TipoAtividade() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
