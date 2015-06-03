package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;

import javax.persistence.Entity;
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
	
	@ManyToOne
	@JoinColumn(name="ID_USUARIO_SANGUE")
	private UsuarioSangue usuarioSangue;
	
	@NotNull 
	@OneToOne
    @JoinColumn(name="ID_TIPO_ANALISE")
	private TipoAnalise tipoAnalise;
	
	@NotNull
	private String resultado;
	
//construtores	
	public ItemExameSangue(UsuarioSangue usuarioSangue,
			TipoAnalise tipoAnalise, String resultado) {
		super();
		this.usuarioSangue = usuarioSangue;
		this.tipoAnalise = tipoAnalise;
		this.resultado = resultado;
	}

	public ItemExameSangue() {
		super();
	}
	
//getters and setters
	public UsuarioSangue getUsuarioSangue() {
		return usuarioSangue;
	}

	public void setUsuarioSangue(UsuarioSangue usuarioSangue) {
		this.usuarioSangue = usuarioSangue;
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
}
