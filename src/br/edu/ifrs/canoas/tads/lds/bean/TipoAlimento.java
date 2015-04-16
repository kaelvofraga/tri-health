package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
public class TipoAlimento extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4096466535761066810L;

	public TipoAlimento() {
		super();
	}

	@NotNull
	@Length(max = 500, min = 4, message = "O nome do tipo de alimento deve ter entre 4 e 100 caracteres.")
	private String nome;

	@Length(max = 400, message = "A descrição do tipo de alimento deve ter no máximo 400 caracteres.")
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
