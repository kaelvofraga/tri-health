package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: ValorMedida
 * Classe Udm (Unidade de Medida). Irá possuir id e descrição
 * 
 * @author JuarezMonteiro
 * @brief Classe que representa uma Unidade de Medida 
 * @since 07/05/2015
 * 
 * 
 * Atributos:
 * descricao (String): descricao da unidade de medida
 * 
 */

@Entity
public class Udm extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 2714240086533052657L;

	@NotNull 
	private String descricao;
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
