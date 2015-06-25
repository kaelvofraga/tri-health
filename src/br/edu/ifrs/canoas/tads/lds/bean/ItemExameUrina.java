package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**@author: Alisson Lorscheiter
 * Proposito da Classe: Classe que representa a entidade dos items de exames que estão 
 * presentes no exame de urina
 * 
 */

@Entity
public class ItemExameUrina extends BaseEntity<Long> implements Serializable{
	

	private static final long serialVersionUID = 4776981056845635368L;
 
	@OneToOne
    @JoinColumn(name="ID_TIPO_ANALISE")
	private TipoAnalise tipoAnalise;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_EXAME_URINA_USUARIO")
	private ExameUrinaUsuario exameUsuario;
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((exameUsuario == null) ? 0 : exameUsuario.hashCode());
		result = prime * result
				+ ((tipoAnalise == null) ? 0 : tipoAnalise.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemExameUrina other = (ItemExameUrina) obj;
		if (exameUsuario == null) {
			if (other.exameUsuario != null)
				return false;
		} else if (!exameUsuario.equals(other.exameUsuario))
			return false;
		if (tipoAnalise == null) {
			if (other.tipoAnalise != null)
				return false;
		} else if (!tipoAnalise.equals(other.tipoAnalise))
			return false;
		 else if (!tipoAnalise.getTipo().equalsIgnoreCase((other.tipoAnalise.getTipo())))
				return false;
		return true;
	}
	
	
	
}
