package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/***
 * 
 * @author Pablo Diehl
 * @version 23/06/2015
 * 
 * @brief Classe Bean de Composição Corporal
 * #Atributos:
 * - Descrição: String que descreve uma composição corporal
 *
 */

@Entity
public class Composicao extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -7007609707567404986L;
	
	@NotNull
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
