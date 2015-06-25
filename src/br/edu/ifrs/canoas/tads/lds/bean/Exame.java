package br.edu.ifrs.canoas.tads.lds.bean;

import br.edu.ifrs.canoas.tads.lds.bean.BaseEntity;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Exame extends BaseEntity<Exame> implements Serializable {

	
	private static final long serialVersionUID = 9127288997447808922L;
	
	@NotNull
	private String nome;
	@Length(max = 200, message = "A descrição do exame deve ter no máximo 300 caracteres.")
	private String descricao;

	public Exame() {
		super();
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}   
}
