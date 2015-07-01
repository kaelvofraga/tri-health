package br.edu.ifrs.canoas.tads.lds.bean;

import br.edu.ifrs.canoas.tads.lds.bean.BaseEntity;
import br.edu.ifrs.canoas.tads.lds.bean.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Resultado
 *
 */
@Entity
public class Resultado extends BaseEntity<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8540791770009046089L;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "USUARIO_ID")
	private Usuario usuario;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "EXAME_ID")
	private Exame exame;
	
	@NotNull(message = "A data do resultado é obrigatória.")
	@Temporal(TemporalType.TIMESTAMP)
	private Date resultadoData;
	
	@Transient
	private List<ResultadoCampos> resultadoCamposList = new ArrayList<ResultadoCampos>();
	
	public Resultado() {
		super();
	}   
	
	public Date getResultadoData() {
		return this.resultadoData;
	}

	public void setResultadoData(Date data) {
		this.resultadoData = data;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Exame getExame() {
		return this.exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public List<ResultadoCampos> getResultadoCamposList() {
		return resultadoCamposList;
	}

	public void setResultadoCamposList(List<ResultadoCampos> resultadoCamposList) {
		this.resultadoCamposList = resultadoCamposList;
	}	
}
