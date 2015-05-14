package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**@author: Alisson Lorscheiter
 * Proposito da Classe: Classe que representa a entidade dos items de exames que estão 
 * presentes no exame de urina
 * 
 */

@Entity
public class ItemExameUrina extends BaseEntity<Long> implements Serializable{
	
	private static final long serialVersionUID = -8539097274270004334L;

	
	@NotNull 
	@OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="ID_TIPOEXAMEURINA")
	private TipoExameUrina tipoExameUrina;
	
	@NotNull
	private String resultado;
	
	private String observacao;
	
	public ItemExameUrina() {
		super();
	}
	
	/*GETTERS & SETTERS*/

	public TipoExameUrina getTipoExameUrina() {
		return tipoExameUrina;
	}

	public void setTipoExameUrina(TipoExameUrina tipoExameUrina) {
		this.tipoExameUrina = tipoExameUrina;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
