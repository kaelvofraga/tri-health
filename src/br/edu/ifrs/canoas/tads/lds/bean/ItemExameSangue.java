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
	@JoinColumn(name="ID_USUARIO_EXAME")
	private UsuarioExame usuarioExame;
	
	@NotNull 
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
}
