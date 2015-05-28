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
	

	private static final long serialVersionUID = 4776981056845635368L;

	@NotNull 
	@OneToOne
    @JoinColumn(name="ID_TIPO_ANALISE")
	private TipoAnalise tipoAnalise;
	
	@ManyToOne
	@JoinColumn(name="ID_EXAME_URINA_USUARIO")
	private ExameUrinaUsuario exameUsuario;
	
	@NotNull
	private String resultado;
	
	public ItemExameUrina() {
		super();
	}
	
	/*GETTERS & SETTERS*/

	public TipoAnalise getTipoAnalise() {
		return tipoAnalise;
	}

	public void setTipoAnalise(TipoAnalise tipoAnalise) {
		this.tipoAnalise = tipoAnalise;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public ExameUrinaUsuario getExameUsuario() {
		return exameUsuario;
	}

	public void setExameUsuario(ExameUrinaUsuario exameUsuario) {
		this.exameUsuario = exameUsuario;
	}
	
}
