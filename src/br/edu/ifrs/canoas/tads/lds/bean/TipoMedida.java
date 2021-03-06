package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: ValorMedida
 * @author JuarezMonteiro
 * 
 * @brief Classe que representa um Tipo de Medida 
 * @since 07/05/2015
 * 
 * 
 * Atributos:
 * descricao (String): descricao do tipo da medida
 * 
 */

@Entity
public class TipoMedida extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = -5430266548612906186L;

	
	@NotNull 
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	
	
	
}
