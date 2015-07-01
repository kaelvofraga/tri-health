package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ItemExameSangue extends BaseEntity<Long> implements Serializable{

	/**
	 * @author André ficht
	 * Classe para os resultados dos exames
	 */
	private static final long serialVersionUID = 6438502909147720009L;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_USUARIO_EXAME")
	private UsuarioExame usuarioExame;
	
	@OneToOne
    @JoinColumn(name="ID_TIPO_ANALISE")
	private TipoAnalise tipoAnalise;
	
	@NotNull
	private String resultado;
	
//construtores	
	public ItemExameSangue(UsuarioExame usuarioExame,
			TipoAnalise tipoAnalise, String resultado) {
		super();
		this.usuarioExame = usuarioExame;
		this.tipoAnalise = tipoAnalise;
		this.resultado = resultado;
	}

	public ItemExameSangue() {
		super();
	}
	
//getters and setters
	public UsuarioExame getUsuarioExame() {
		return usuarioExame;
	}

	public void setUsuarioExame(UsuarioExame usuarioExame) {
		this.usuarioExame = usuarioExame;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((resultado == null) ? 0 : resultado.hashCode());
		result = prime * result
				+ ((tipoAnalise == null) ? 0 : tipoAnalise.hashCode());
		result = prime * result
				+ ((usuarioExame == null) ? 0 : usuarioExame.hashCode());
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
		ItemExameSangue other = (ItemExameSangue) obj;
		if (resultado == null) {
			if (other.resultado != null)
				return false;
		} else if (!resultado.equals(other.resultado))
			return false;
		if (tipoAnalise == null) {
			if (other.tipoAnalise != null)
				return false;
		} else if (!tipoAnalise.equals(other.tipoAnalise))
			return false;
		if (usuarioExame == null) {
			if (other.usuarioExame != null)
				return false;
		} else if (!usuarioExame.equals(other.usuarioExame))
			return false;
		return true;
	}
	
	
}
