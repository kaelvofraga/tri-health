package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**@author: Alisson Lorscheiter
 * Proposito da Classe: Classe que representa a entidade dos items de exames que estão 
 * presentes no exame de urina
 * 
 */

@Entity
public class ItemExameUrina extends BaseEntity<Long> implements Serializable{
	

	private static final long serialVersionUID = -7293203217920585961L;

	@NotNull 
	@OneToOne
    @JoinColumn(name="ID_TIPOEXAMEURINA")
	private TipoAnalise tipoExameUrina;
	
	@ManyToOne
	@JoinColumn(name="ID_EXAME_URINA_USUARIO")
	private ExameUrinaUsuario exameUsuario;
	
	@NotNull
	private String resultado;
	
	
	
	public ItemExameUrina() {
		super();
	}
	
	/*GETTERS & SETTERS*/

	public TipoAnalise getTipoExameUrina() {
		return tipoExameUrina;
	}

	public void setTipoExameUrina(TipoAnalise tipoExameUrina) {
		this.tipoExameUrina = tipoExameUrina;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}


}
