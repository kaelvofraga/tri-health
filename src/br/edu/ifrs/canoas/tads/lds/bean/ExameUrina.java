package br.edu.ifrs.canoas.tads.lds.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
public class ExameUrina extends BaseEntity<Long> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8539097274270004334L;

	@NotNull @ManyToOne
    @JoinColumn(name="ID_USUARIO") 
	private Usuario usuario;
	
	@NotNull @OneToOne (cascade=CascadeType.PERSIST)
    @JoinColumn(name="ID_TIPOEXAMEURINA")
	private TipoExameUrina tipoExameUrina;
	
//	@NotNull
	private String resultado;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	private String observacao;
	
	public ExameUrina() {
		super();
	}

	public ExameUrina(Usuario usuario, TipoExameUrina tipoExameUrina, String resultado,
			Date data, String observacao) {
		super();
		this.usuario = usuario;
		this.tipoExameUrina = tipoExameUrina;
		this.resultado=resultado;
		this.data = data;
		this.observacao = observacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
